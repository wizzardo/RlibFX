package com.ss.rlib.fx.util.converter;

import com.ss.rlib.common.util.StringUtils;
import javafx.util.StringConverter;
import org.jetbrains.annotations.Nullable;

/**
 * The implementation of limited integer string converter.
 *
 * @author JavaSaBR
 */
public class LimitedIntegerStringConverter extends StringConverter<Integer> {

    /**
     * The min value.
     */
    private int minValue;

    /**
     * The max value.
     */
    private int maxValue;

    public LimitedIntegerStringConverter() {
        this.maxValue = Integer.MAX_VALUE;
        this.minValue = Integer.MIN_VALUE;
    }

    @Override
    public @Nullable Integer fromString(@Nullable String value) {

        if (StringUtils.isEmpty(value)) {
            return null;
        }

        var result = Integer.valueOf(value);

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
    public int getMaxValue() {
        return maxValue;
    }

    /**
     * Sets the max value.
     *
     * @param maxValue the max value.
     */
    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * Gets the min value.
     *
     * @return the min value.
     */
    public int getMinValue() {
        return minValue;
    }

    /**
     * Sets the min value.
     *
     * @param minValue the min value.
     */
    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    @Override
    public @Nullable String toString(@Nullable Integer value) {

        if (value == null) {
            return StringUtils.EMPTY;
        }

        return Integer.toString(value);
    }
}
