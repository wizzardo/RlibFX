package rlib.ui.window.event;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import rlib.ui.window.UIWindow;

/**
 * Базовая реализация события {@link UIWindow}.
 *
 * @author Ronn
 */
public class UIWindowEvent extends Event {

    private static final long serialVersionUID = -1911052015865869031L;

    public static final EventType<UIWindowEvent> EVENT_TYPE = new EventType<>(UIWindowEvent.class.getSimpleName());

    public UIWindowEvent(final EventType<? extends UIWindowEvent> eventType) {
        super(eventType);
    }

    public UIWindowEvent(final Object source, final EventTarget target, final EventType<? extends UIWindowEvent> eventType) {
        super(source, target, eventType);
    }
}
