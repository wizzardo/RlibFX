package rlib.ui.window;

import java.awt.Point;

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

	public void setTitle(String title);

	public String getTitle();

	public void setSize(int width, int height);

	public void setPosition(int x, int y);

	public Point getPosition();
}
