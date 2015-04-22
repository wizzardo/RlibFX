package rlib.ui.window.impl;

import static javafx.geometry.Pos.CENTER_RIGHT;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import rlib.ui.hanlder.WindowDragHandler;
import rlib.ui.page.UIPage;
import rlib.ui.util.FXUtils;
import rlib.util.array.Array;

/**
 * Реализация окна без стандратной декорации.
 * 
 * @author Ronn
 */
public class UndecoratedUIWindow extends DefaultUIWindow {

	public UndecoratedUIWindow(final Stage stage, final Array<Class<? extends UIPage>> availablePages) {
		super(stage, availablePages);
	}

	@Override
	protected Stage configureStage(final Stage stage) {
		stage.initStyle(StageStyle.UNDECORATED);
		return super.configureStage(stage);
	}

	@Override
	protected Pane createRoot() {

		final VBox root = new VBox();
		final HBox header = createHeader();

		final Button closeButton = createCloseButton();
		final Button miniminizeButton = createMiniminizeButton();

		if(miniminizeButton != null) {
			FXUtils.addToPane(miniminizeButton, header);
		}

		if(closeButton != null) {
			FXUtils.addToPane(closeButton, header);
		}

		FXUtils.addToPane(header, root);

		if(miniminizeButton != null) {
			applyMarginToMiniminizeButton(miniminizeButton);
		}

		if(closeButton != null) {
			applyMarginToCloseButton(closeButton);
		}

		return root;
	}

	protected void applyMarginToMiniminizeButton(final Button miniminizeButton) {
		HBox.setMargin(miniminizeButton, new Insets(4, 4, 0, 0));
	}

	protected void applyMarginToCloseButton(final Button closeButton) {
		HBox.setMargin(closeButton, new Insets(4, 4, 0, 0));
	}

	protected Button createCloseButton() {

		final Button button = new Button();
		button.setText("Close");
		button.setOnAction(event -> close());

		return button;
	}

	protected Button createMiniminizeButton() {

		final Button button = new Button();
		button.setText("Miniminize");

		return button;
	}

	protected HBox createHeader() {

		final HBox header = new HBox();
		header.setAlignment(CENTER_RIGHT);

		WindowDragHandler.install(header);

		return header;
	}
}
