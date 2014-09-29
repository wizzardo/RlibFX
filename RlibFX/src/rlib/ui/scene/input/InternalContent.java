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

	public void putObject(Object object) {
		put(DATA_OBJECT, object);
	}

	public void putString(String string) {
		put(DATA_STRING, string);
	}

	public void putNumber(Number number) {
		put(DATA_NUMBER, number);
	}

	public void putReference(Reference reference) {
		put(DATA_REFERENCE, reference);
	}

	@SuppressWarnings("unchecked")
	public <T> T getObject() {
		return (T) get(DATA_OBJECT);
	}

	public String getString() {
		return (String) get(DATA_STRING);
	}

	public Number getNumber() {
		return (Number) get(DATA_NUMBER);
	}

	public Reference getReference() {
		return (Reference) get(DATA_REFERENCE);
	}
}
