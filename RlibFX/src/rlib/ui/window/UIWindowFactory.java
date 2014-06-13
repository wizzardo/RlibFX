package rlib.ui.window;

import javafx.stage.Stage;
import rlib.ui.page.UIPage;
import rlib.ui.window.impl.DefaultUIWindow;
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

	public static final UIWindow create(Stage stage, Array<Class<? extends UIPage>> pages) {
		return new DefaultUIWindow(stage, pages);
	}
}
