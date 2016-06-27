package rlib.ui.scene.input;

import java.util.HashMap;

import javafx.scene.input.DataFormat;
import rlib.util.ref.Reference;

/**
 * Реализация внутреннего контента для хранения данных перемещаемых объектов.
 *
 * @author Ronn
 */
public class InternalContent extends HashMap<DataFormat, Object> {

    private static final long serialVersionUID = 2133513859087232216L;

    public static final DataFormat DATA_OBJECT = new DataFormat("internal/object");
    public static final DataFormat DATA_STRING = new DataFormat("internal/string");
    public static final DataFormat DATA_NUMBER = new DataFormat("internal/number");
    public static final DataFormat DATA_REFERENCE = new DataFormat("internal/reference");

    public Number getNumber() {
        return (Number) get(DATA_NUMBER);
    }

    @SuppressWarnings("unchecked")
    public <T> T getObject() {
        return (T) get(DATA_OBJECT);
    }

    public Reference getReference() {
        return (Reference) get(DATA_REFERENCE);
    }

    public String getString() {
        return (String) get(DATA_STRING);
    }

    public void putNumber(final Number number) {
        put(DATA_NUMBER, number);
    }

    public void putObject(final Object object) {
        put(DATA_OBJECT, object);
    }

    public void putReference(final Reference reference) {
        put(DATA_REFERENCE, reference);
    }

    public void putString(final String string) {
        put(DATA_STRING, string);
    }
}
