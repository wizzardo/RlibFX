package rlib.ui.component.impl;

import org.jetbrains.annotations.NotNull;

import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

/**
 * The simple implementation of droppable element.
 *
 * @author JavaSaBr
 */
public class SimpleDroppedPanel extends Pane {

    public SimpleDroppedPanel() {
        setOnDragOver(this::processOnDragOver);
        setOnDragEntered(this::processOnDragEntered);
        setOnDragExited(this::processOnDragExited);
        setOnDragDropped(this::processOnDragDropped);
    }

    /**
     * Accept a transfer mode.
     */
    protected void acceptTransfersMode(@NotNull final DragEvent event) {
        event.acceptTransferModes(TransferMode.COPY);
    }

    /**
     * Handle finishing dropping.
     */
    protected void processOnDragDropped(@NotNull final DragEvent event) {
        event.setDropCompleted(true);
        event.consume();
    }

    /**
     * Handle entering to a drop area.
     */
    protected void processOnDragEntered(@NotNull final DragEvent event) {
        event.consume();
    }

    /**
     * Handle exiting from a drop area.
     */
    protected void processOnDragExited(@NotNull final DragEvent event) {
        event.consume();
    }

    /**
     * Handle dragging over element.
     */
    protected void processOnDragOver(@NotNull final DragEvent event) {

        final Object source = event.getGestureSource();
        if (source != this) acceptTransfersMode(event);

        event.consume();
    }
}
