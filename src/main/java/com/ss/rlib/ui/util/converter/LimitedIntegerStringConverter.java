package com.ss.rlib.ui.util.converter;

import com.ss.rlib.util.StringUtils;
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

    /**
     * Instantiates a new Limited integer string converter.
     */
    public LimitedIntegerStringConverter() {
        this.maxValue = Integer.MAX_VALUE;
        this.minValue = Integer.MIN_VALUE;
    }

    @Nullable
    @Override
    public Integer fromString(@Nullable final String value) {
        if (StringUtils.isEmpty(value)) return null;

        final Integer result = Integer.valueOf(value);

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
    public int getMaxValue() {
        return maxValue;
    }

    /**
     * Sets max value.
     *
     * @param maxValue the max value.
     */
    public void setMaxValue(final int maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * Gets min value.
     *
     * @return the min value.
     */
    public int getMinValue() {
        return minValue;
    }

    /**
     * Sets min value.
     *
     * @param minValue the min value.
     */
    public void setMinValue(final int minValue) {
        this.minValue = minValue;
    }

    @Nullable
    @Override
    public String toString(@Nullable final Integer value) {
        if (value == null) return StringUtils.EMPTY;
        return Integer.toString(value.intValue());
    }
}
