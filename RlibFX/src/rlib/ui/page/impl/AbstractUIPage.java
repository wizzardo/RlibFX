package rlib.ui.page.impl;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import rlib.ui.page.UIPage;
import rlib.ui.window.UIWindow;

/**
 * Базовая реализация {@link UIPage}.
 * 
 * @author Ronn
 */
public class AbstractUIPage implements UIPage {

	/** рутовый узел страницы */
	private final Pane rootNode;

	public AbstractUIPage() {

		this.rootNode = createRoot();
	}

	protected Pane createRoot() {
		return new VBox();
	}

	protected Scene createdScene() {
		return new Scene(rootNode, 400, 280, Color.LIGHTGRAY);
	}

	@Override
	public Pane init(UIWindow window) {
		// TODO Автоматически созданная заглушка метода
		return null;
	}

	@Override
	public void preShow(UIWindow window) {
		// TODO Автоматически созданная заглушка метода

	}

	@Override
	public void postShow(UIWindow window) {
		// TODO Автоматически созданная заглушка метода

	}
}
