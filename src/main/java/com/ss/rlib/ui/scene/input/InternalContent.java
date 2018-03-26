package com.ss.rlib.ui.scene.input;

import static com.ss.rlib.util.ClassUtils.unsafeCast;
import com.ss.rlib.util.ref.Reference;
import javafx.scene.input.DataFormat;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

/**
 * The implementation of a clipboard/dragboard content which will use only internal.
 *
 * @author JavaSaBr
 */
public class InternalContent extends HashMap<DataFormat, Object> {

    private static final long serialVersionUID = 2133513859087232216L;

    /**
     * The constant DATA_OBJECT.
     */
    @NotNull
    public static final DataFormat DATA_OBJECT = new DataFormat("internal/object");

    /**
     * The constant DATA_STRING.
     */
    @NotNull
    public static final DataFormat DATA_STRING = new DataFormat("internal/string");

    /**
     * The constant DATA_NUMBER.
     */
    @NotNull
    public static final DataFormat DATA_NUMBER = new DataFormat("internal/number");

    /**
     * The constant DATA_REFERENCE.
     */
    @NotNull
    public static final DataFormat DATA_REFERENCE = new DataFormat("internal/reference");

    /**
     * Get a number.
     *
     * @return the number or null.
     */
    public @Nullable Number getNumber() {
        return (Number) get(DATA_NUMBER);
    }

    /**
     * Get an object.
     *
     * @param <T> the object's type.
     * @return the object or null.
     */
    public <T> @Nullable T getObject() {
        return unsafeCast(get(DATA_OBJECT));
    }

    /**
     * Get a reference.
     *
     * @return the reference or null.
     */
    public @Nullable Reference getReference() {
        return (Reference) get(DATA_REFERENCE);
    }

    /**
     * Get a string.
     *
     * @return the string or null.
     */
    public @Nullable String getString() {
        return (String) get(DATA_STRING);
    }

    /**
     * Put a number.
     *
     * @param number the number or null.
     */
    public void putNumber(@Nullable final Number number) {
        put(DATA_NUMBER, number);
    }

    /**
     * Put an object.
     *
     * @param object the object or null.
     */
    public void putObject(@Nullable final Object object) {
        put(DATA_OBJECT, object);
    }

    /**
     * Put a reference.
     *
     * @param reference the reference or null.
     */
    public void putReference(@Nullable final Reference reference) {
        put(DATA_REFERENCE, reference);
    }

    /**
     * Put a string.
     *
     * @param string the string or null.
     */
    public void putString(@Nullable final String string) {
        put(DATA_STRING, string);
    }
}
