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

	/** окно в котором находится страница */
	private UIWindow window;

	public AbstractUIPage() {
		this.rootNode = createRoot();
	}

	protected Pane createRoot() {
		return new VBox();
	}

	@Override
	public Pane init(final UIWindow window) {
		this.window = window;

		final Pane rootNode = getRootNode();
		initImpl(window, rootNode);

		return rootNode;
	}

	@Override
	public Pane getRootNode() {
		return rootNode;
	}

	protected void initImpl(final UIWindow window, final Pane root) {
	}

	@Override
	public void prePageShow(final UIWindow window) {
	}

	@Override
	public void postPageShow(final UIWindow window) {
	}

	@Override
	public void prePageHide(final UIWindow window) {
	}

	@Override
	public void postPageHide(final UIWindow window) {
	}

	@Override
	public void windowHided(final UIWindow window) {
	}

	@Override
	public void windowShowed(final UIWindow window) {
	}

	@Override
	public void windowClosed(final UIWindow window) {
	}

	@Override
	public UIWindow getWindow() {
		return window;
	}
}
