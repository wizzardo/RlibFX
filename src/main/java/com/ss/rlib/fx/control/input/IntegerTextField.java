package com.ss.rlib.fx.control.input;

import com.ss.rlib.fx.util.converter.LimitedIntegerStringConverter;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.ScrollEvent;
import org.jetbrains.annotations.NotNull;

/**
 * The implementation of a text field control to edit integer values.
 *
 * @author JavaSaBr
 */
public final class IntegerTextField extends TypedTextField<Integer> {


    public IntegerTextField() {
        setScrollPower(30);
    }

    public IntegerTextField(@NotNull String text) {
        super(text);
        setScrollPower(30);
    }

    @Override
    protected @NotNull TextFormatter<Integer> createTextFormatter() {
        return new TextFormatter<>(new LimitedIntegerStringConverter());
    }

    @Override
    protected void scrollValueImpl(@NotNull ScrollEvent event) {
        super.scrollValueImpl(event);

        var value = getValue();

        var longValue = (long) (value * 1000);
        longValue += event.getDeltaY() * (getScrollPower() * (event.isShiftDown() ? 0.5F : 1F));

        var resultValue = (int) (longValue / 1000F);
        var stringValue = String.valueOf(resultValue);

        var textFormatter = getTextFormatter();
        var valueConverter = textFormatter.getValueConverter();
        try {
            valueConverter.fromString(stringValue);
        } catch (final RuntimeException e) {
            return;
        }

        setText(stringValue);
        positionCaret(stringValue.length());
    }

    /**
     * Sets value limits for this field.
     *
     * @param min the min value.
     * @param max thr max value.
     */
    public void setMinMax(int min, int max) {
        var valueConverter = getTypedTextFormatter().getValueConverter();
        if (valueConverter instanceof LimitedIntegerStringConverter) {
            var converter = (LimitedIntegerStringConverter) valueConverter;
            converter.setMaxValue(max);
            converter.setMinValue(min);
        }
    }

    /**
     * Gets a current value.
     *
     * @return the current value.
     */
    public int getValue() {

        var textFormatter = getTypedTextFormatter();
        var valueConverter = textFormatter.getValueConverter();
        var value = textFormatter.getValue();

        if (value == null && valueConverter instanceof LimitedIntegerStringConverter) {
            return ((LimitedIntegerStringConverter) valueConverter).getMinValue();
        }

        return value == null ? 0 : value;
    }

    /**
     * Sets a new value.
     *
     * @param value the new value.
     */
    public void setValue(final int value) {
        setText(String.valueOf(value));
    }
}
