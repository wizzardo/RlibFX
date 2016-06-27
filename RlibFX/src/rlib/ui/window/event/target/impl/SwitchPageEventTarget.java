package rlib.ui.window.event.target.impl;

import javafx.event.EventDispatchChain;
import javafx.event.EventTarget;
import rlib.ui.page.UIPage;
import rlib.ui.window.UIWindow;
import rlib.ui.window.event.UIWindowEvent;

/**
 * Реализация таргета для {@link UIWindowEvent} перекулючения {@link UIPage} в {@link UIWindow}.
 *
 * @author Ronn
 */
public class SwitchPageEventTarget implements EventTarget {

    /**
     * Предыдущая страница.
     */
    private final UIPage prevPage;

    /**
     * Новая страница.
     */
    private final UIPage newPage;

    public SwitchPageEventTarget(final UIPage prevPage, final UIPage newPage) {
        this.prevPage = prevPage;
        this.newPage = newPage;
    }

    @Override
    public EventDispatchChain buildEventDispatchChain(final EventDispatchChain tail) {
        return null;
    }

    /**
     * @return новая страница.
     */
    public UIPage getNewPage() {
        return newPage;
    }

    /**
     * @return предыдущая страница.
     */
    public UIPage getPrevPage() {
        return prevPage;
    }
}
