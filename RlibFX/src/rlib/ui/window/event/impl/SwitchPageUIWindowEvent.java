package rlib.ui.window.event.impl;

import javafx.event.EventType;
import rlib.ui.page.UIPage;
import rlib.ui.window.event.UIWindowEvent;
import rlib.ui.window.event.target.impl.SwitchPageEventTarget;

/**
 * Реализация события {@link UIWindowEvent} при переключении {@link UIPage}.
 *
 * @author Ronn
 */
public class SwitchPageUIWindowEvent extends UIWindowEvent {

    private static final long serialVersionUID = 97789314357027494L;

    public static final EventType<SwitchPageUIWindowEvent> EVENT_TYPE = new EventType<>(UIWindowEvent.EVENT_TYPE, SwitchPageUIWindowEvent.class.getSimpleName());

    public SwitchPageUIWindowEvent(final Object source, final SwitchPageEventTarget target, final EventType<? extends SwitchPageUIWindowEvent> eventType) {
        super(source, target, eventType);
    }
}
