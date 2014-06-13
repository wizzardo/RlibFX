package rlib.ui.window;

import rlib.ui.page.UIPage;

/**
 * Интерфейс для реализации UI окна.
 * 
 * @author Ronn
 */
public interface UIWindow {

	/**
	 * Отобразить UI страницу в окне указанного класса.
	 * 
	 * @param pageClass класс нужной страницы.
	 */
	public void showPage(Class<? extends UIPage> pageClass);
}
