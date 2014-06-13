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
	 * Закрыть окно.
	 */
	public void close();

	/**
	 * @return текущая {@link UIPage} у {@link UIWindow}.
	 */
	public UIPage getCurrentPage();

	/**
	 * @return текущая позиция окна.
	 */
	public Point getPosition();

	/**
	 * Получение текущего титула окна.
	 * 
	 * @return текущий титул окна.
	 */
	public String getTitle();

	/**
	 * Спрятать окно.
	 */
	public void hide();

	/**
	 * Переместить окно в центр экрана.
	 */
	public void moveToCenter();

	/**
	 * @param currentPage текущая {@link UIPage} у {@link UIWindow}.
	 */
	public void setCurrentPage(UIPage currentPage);

	/**
	 * Установка новой позиции окна.
	 * 
	 * @param x новая позиция по X.
	 * @param y новая позиция по Y.
	 */
	public void setPosition(int x, int y);

	/**
	 * Установка нового размера окна.
	 * 
	 * @param width новая ширина окна.
	 * @param height новая высота окна.
	 */
	public void setSize(int width, int height);

	/**
	 * Установка нового титула окна.
	 * 
	 * @param title титул окна.
	 */
	public void setTitle(String title);

	/**
	 * Отобразить окно.
	 */
	public void show();

	/**
	 * Отобразить UI страницу в окне указанного класса.
	 * 
	 * @param pageClass класс нужной страницы.
	 */
	public void showPage(Class<? extends UIPage> pageClass);
}
