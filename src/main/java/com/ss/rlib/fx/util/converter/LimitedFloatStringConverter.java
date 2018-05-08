package com.ss.rlib.fx.util.converter;

import com.ss.rlib.common.util.StringUtils;
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

    public LimitedFloatStringConverter() {
        this.maxValue = Float.MAX_VALUE;
        this.minValue = Integer.MIN_VALUE;
    }

    @Override
    public @Nullable Float fromString(@Nullable String value) {

        if (StringUtils.isEmpty(value)) {
            return null;
        }

        var result = Float.valueOf(value);

        if (result < minValue) {
            throw new IllegalArgumentException();
        } else if (result > maxValue) {
            throw new IllegalArgumentException();
        }

        return result;
    }

    /**
     * Gets the max value.
     *
     * @return the max value.
     */
    public float getMaxValue() {
        return maxValue;
    }

    /**
     * Sets the max value.
     *
     * @param maxValue the max value.
     */
    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * Gets the min value.
     *
     * @return the min value.
     */
    public float getMinValue() {
        return minValue;
    }

    /**
     * Sets the min value.
     *
     * @param minValue the min value.
     */
    public void setMinValue(float minValue) {
        this.minValue = minValue;
    }


    @Override
    public @Nullable String toString(@Nullable Float value) {

        if (value == null) {
            return StringUtils.EMPTY;
        }

        return Float.toString(value);
    }
}
