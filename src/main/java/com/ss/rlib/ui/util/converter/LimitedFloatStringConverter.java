package com.ss.rlib.ui.util.converter;

import com.ss.rlib.util.StringUtils;
import javafx.util.StringConverter;
import org.jetbrains.annotations.Nullable;

/**
 * The implementation of limited float string converter.
 *
 * @author JavaSaBr
 */
public class LimitedFloatStringConverter extends StringConverter<Float> {

    /**
     * The min value.
     */
    private float minValue;

    /**
     * The max value.
     */
    private float maxValue;

    /**
     * Instantiates a new Limited float string converter.
     */
    public LimitedFloatStringConverter() {
        this.maxValue = Float.MAX_VALUE;
        this.minValue = Integer.MIN_VALUE;
    }

    @Nullable
    @Override
    public Float fromString(@Nullable final String value) {
        if (StringUtils.isEmpty(value)) return null;

        final Float result = Float.valueOf(value);

        if (result < minValue) {
            throw new IllegalArgumentException();
        } else if (result > maxValue) {
            throw new IllegalArgumentException();
        }

        return result;
    }

    /**
     * Gets max value.
     *
     * @return the max value.
     */
    public float getMaxValue() {
        return maxValue;
    }

    /**
     * Sets max value.
     *
     * @param maxValue the max value.
     */
    public void setMaxValue(final float maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * Gets min value.
     *
     * @return the min value.
     */
    public float getMinValue() {
        return minValue;
    }

    /**
     * Sets min value.
     *
     * @param minValue the min value.
     */
    public void setMinValue(final float minValue) {
        this.minValue = minValue;
    }

    @Nullable
    @Override
    public String toString(@Nullable final Float value) {
        if (value == null) return StringUtils.EMPTY;
        return Float.toString(value.floatValue());
    }
}
