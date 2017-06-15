package com.ss.rlib.ui.component.impl;

import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;

/**
 * The simple implementation of a draggable element.
 *
 * @author JavaSaBr
 */
public class SimpleDraggableElement extends VBox {

    /**
     * Instantiates a new Simple draggable element.
     */
    public SimpleDraggableElement() {
        setOnDragDetected(this::processStartDragAndDrop);
        setOnDragDone(this::processFinishDragAndDrop);
    }

    /**
     * Get transfer mode transfer mode [ ].
     *
     * @return transfer modes.
     */
    @NotNull
    protected TransferMode[] getTransferMode() {
        return TransferMode.COPY_OR_MOVE;
    }

    /**
     * Prepare data to dragboard.
     *
     * @param dragboard the dragboard
     */
    protected void prepareData(@NotNull final Dragboard dragboard) {
    }

    /**
     * Handle finishing dragging.
     *
     * @param event the event
     */
    protected void processFinishDragAndDrop(@NotNull final DragEvent event) {
        event.consume();
    }

    /**
     * Handle starting dragging.
     *
     * @param event the event
     */
    protected void processStartDragAndDrop(@NotNull final MouseEvent event) {
        prepareData(startDragAndDrop(getTransferMode()));
        event.consume();
    }
}
