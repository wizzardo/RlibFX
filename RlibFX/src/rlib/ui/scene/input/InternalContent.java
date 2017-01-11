package rlib.ui.scene.input;

import static rlib.util.ClassUtils.unsafeCast;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.HashMap;

import javafx.scene.input.DataFormat;
import rlib.util.ref.Reference;

/**
 * The implementation of a clipboard/dragboard content which will use only internal.
 *
 * @author JavaSaBr
 */
public class InternalContent extends HashMap<DataFormat, Object> {

    private static final long serialVersionUID = 2133513859087232216L;

    @NotNull
    public static final DataFormat DATA_OBJECT = new DataFormat("internal/object");

    @NotNull
    public static final DataFormat DATA_STRING = new DataFormat("internal/string");

    @NotNull
    public static final DataFormat DATA_NUMBER = new DataFormat("internal/number");

    @NotNull
    public static final DataFormat DATA_REFERENCE = new DataFormat("internal/reference");

    /**
     * @return the number or null.
     */
    @Nullable
    public Number getNumber() {
        return (Number) get(DATA_NUMBER);
    }

    /**
     * @return the object or null.
     */
    @Nullable
    public <T> T getObject() {
        return unsafeCast(get(DATA_OBJECT));
    }

    /**
     * @return the reference or null.
     */
    @Nullable
    public Reference getReference() {
        return (Reference) get(DATA_REFERENCE);
    }

    /**
     * @return the string or null.
     */
    @Nullable
    public String getString() {
        return (String) get(DATA_STRING);
    }

    /**
     * @param number the number or null.
     */
    public void putNumber(@Nullable final Number number) {
        put(DATA_NUMBER, number);
    }

    /**
     * @param object the object or null.
     */
    public void putObject(@Nullable final Object object) {
        put(DATA_OBJECT, object);
    }

    /**
     * @param reference the reference or null.
     */
    public void putReference(@Nullable final Reference reference) {
        put(DATA_REFERENCE, reference);
    }

    /**
     * @param string the string or null.
     */
    public void putString(@Nullable final String string) {
        put(DATA_STRING, string);
    }
}
