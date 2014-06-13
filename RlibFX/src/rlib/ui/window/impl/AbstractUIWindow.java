package rlib.ui.window.impl;

import java.awt.Point;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import rlib.logging.Logger;
import rlib.logging.LoggerManager;
import rlib.ui.page.UIPage;
import rlib.ui.util.FXUtils;
import rlib.ui.window.UIWindow;
import rlib.util.ClassUtils;
import rlib.util.array.Array;
import rlib.util.array.ArrayFactory;
import rlib.util.table.Table;
import rlib.util.table.TableFactory;

/**
 * Базовая реализация {@link UIWindow}.
 * 
 * @author Ronn
 */
public class AbstractUIWindow implements UIWindow {

	protected static final Logger LOGGER = LoggerManager.getLogger(UIWindow.class);

	/** список доступных {@link UIPage} в рамках этого {@link UIWindow} */
	private final Array<Class<? extends UIPage>> availablePages;
	/** таблица созданных {@link UIPage} */
	private final Table<Class<? extends UIPage>, UIPage> pages;

	/** таблица рутовых узлов {@link UIPage} */
	private final Table<UIPage, Pane> pageRoots;

	/** {@link Stage} в рамках которой рисуется {@link UIWindow} */
	private final Stage stage;
	/** сцена {@link UIWindow} */
	private final Scene scene;
	/** рутовый узел {@link UIWindow} */
	private final Pane rootNode;

	/** рутовый узел для {@link UIPage} */
	private volatile Pane rootPageNode;
	/** текущая {@link UIPage} у {@link UIWindow} */
	private volatile UIPage currentPage;

	public AbstractUIWindow(final Stage stage, final Array<Class<? extends UIPage>> availablePages) {
		this.availablePages = ArrayFactory.newArray(Class.class);
		this.availablePages.addAll(availablePages);
		this.pages = TableFactory.newObjectTable();
		this.pageRoots = TableFactory.newObjectTable();
		this.stage = stage;
		this.rootNode = createRoot();
		this.scene = createdScene();

		configureRoot();

		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void close() {
		stage.close();

		final UIPage currentPage = getCurrentPage();

		if(currentPage != null) {
			currentPage.windowClosed(this);
		}
	}

	/**
	 * Настройка рутового узла для размещения {@link UIPage}.
	 * 
	 * @param rootNode рутовый узел {@link UIWindow}.
	 * @return узел, в котором будут размещаться {@link UIPage}.
	 */
	protected Pane configurePageRoot(final Pane rootNode) {
		return rootNode;
	}

	/**
	 * Настройка рутового узла для {@link UIWindow}.
	 */
	protected void configureRoot() {

		final DoubleProperty width = rootNode.prefWidthProperty();
		width.bind(scene.widthProperty());

		final DoubleProperty heigh = rootNode.prefHeightProperty();
		heigh.bind(scene.heightProperty());

		setRootPageNode(configurePageRoot(rootNode));
	}

	/**
	 * Создание и определение сцены {@link UIWindow}.
	 */
	protected Scene createdScene() {
		return new Scene(rootNode, 400, 400, Color.WHITE);
	}

	/**
	 * Создание и определении рутового узлы {@link UIWindow}.
	 */
	protected Pane createRoot() {
		return new VBox();
	}

	@Override
	public UIPage getCurrentPage() {
		return currentPage;
	}

	/**
	 * @return таблица рутовых узлов {@link UIPage}.
	 */
	protected Table<UIPage, Pane> getPageRoots() {
		return pageRoots;
	}

	/**
	 * @return таблица созданных {@link UIPage}.
	 */
	protected Table<Class<? extends UIPage>, UIPage> getPages() {
		return pages;
	}

	@Override
	public Point getPosition() {
		return new Point((int) stage.getX(), (int) stage.getY());
	}

	/**
	 * @return рутовый узел для {@link UIPage}.
	 */
	protected Pane getRootPageNode() {
		return rootPageNode;
	}

	@Override
	public String getTitle() {
		return stage.getTitle();
	}

	@Override
	public void hide() {
		stage.hide();

		final UIPage currentPage = getCurrentPage();

		if(currentPage != null) {
			currentPage.windowHided(this);
		}
	}

	@Override
	public void moveToCenter() {
		stage.centerOnScreen();
	}

	@Override
	public void setCurrentPage(final UIPage currentPage) {
		this.currentPage = currentPage;
	}

	@Override
	public void setPosition(final int x, final int y) {
		stage.setX(x);
		stage.setY(y);
	}

	protected void setRootPageNode(final Pane rootPageNode) {
		this.rootPageNode = rootPageNode;
	}

	@Override
	public void setSize(final int width, final int height) {
		stage.setWidth(width);
		stage.setHeight(height);
	}

	@Override
	public void setTitle(final String title) {
		stage.setTitle(title);
	}

	@Override
	public void show() {
		stage.show();

		final UIPage currentPage = getCurrentPage();

		if(currentPage != null) {
			currentPage.windowShowed(this);
		}
	}

	@Override
	public void showPage(final Class<? extends UIPage> pageClass) {

		final Table<Class<? extends UIPage>, UIPage> pages = getPages();
		final Table<UIPage, Pane> pageRoots = getPageRoots();

		final Pane rootPageNode = getRootPageNode();

		UIPage page = pages.get(pageClass);

		if(page == null) {
			synchronized(pages) {

				page = pages.get(pageClass);

				if(page == null) {
					page = ClassUtils.newInstance(pageClass);
					pages.put(pageClass, page);
				}
			}
		}

		Pane plane = pageRoots.get(page);

		if(plane == null) {
			synchronized(pageRoots) {

				plane = pageRoots.get(page);

				if(plane == null) {
					plane = page.init(this);
					pageRoots.put(page, plane);
				}
			}
		}

		final UIPage currentPage = getCurrentPage();

		if(currentPage != null) {

			try {

				final Pane currentRoot = pageRoots.get(page);

				if(currentRoot != null) {

					currentPage.prePageHide(this);
					try {

						final DoubleProperty width = currentRoot.prefWidthProperty();
						width.unbind();

						final DoubleProperty heigh = currentRoot.prefHeightProperty();
						heigh.unbind();

						FXUtils.removeToPane(currentRoot, rootPageNode);

					} catch(final Exception e) {
						LOGGER.warning(this, e);
					} finally {
						currentPage.postPageHide(this);
					}
				}

			} catch(final Exception e) {
				LOGGER.warning(this, e);
			} finally {
				setCurrentPage(null);
			}
		}

		page.prePageShow(this);
		try {

			final DoubleProperty height = plane.prefHeightProperty();
			height.bind(rootPageNode.heightProperty());

			final DoubleProperty width = plane.prefWidthProperty();
			width.bind(rootPageNode.widthProperty());

			FXUtils.addToPane(plane, rootPageNode);

		} catch(final Exception e) {
			LOGGER.warning(this, e);
		} finally {
			page.postPageShow(this);
		}

		setCurrentPage(page);
	}
}
