package rlib.ui.window;

import java.awt.Point;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.layout.Pane;
import rlib.ui.page.UIPage;
import rlib.ui.window.event.UIWindowEvent;

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

	/**
	 * Загрука и приминение CSS.
	 * 
	 * @param path путь к файлу CSS.
	 */
	public void loadStylesheets(String path);

	/**
	 * @return рутовый контейнер окна.
	 */
	public Pane getRootNode();

	/**
	 * Установка минимального размера окна.
	 * 
	 * @param width ширина окна.
	 * @param height высота окна.
	 */
	public void setMinimalSize(int width, int height);

	/**
	 * Добавление обработчиков событий окна.
	 * 
	 * @param eventType тип события.
	 * @param eventHandler обработчик события.
	 */
	public void addEventHandler(EventType<? extends Event> eventType, EventHandler<? super Event> eventHandler);

	/**
	 * Уведомление всех обработчиков о указнном событии.
	 * 
	 * @param event отправленное событие.
	 */
	public void notify(UIWindowEvent event);

	/**
	 * Установка максимального размера окна.
	 * 
	 * @param width максимальная ширина.
	 * @param height максимальная высота.
	 */
	public void setMaximumSize(final int width, final int height);

	/**
	 * Указание, можно ли изменять размер окна.
	 * 
	 * @param rezisable можно ли изменять размер окна.
	 */
	public void setRezisable(boolean rezisable);

	/**
	 * @return текущая координата X окна.
	 */
	public double getX();

	/**
	 * @return текущая координата Y окна.
	 */
	public double getY();
}
