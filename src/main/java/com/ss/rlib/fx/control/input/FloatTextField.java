package com.ss.rlib.fx.control.input;

import com.ss.rlib.fx.util.converter.LimitedFloatStringConverter;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.ScrollEvent;
import org.jetbrains.annotations.NotNull;

/**
 * The implementation of a text field control to edit float values.
 *
 * @author JavaSaBr
 */
public final class FloatTextField extends TypedTextField<Float> {

    public FloatTextField() {
        super();
        setScrollPower(30);
    }

    public FloatTextField(@NotNull String text) {
        super(text);
        setScrollPower(30);
    }

    @Override
    protected @NotNull TextFormatter<Float> createTextFormatter() {
        return new TextFormatter<>(new LimitedFloatStringConverter());
    }

    @Override
    protected void scrollValueImpl(@NotNull ScrollEvent event) {
        super.scrollValueImpl(event);

        var value = getValue();

        var longValue = (long) (value * 1000);
        longValue += event.getDeltaY() * (getScrollPower() * (event.isShiftDown() ? 0.5F : 1F));

        var resultValue = longValue / 1000F;
        var stringValue = String.valueOf(resultValue);

        var textFormatter = getTextFormatter();
        var valueConverter = textFormatter.getValueConverter();
        try {
            valueConverter.fromString(stringValue);
        } catch (RuntimeException e) {
            return;
        }

        setText(stringValue);
        positionCaret(stringValue.length());
    }


    /**
     * Set value limits for this field.
     *
     * @param min the min value.
     * @param max the max value.
     */
    public void setMinMax(float min, float max) {

        var textFormatter = getTypedTextFormatter();
        var valueConverter = textFormatter.getValueConverter();

        if (valueConverter instanceof LimitedFloatStringConverter) {
            var converter = (LimitedFloatStringConverter) valueConverter;
            converter.setMaxValue(max);
            converter.setMinValue(min);
        }
    }

    /**
     * Gets a current value.
     *
     * @return the current value.
     */
    public float getValue() {
        var textFormatter = getTypedTextFormatter();
        var value = textFormatter.getValue();
        return value == null ? 0F : value;
    }

    /**
     * Sets a new value.
     *
     * @param value the new value.
     */
    public void setValue(float value) {
        setText(String.valueOf(value));
    }
}
