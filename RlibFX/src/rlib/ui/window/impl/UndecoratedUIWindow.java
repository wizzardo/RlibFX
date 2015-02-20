package rlib.ui.window.impl;

import static javafx.geometry.Pos.CENTER_RIGHT;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
	protected Stage configureStage(Stage stage) {
		stage.initStyle(StageStyle.UNDECORATED);
		return super.configureStage(stage);
	}

	@Override
	protected Pane createRoot() {

		VBox root = new VBox();

		HBox header = createHeader();

		Button closeButton = createCloseButton();
		Button miniminizeButton = createMiniminizeButton();

		FXUtils.addToPane(miniminizeButton, header);
		FXUtils.addToPane(closeButton, header);
		FXUtils.addToPane(header, root);

		applyMarginToCloseButton(closeButton);
		applyMarginToMiniminizeButton(miniminizeButton);

		return root;
	}

	protected void applyMarginToMiniminizeButton(Button miniminizeButton) {
		HBox.setMargin(miniminizeButton, new Insets(4, 4, 0, 0));
	}

	protected void applyMarginToCloseButton(Button closeButton) {
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

		return header;
	}
}
