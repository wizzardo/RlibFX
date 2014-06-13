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

	public void preShow(UIWindow window);

	public void postShow(UIWindow window);
}
