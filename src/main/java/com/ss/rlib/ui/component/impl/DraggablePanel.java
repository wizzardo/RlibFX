package com.ss.rlib.ui.component.impl;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The implementation of a draggable panel.
 *
 * @author JavaSaBr
 */
public class DraggablePanel extends VBox {

    /**
     * The drag offset.
     */
    @Nullable
    private Point2D dragOffset;

    /**
     * Started x coord.
     */
    private double initX;

    /**
     * Started y coord.
     */
    private double initY;

    /**
     * Instantiates a new Draggable panel.
     */
    public DraggablePanel() {
        setOnMousePressed(this::processStartDrag);
        setOnMouseDragged(this::processMove);
        setOnMouseReleased(this::processStopDrag);
    }

    /**
     * Gets drag offset.
     *
     * @return the drag offset.
     */
    @Nullable
    protected Point2D getDragOffset() {
        return dragOffset;
    }

    /**
     * Sets drag offset.
     *
     * @param dragOffset the drag offset.
     */
    public void setDragOffset(@Nullable final Point2D dragOffset) {
        this.dragOffset = dragOffset;
    }

    /**
     * Gets init x.
     *
     * @return started x coord.
     */
    protected double getInitX() {
        return initX;
    }

    /**
     * Sets init x.
     *
     * @param initX started x coord.
     */
    public void setInitX(final double initX) {
        this.initX = initX;
    }

    /**
     * Gets init y.
     *
     * @return started y coord.
     */
    protected double getInitY() {
        return initY;
    }

    /**
     * Sets init y.
     *
     * @param initY started y coord.
     */
    public void setInitY(final double initY) {
        this.initY = initY;
    }

    /**
     * Handle moving.
     *
     * @param event the event
     */
    protected void processMove(@NotNull final MouseEvent event) {

        final Point2D dragOffset = getDragOffset();
        if (dragOffset == null) return;

        final double dragX = event.getSceneX() - dragOffset.getX();
        final double dragY = event.getSceneY() - getDragOffset().getY();

        final double newXPosition = initX + dragX;
        final double newYPosition = initY + dragY;

        setTranslateX(newXPosition);
        setTranslateY(newYPosition);
    }

    /**
     * Handle starting dragging.
     *
     * @param event the event
     */
    protected void processStartDrag(@NotNull final MouseEvent event) {
        setInitX(getTranslateX());
        setInitY(getTranslateY());
        setDragOffset(new Point2D(event.getSceneX(), event.getSceneY()));
        toFront();
    }

    /**
     * Handle finishing dragging.
     *
     * @param event the event
     */
    protected void processStopDrag(@NotNull final MouseEvent event) {
        setInitX(0);
        setInitY(0);
        setDragOffset(null);
    }
}
