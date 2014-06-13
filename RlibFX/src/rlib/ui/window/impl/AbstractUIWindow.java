package rlib.ui.window.impl;

import java.awt.Point;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import rlib.ui.page.UIPage;
import rlib.ui.window.UIWindow;
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

	/** список доступных страниц в рамках этого окна */
	private final Array<Class<? extends UIPage>> availablePages;
	/** таблица созданных страниц */
	private final Table<Class<? extends UIPage>, UIPage> pages;

	/** стадия в рамках которой рисуется окно */
	private final Stage stage;
	/** сцена окна */
	private final Scene scene;
	/** рутовый узел окна */
	private final Pane rootNode;

	public AbstractUIWindow(Stage stage, Array<Class<? extends UIPage>> availablePages) {
		this.availablePages = ArrayFactory.newArray(Class.class);
		this.availablePages.addAll(availablePages);
		this.pages = TableFactory.newObjectTable();
		this.stage = stage;
		this.rootNode = createRoot();
		this.scene = createdScene();

		configureRoot();

		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void showPage(Class<? extends UIPage> pageClass) {
		// TODO Автоматически созданная заглушка метода

	}

	protected void configureRoot() {

		DoubleProperty width = rootNode.prefWidthProperty();
		width.bind(scene.widthProperty());

		DoubleProperty heigh = rootNode.prefHeightProperty();
		heigh.bind(scene.heightProperty());
	}

	protected Pane createRoot() {
		return new VBox();
	}

	protected Scene createdScene() {
		return new Scene(rootNode, 400, 400, Color.WHITE);
	}

	@Override
	public void setTitle(String title) {
		stage.setTitle(title);
	}

	@Override
	public String getTitle() {
		return stage.getTitle();
	}

	@Override
	public void setSize(int width, int height) {
		stage.setWidth(width);
		stage.setHeight(height);
	}

	@Override
	public void setPosition(int x, int y) {
		stage.setX(x);
		stage.setY(y);
	}

	@Override
	public Point getPosition() {
		return new Point((int) stage.getX(), (int) stage.getY());
	}
}
