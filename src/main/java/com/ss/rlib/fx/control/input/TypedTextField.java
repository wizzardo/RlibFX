package com.ss.rlib.fx.control.input;

import static com.ss.rlib.common.util.ClassUtils.unsafeCast;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.ScrollEvent;
import org.jetbrains.annotations.NotNull;

/**
 * The implementation of a typed text field control.
 *
 * @author JavaSaBr
 */
public class TypedTextField<T> extends TextField {

    /**
     * The scroll power.
     */
    private float scrollPower;

    public TypedTextField() {
        setTextFormatter(createTextFormatter());
        setOnScroll(this::scrollValue);
    }

    public TypedTextField(@NotNull String text) {
        super(text);
        setTextFormatter(createTextFormatter());
        setOnScroll(this::scrollValue);
    }

    /**
     * Creates a new text formatter.
     *
     * @return the new text formatter.
     */
    protected @NotNull TextFormatter<T> createTextFormatter() {
        throw new UnsupportedOperationException();
    }

    /**
     * Sets the scroll power.
     *
     * @param scrollPower the scroll power.
     */
    public void setScrollPower(float scrollPower) {
        this.scrollPower = scrollPower;
    }

    /**
     * Gets the scroll power.
     *
     * @return the scroll power.
     */
    public float getScrollPower() {
        return scrollPower;
    }

    /**
     * Scroll the current value.
     *
     * @param event the scroll event.
     */
    protected void scrollValue(@NotNull ScrollEvent event) {

        if (!event.isControlDown()) {
            return;
        }

        event.consume();

        scrollValueImpl(event);
    }

    /**
     * Scroll the current value.
     *
     * @param event the scroll event.
     */
    protected void scrollValueImpl(@NotNull ScrollEvent event) {
    }

    /**
     * Add a new change listener.
     *
     * @param listener the change listener.
     */
    public void addChangeListener(@NotNull ChangeListener<T> listener) {
        getTypedTextFormatter()
                .valueProperty()
                .addListener(listener);
    }

    /**
     * Gets the typed text formatter.
     *
     * @return the typed text formatter.
     */
    protected @NotNull TextFormatter<T> getTypedTextFormatter() {
        return unsafeCast(getTextFormatter());
    }

    /**
     * Gets the value property.
     *
     * @return the value property.
     */
    public @NotNull ReadOnlyObjectProperty<T> valueProperty() {
        return getTypedTextFormatter().valueProperty();
    }
}
