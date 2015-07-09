package rlib.ui.window.impl;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import rlib.ui.hanlder.WindowDragHandler;
import rlib.ui.page.UIPage;
import rlib.ui.util.FXUtils;
import rlib.util.array.Array;

import static javafx.geometry.Pos.CENTER_RIGHT;
import static javafx.stage.StageStyle.UNDECORATED;

/**
 * Реализация окна без стандратной декорации.
 *
 * @author Ronn
 */
public class UndecoratedUIWindow extends DefaultUIWindow {

    public UndecoratedUIWindow(final Stage stage, final Array<Class<? extends UIPage>> availablePages) {
        super(stage, availablePages);
    }

    protected void applyMarginToCloseButton(final Button closeButton) {
        HBox.setMargin(closeButton, new Insets(4, 4, 0, 0));
    }

    protected void applyMarginToMinimiseButton(final Button minimiseButton) {
        HBox.setMargin(minimiseButton, new Insets(4, 4, 0, 0));
    }

    @Override
    protected Stage configureStage(final Stage stage) {
        stage.initStyle(UNDECORATED);
        return super.configureStage(stage);
    }

    protected Button createCloseButton() {

        final Button button = new Button();
        button.setText("Close");
        button.setOnAction(event -> close());

        return button;
    }

    protected Pane createHeader() {

        final HBox header = new HBox();
        header.setAlignment(CENTER_RIGHT);

        WindowDragHandler.install(header);

        return header;
    }

    protected Button createMinimiseButton() {

        final Button button = new Button();
        button.setText("Minimise");

        return button;
    }

    @Override
    protected Pane createRoot() {

        final VBox root = new VBox();
        final Pane header = createHeader();

        final Button closeButton = createCloseButton();
        final Button minimiseButton = createMinimiseButton();

        if (minimiseButton != null) {
            FXUtils.addToPane(minimiseButton, header);
        }

        if (closeButton != null) {
            FXUtils.addToPane(closeButton, header);
        }

        FXUtils.addToPane(header, root);

        if (minimiseButton != null) {
            applyMarginToMinimiseButton(minimiseButton);
        }

        if (closeButton != null) {
            applyMarginToCloseButton(closeButton);
        }

        return root;
    }
}
