package rlib.ui.component.impl;

import com.sun.istack.internal.NotNull;

import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;

/**
 * The simple implementation of a draggable element.
 *
 * @author JavaSaBr
 */
public class SimpleDraggableElement extends VBox {

    public SimpleDraggableElement() {
        setOnDragDetected(this::processStartDragAndDrop);
        setOnDragDone(this::processFinishDragAndDrop);
    }

    /**
     * @return transfer modes.
     */
    @NotNull
    protected TransferMode[] getTransferMode() {
        return TransferMode.COPY_OR_MOVE;
    }

    /**
     * Prepare data to dragboard.
     */
    protected void prepareData(@NotNull final Dragboard dragboard) {
    }

    /**
     * Handle finishing dragging.
     */
    protected void processFinishDragAndDrop(@NotNull final DragEvent event) {
        event.consume();
    }

    /**
     * Handle starting dragging.
     */
    protected void processStartDragAndDrop(@NotNull final MouseEvent event) {
        prepareData(startDragAndDrop(getTransferMode()));
        event.consume();
    }
}
