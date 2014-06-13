package rlib.ui.window.impl;

import javafx.stage.Stage;
import rlib.ui.page.UIPage;
import rlib.util.array.Array;

/**
 * Стандартная реализация окна JavaFX.
 * 
 * @author Ronn
 */
public class DefaultUIWindow extends AbstractUIWindow {

	public DefaultUIWindow(Stage stage, Array<Class<? extends UIPage>> availablePages) {
		super(stage, availablePages);
	}

}
