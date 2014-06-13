package rlib.ui.page;

import javafx.scene.layout.Pane;
import rlib.ui.window.UIWindow;

/**
 * Интерфейс для реализации UI страницы.
 * 
 * @author Ronn
 */
public interface UIPage {

	public Pane init(UIWindow window);

	public void prePageShow(UIWindow window);

	public void postPageShow(UIWindow window);

	public void prePageHide(UIWindow window);

	public void postPageHide(UIWindow window);

	public void windowHided(UIWindow window);

	public void windowShowed(UIWindow window);

	public void windowClosed(UIWindow window);

	/**
	 * @return рутовый узел {@link UIPage}.
	 */
	public Pane getRootNode();
}
