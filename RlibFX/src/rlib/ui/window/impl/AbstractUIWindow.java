package rlib.ui.window.impl;

import java.awt.Point;

import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
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
import rlib.ui.window.event.UIWindowEvent;
import rlib.ui.window.event.impl.SwitchPageUIWindowEvent;
import rlib.ui.window.event.target.impl.SwitchPageEventTarget;
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

	/** таблица обработчиков событий */
	private final Table<EventType<? extends Event>, Array<EventHandler<? super Event>>> eventHandlers;

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
		this.eventHandlers = TableFactory.newObjectTable();
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
	public void addEventHandler(final EventType<? extends Event> eventType, final EventHandler<? super Event> eventHandler) {

		final Table<EventType<? extends Event>, Array<EventHandler<? super Event>>> eventHandlers = getEventHandlers();

		Array<EventHandler<? super Event>> handlers = eventHandlers.get(eventType);

		if(handlers == null) {
			handlers = ArrayFactory.newArray(EventHandler.class);
			eventHandlers.put(eventType, handlers);
		}

		handlers.add(eventHandler);
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
		rootNode.layout();
		return rootNode;
	}

	/**
	 * Настройка рутового узла для {@link UIWindow}.
	 */
	protected void configureRoot() {

		final DoubleProperty width = rootNode.prefWidthProperty();
		width.bind(stage.widthProperty());
		width.addListener((ChangeListener<Number>) (observable, oldValue, newValue) -> {
			if(LOGGER.isEnabledDebug()) {
				LOGGER.debug("new root width value " + newValue);
			}
		});

		width.addListener((ChangeListener<Number>) (observable, oldValue, newValue) -> System.out.println("new root width value " + newValue));

		final DoubleProperty height = rootNode.prefHeightProperty();
		height.bind(stage.heightProperty());
		height.addListener((ChangeListener<Number>) (observable, oldValue, newValue) -> {
			if(LOGGER.isEnabledDebug()) {
				LOGGER.debug("new root height value " + newValue);
			}
		});

		setRootPageNode(configurePageRoot(rootNode));
	}

	/**
	 * Создание и определение сцены {@link UIWindow}.
	 */
	protected Scene createdScene() {
		final Scene scene = new Scene(rootNode, 400, 400, Color.WHITE);
		return scene;
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
	 * @return таблица обработчиков событий.
	 */
	protected Table<EventType<? extends Event>, Array<EventHandler<? super Event>>> getEventHandlers() {
		return eventHandlers;
	}

	/**
	 * @return таблица рутовых узлов {@link UIPage}. scene.
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

	@Override
	public Pane getRootNode() {
		return rootNode;
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
	public void loadStylesheets(final String path) {
		final ObservableList<String> stylesheets = scene.getStylesheets();

		System.out.println("styles " + stylesheets);

		stylesheets.add(path);
	}

	@Override
	public void moveToCenter() {
		stage.centerOnScreen();
	}

	@Override
	public void notify(final UIWindowEvent event) {

		final Table<EventType<? extends Event>, Array<EventHandler<? super Event>>> eventHandlers = getEventHandlers();

		for(EventType<? extends Event> eventType = event.getEventType(); eventType != null; eventType = eventType.getSuperType()) {

			final Array<EventHandler<? super Event>> handlers = eventHandlers.get(eventType);

			if(handlers == null || handlers.isEmpty()) {
				continue;
			}

			for(final EventHandler<? super Event> handler : handlers.array()) {

				if(handler == null) {
					break;
				}

				handler.handle(event);
			}
		}
	}

	@Override
	public void setCurrentPage(final UIPage currentPage) {
		this.currentPage = currentPage;
	}

	@Override
	public void setMinimalSize(final int width, final int height) {
		stage.setMinHeight(height);
		stage.setMinWidth(width);
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

		boolean created = false;

		if(plane == null) {
			synchronized(pageRoots) {

				plane = pageRoots.get(page);

				if(plane == null) {
					plane = page.init(this);
					pageRoots.put(page, plane);
					created = true;
				}
			}
		}

		final UIPage currentPage = getCurrentPage();

		if(currentPage != null) {

			try {

				final Pane currentRoot = pageRoots.get(currentPage);

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

			if(created) {
				height.addListener((ChangeListener<Number>) (observable, oldValue, newValue) -> {
					if(LOGGER.isEnabledDebug()) {
						LOGGER.debug("new page height value " + newValue);
					}
				});
			}

			height.bind(rootPageNode.heightProperty());

			final DoubleProperty width = plane.prefWidthProperty();

			if(created) {
				width.addListener((ChangeListener<Number>) (observable, oldValue, newValue) -> {
					if(LOGGER.isEnabledDebug()) {
						LOGGER.debug("new page width value " + newValue);
					}
				});
			}

			width.bind(rootPageNode.widthProperty());

			FXUtils.addToPane(plane, rootPageNode);

		} catch(final Exception e) {
			LOGGER.warning(this, e);
		} finally {
			page.postPageShow(this);
		}

		setCurrentPage(page);

		SwitchPageEventTarget eventTarget = new SwitchPageEventTarget(currentPage, page);
		SwitchPageUIWindowEvent event = new SwitchPageUIWindowEvent(this, eventTarget, SwitchPageUIWindowEvent.EVENT_TYPE);

		notify(event);
	}
}
