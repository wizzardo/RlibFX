package rlib.ui.component;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import rlib.ui.component.impl.DraggablePanel;
import rlib.ui.util.FXUtils;

/**
 * @author Ronn
 */
public class TestWindowComponent extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Pane pane = new Pane();

		Scene scene = new Scene(pane, 600, 400);

		primaryStage.setScene(scene);
		primaryStage.show();

		DraggablePanel graggablePanel = new DraggablePanel();
		graggablePanel.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));

		FXUtils.setFixedSize(graggablePanel, 100, 100);
		FXUtils.addToPane(graggablePanel, pane);

		final Text source = new Text(50, 100, "DRAG ME");
		final Text target = new Text(300, 100, "DROP HERE");

		source.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				/* drag was detected, start a drag-and-drop gesture */
				/* allow any transfer mode */
				Dragboard db = source.startDragAndDrop(TransferMode.ANY);

				/* Put a string on a dragboard */
				ClipboardContent content = new ClipboardContent();
				content.putString(source.getText());
				db.setContent(content);

				event.consume();

				System.out.println("setOnDragDetected");
			}
		});

		target.setOnDragOver(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				/* data is dragged over the target */
				/*
				 * accept it only if it is not dragged from the same node and if
				 * it has a string data
				 */
				if(event.getGestureSource() != target && event.getDragboard().hasString()) {
					/* allow for both copying and moving, whatever user chooses */
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}

				event.consume();

				System.out.println("setOnDragOver");
			}
		});

		target.setOnDragEntered(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				/* the drag-and-drop gesture entered the target */
				/* show to the user that it is an actual gesture target */
				if(event.getGestureSource() != target && event.getDragboard().hasString()) {
					target.setFill(Color.GREEN);
				}

				event.consume();
				System.out.println("setOnDragEntered");
			}
		});

		target.setOnDragExited(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				/* mouse moved away, remove the graphical cues */
				target.setFill(Color.BLACK);

				event.consume();
				System.out.println("setOnDragExited");
			}
		});
		//
		target.setOnDragDropped(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				/* data dropped */
				/* if there is a string data on dragboard, read it and use it */
				Dragboard db = event.getDragboard();
				boolean success = false;
				if(db.hasString()) {
					target.setText(db.getString());
					success = true;
				}
				/*
				 * let the source know whether the string was successfully
				 * transferred and used
				 */
				event.setDropCompleted(success);

				event.consume();
				System.out.println("setOnDragDropped");
			}
		});

		source.setOnDragDone(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				/* the drag and drop gesture ended */
				/* if the data was successfully moved, clear it */
				if(event.getTransferMode() == TransferMode.MOVE) {
					source.setText("");
				}
				event.consume();
			}
		});

		FXUtils.addToPane(source, pane);
		FXUtils.addToPane(target, pane);
	}
}
