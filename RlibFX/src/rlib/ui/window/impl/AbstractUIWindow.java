package rlib.ui.window.impl;

import javafx.beans.property.DoubleProperty;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import rlib.logging.Logger;
import rlib.logging.LoggerManager;
import rlib.ui.page.UIPage;
import rlib.ui.util.FXUtils;
import rlib.ui.window.UIWindow;
import rlib.ui.window.event.UIWindowEvent;
import rlib.ui.window.event.impl.SwitchPageUIWindowEvent;
import rlib.ui.window.event.target.impl.SwitchPageEventTarget;
import rlib.util.ClassUtils;
import rlib.util.array.Array;
import rlib.util.array.ArrayFactory;
import rlib.util.dictionary.DictionaryFactory;
import rlib.util.dictionary.ObjectDictionary;

import java.awt.*;

/**
 * Базовая реализация {@link UIWindow}.
 *
 * @author Ronn
 */
public class AbstractUIWindow implements UIWindow {

    protected static final Logger LOGGER = LoggerManager.getLogger(UIWindow.class);

    /**
     * Словарь обработчиков событий.
     */
    private final ObjectDictionary<EventType<? extends Event>, Array<EventHandler<? super Event>>> eventHandlers;

    /**
     * Список доступных {@link UIPage} в рамках этого {@link UIWindow}.
     */
    private final Array<Class<? extends UIPage>> availablePages;

    /**
     * Словарь созданных {@link UIPage}.
     */
    private final ObjectDictionary<Class<? extends UIPage>, UIPage> pages;

    /**
     * Словарь рутовых узлов {@link UIPage}.
     */
    private final ObjectDictionary<UIPage, Pane> pageRoots;

    /**
     * {@link Stage} в рамках которой рисуется {@link UIWindow}.
     */
    private final Stage stage;

    /**
     * Сцена {@link UIWindow}.
     */
    private final Scene scene;

    /**
     * Рутовый узел {@link UIWindow}.
     */
    private volatile Pane rootNode;

    /**
     * Рутовый узел для {@link UIPage}.
     */
    private volatile Pane rootPageNode;

    /**
     * Текущая {@link UIPage} у {@link UIWindow}.
     */
    private volatile UIPage currentPage;

    /**
     * Предыдущая страница окна.
     */
    private volatile UIPage prevPage;

    public AbstractUIWindow(final Stage stage, final Array<Class<? extends UIPage>> availablePages) {
        this.eventHandlers = DictionaryFactory.newObjectDictionary();
        this.availablePages = ArrayFactory.newArray(Class.class);
        this.availablePages.addAll(availablePages);
        this.pages = DictionaryFactory.newObjectDictionary();
        this.pageRoots = DictionaryFactory.newObjectDictionary();
        this.stage = configureStage(stage);
        this.rootNode = createRoot();
        this.scene = createdScene();

        configureRoot();

        configureStage(stage).setScene(scene);
        configureStage(stage).show();
    }

    @Override
    public void addEventHandler(final EventType<? extends Event> eventType, final EventHandler<? super Event> eventHandler) {

        final ObjectDictionary<EventType<? extends Event>, Array<EventHandler<? super Event>>> eventHandlers = getEventHandlers();

        Array<EventHandler<? super Event>> handlers = eventHandlers.get(eventType);

        if (handlers == null) {
            handlers = ArrayFactory.newArray(EventHandler.class);
            eventHandlers.put(eventType, handlers);
        }

        handlers.add(eventHandler);
    }

    @Override
    public void close() {
        stage.close();

        final UIPage currentPage = getCurrentPage();

        if (currentPage != null) {
            currentPage.windowClosed(this);
        }
    }

    /**
     * Настройка рутового узла для размещения {@link UIPage}.
     *
     * @param rootNode рутовый узел {@link UIWindow}.
     * @return узел, в котором будут размещаться {@link UIPage}.
     */
    protected Pane configurePageRoot(final Pane rootNode) {
        rootNode.layout();
        return rootNode;
    }

    /**
     * Настройка рутового узла для {@link UIWindow}.
     */
    protected void configureRoot() {
        FXUtils.bindFixedSize(rootNode, stage.widthProperty(), stage.heightProperty());
        setRootPageNode(configurePageRoot(rootNode));
    }

    protected Stage configureStage(final Stage stage) {
        return stage;
    }

    /**
     * Создание и определении рутового узлы {@link UIWindow}.
     */
    protected Pane createRoot() {
        return new VBox();
    }

    /**
     * Создание и определение сцены {@link UIWindow}.
     */
    protected Scene createdScene() {
        final Scene scene = new Scene(rootNode, 400, 400, Color.WHITE);
        return scene;
    }

    @Override
    public UIPage getCurrentPage() {
        return currentPage;
    }

    @Override
    public void setCurrentPage(final UIPage currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * @return словарь обработчиков событий.
     */
    protected ObjectDictionary<EventType<? extends Event>, Array<EventHandler<? super Event>>> getEventHandlers() {
        return eventHandlers;
    }

    @Override
    public Stage getOwner() {
        return stage;
    }

    /**
     * @return словарь рутовых узлов {@link UIPage}. scene.
     */
    protected ObjectDictionary<UIPage, Pane> getPageRoots() {
        return pageRoots;
    }

    /**
     * @return словарь созданных {@link UIPage}.
     */
    protected ObjectDictionary<Class<? extends UIPage>, UIPage> getPages() {
        return pages;
    }

    @Override
    public Point getPosition() {
        return new Point((int) stage.getX(), (int) stage.getY());
    }

    @Override
    public Pane getRootNode() {
        return rootNode;
    }

    protected void setRootNode(final Pane rootNode) {
        this.rootNode = rootNode;
    }

    /**
     * @return рутовый узел для {@link UIPage}.
     */
    protected Pane getRootPageNode() {
        return rootPageNode;
    }

    protected void setRootPageNode(final Pane rootPageNode) {
        this.rootPageNode = rootPageNode;
    }

    @Override
    public String getTitle() {
        return stage.getTitle();
    }

    @Override
    public void setTitle(final String title) {
        stage.setTitle(title);
    }

    @Override
    public double getX() {
        return stage.getX();
    }

    @Override
    public double getY() {
        return stage.getY();
    }

    @Override
    public void hide() {
        stage.hide();

        final UIPage currentPage = getCurrentPage();

        if (currentPage != null) {
            currentPage.windowHided(this);
        }
    }

    @Override
    public void loadStylesheets(final String path) {
        final ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.add(path);
    }

    @Override
    public void moveToCenter() {
        stage.centerOnScreen();
    }

    @Override
    public void notify(final UIWindowEvent event) {

        final ObjectDictionary<EventType<? extends Event>, Array<EventHandler<? super Event>>> eventHandlers = getEventHandlers();

        for (EventType<? extends Event> eventType = event.getEventType(); eventType != null; eventType = (EventType<? extends Event>) eventType.getSuperType()) {

            final Array<EventHandler<? super Event>> handlers = eventHandlers.get(eventType);

            if (handlers == null || handlers.isEmpty()) {
                continue;
            }

            for (final EventHandler<? super Event> handler : handlers.array()) {

                if (handler == null) {
                    break;
                }

                handler.handle(event);
            }
        }
    }

    @Override
    public void setMaximumSize(final int width, final int height) {
        stage.setMaxWidth(width);
        stage.setMaxHeight(height);
    }

    @Override
    public void setMinimalSize(final int width, final int height) {
        stage.setMinHeight(height);
        stage.setMinWidth(width);
    }

    @Override
    public void setPosition(final int x, final int y) {
        stage.setX(x);
        stage.setY(y);
    }

    @Override
    public void setResizable(boolean resizable) {
        stage.setResizable(resizable);
    }

    @Override
    public void setSize(final int width, final int height) {
        stage.setWidth(width);
        stage.setHeight(height);
    }

    @Override
    public void show() {
        stage.show();

        final UIPage currentPage = getCurrentPage();

        if (currentPage != null) {
            currentPage.windowShowed(this);
        }
    }

    private void setPrevPage(final UIPage prevPage) {
        this.prevPage = prevPage;
    }

    @Override
    public void showPage(final Class<? extends UIPage> pageClass) {

        final ObjectDictionary<Class<? extends UIPage>, UIPage> pages = getPages();
        final ObjectDictionary<UIPage, Pane> pageRoots = getPageRoots();

        final Pane rootPageNode = getRootPageNode();

        UIPage page = pages.get(pageClass);

        if (page == null) {
            synchronized (pages) {

                page = pages.get(pageClass);

                if (page == null) {
                    page = ClassUtils.newInstance(pageClass);
                    pages.put(pageClass, page);
                }
            }
        }

        Pane plane = pageRoots.get(page);

        boolean created = false;

        if (plane == null) {
            synchronized (pageRoots) {

                plane = pageRoots.get(page);

                if (plane == null) {
                    plane = page.init(this);
                    pageRoots.put(page, plane);
                    created = true;
                }
            }
        }

        final UIPage currentPage = getCurrentPage();
        final Pane currentRoot = currentPage == null? null : pageRoots.get(currentPage);

        if (currentPage != null && currentRoot != null) {

            try {

                currentPage.prePageHide(this);
                try {

                    DoubleProperty width = currentRoot.prefWidthProperty();
                    width.unbind();

                    width = currentRoot.minWidthProperty();
                    width.unbind();

                    width = currentRoot.maxWidthProperty();
                    width.unbind();

                    DoubleProperty height = currentRoot.prefHeightProperty();
                    height.unbind();

                    height = currentRoot.minHeightProperty();
                    height.unbind();

                    height = currentRoot.maxHeightProperty();
                    height.unbind();

                    FXUtils.removeToPane(currentRoot, rootPageNode);

                } catch (final Exception e) {
                    LOGGER.warning(this, e);
                } finally {
                    currentPage.postPageHide(this);
                }

            } catch (final Exception e) {
                LOGGER.warning(this, e);
            } finally {
                setCurrentPage(null);
            }

            setPrevPage(currentPage);
        }

        page.prePageShow(this);
        try {

            DoubleProperty height = plane.prefHeightProperty();

            if (created) {
                height.addListener((observable, oldValue, newValue) -> {
                    if (LOGGER.isEnabledDebug()) {
                        LOGGER.debug("new page height value " + newValue);
                    }
                });
            }

            height.bind(rootPageNode.heightProperty());

            height = plane.minHeightProperty();
            height.bind(rootPageNode.heightProperty());

            height = plane.maxHeightProperty();
            height.bind(rootPageNode.heightProperty());

            DoubleProperty width = plane.prefWidthProperty();

            if (created) {
                width.addListener((observable, oldValue, newValue) -> {
                    if (LOGGER.isEnabledDebug()) {
                        LOGGER.debug("new page width value " + newValue);
                    }
                });
            }

            width = plane.minWidthProperty();
            width.bind(rootPageNode.widthProperty());

            width = plane.maxWidthProperty();
            width.bind(rootPageNode.widthProperty());

            FXUtils.addToPane(plane, rootPageNode);

        } catch (final Exception e) {
            LOGGER.warning(this, e);
        } finally {
            page.postPageShow(this);
        }

        setCurrentPage(page);

        final SwitchPageEventTarget eventTarget = new SwitchPageEventTarget(currentPage, page);
        final SwitchPageUIWindowEvent event = new SwitchPageUIWindowEvent(this, eventTarget, SwitchPageUIWindowEvent.EVENT_TYPE);

        notify(event);
    }

    @Override
    public Class<? extends UIPage> getPrevPage() {
        return prevPage == null? null : prevPage.getClass();
    }
}
