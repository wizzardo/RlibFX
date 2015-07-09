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

    public DefaultUIWindow(final Stage stage, final Array<Class<? extends UIPage>> availablePages) {
        super(stage, availablePages);
    }

}
