package rlib.ui.component.impl;

import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

/**
 * Реализация простой панельки с перемещаемым контентом.
 *
 * @author Ronn
 */
public class SimpleDraggablePanel extends Pane {

    public SimpleDraggablePanel() {
        setOnDragDetected(this::processStartDragAndDrop);
        setOnDragDone(this::processFinishDragAndDrop);
    }

    protected TransferMode[] getTransferMode() {
        return TransferMode.COPY_OR_MOVE;
    }

    /**
     * Процесс внесения данных в буффер перемещения.
     */
    protected void prepareData(final Dragboard dragboard) {
    }

    /**
     * Процесс завершения перемещения объекта.
     */
    protected void processFinishDragAndDrop(final DragEvent event) {
        event.consume();
    }

    /**
     * Процесс старта перемещения объекта.
     */
    protected void processStartDragAndDrop(final MouseEvent event) {
        prepareData(startDragAndDrop(getTransferMode()));
        event.consume();
    }
}
