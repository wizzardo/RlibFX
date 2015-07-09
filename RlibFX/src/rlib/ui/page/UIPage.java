package rlib.ui.page;

import javafx.scene.layout.Pane;
import rlib.ui.window.UIWindow;

/**
 * Интерфейс для реализации UI страницы.
 *
 * @author Ronn
 */
public interface UIPage {

    /**
     * @return рутовый узел {@link UIPage}.
     */
    public Pane getRootNode();

    /**
     * Получение окна, в котором находится страница.
     *
     * @return окно страницы.
     */
    public UIWindow getWindow();

    /**
     * Инициализация страницы для указанного окна.
     *
     * @param window окно в котором будет размещена страницаю
     * @return рутовый узел этой страницы.
     */
    public Pane init(UIWindow window);

    /**
     * Обработка этой страницы после ее скрытия.
     *
     * @param window окно в котором была скрыта эта страница.
     */
    public void postPageHide(UIWindow window);

    /**
     * Обработка этой страницы после ее показа в окне.
     *
     * @param window окно в котором показана страница.
     */
    public void postPageShow(UIWindow window);

    /**
     * Обработка этой страницы перед ее скрытием.
     *
     * @param window окно в котором эта страница показана.
     */
    public void prePageHide(UIWindow window);

    /**
     * Обработка этой страницы перед ее показом.
     *
     * @param window окно в котором она будет показана.
     */
    public void prePageShow(UIWindow window);

    /**
     * Обработка события закрытия окна.
     *
     * @param window закрытое окно.
     */
    public void windowClosed(UIWindow window);

    /**
     * Обработка события скрытия окна.
     *
     * @param window скрытое окно.
     */
    public void windowHided(UIWindow window);

    /**
     * Обработка события отображения окна.
     *
     * @param window отображенное окно.
     */
    public void windowShowed(UIWindow window);
}
