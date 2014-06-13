package rlib.ui.window;

import rlib.ui.page.UIPage;
import rlib.util.array.Array;

/**
 * Фабрика {@link UIWindow}.
 * 
 * @author Ronn
 */
public final class UIWindowFactory {

	private UIWindowFactory() {
		throw new RuntimeException();
	}

	public static final UIWindow create(Array<Class<? extends UIPage>> pages) {
		return null;
	}
}
