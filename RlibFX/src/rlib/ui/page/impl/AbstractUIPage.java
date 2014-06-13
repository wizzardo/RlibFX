package rlib.ui.page.impl;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import rlib.ui.page.UIPage;
import rlib.ui.window.UIWindow;

/**
 * Базовая реализация {@link UIPage}.
 * 
 * @author Ronn
 */
public class AbstractUIPage implements UIPage {

	/** рутовый узел {@link UIPage} */
	private final Pane rootNode;

	public AbstractUIPage() {
		this.rootNode = createRoot();
	}

	protected Pane createRoot() {
		return new VBox();
	}

	@Override
	public Pane init(UIWindow window) {
		Pane rootNode = getRootNode();
		initImpl(window, rootNode);
		return rootNode;
	}

	@Override
	public Pane getRootNode() {
		return rootNode;
	}

	protected void initImpl(UIWindow window, Pane root) {
	}

	@Override
	public void prePageShow(UIWindow window) {
	}

	@Override
	public void postPageShow(UIWindow window) {
	}

	@Override
	public void prePageHide(UIWindow window) {
	}

	@Override
	public void postPageHide(UIWindow window) {
	}

	@Override
	public void windowHided(UIWindow window) {
	}

	@Override
	public void windowShowed(UIWindow window) {
	}

	@Override
	public void windowClosed(UIWindow window) {
	}
}
