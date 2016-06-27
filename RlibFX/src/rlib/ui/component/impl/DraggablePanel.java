package rlib.ui.component.impl;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * Реализация перемещаемой панели.
 *
 * @author Ronn
 */
public class DraggablePanel extends Pane {

    /**
     * Положение перед началом перемещения по X.
     */
    private double initX;

    /**
     * Положение перед началом перемещения по Y.
     */
    private double initY;

    /**
     * Место в котором был клик перед началом перемещения.
     */
    private Point2D dragOffset;

    public DraggablePanel() {
        setOnMousePressed(this::processStartDrag);
        setOnMouseDragged(this::processMove);
        setOnMouseReleased(this::processStopDrag);
    }

    /**
     * @return место в котором был клик перед началом перемещения.
     */
    protected Point2D getDragOffset() {
        return dragOffset;
    }

    /**
     * @param dragOffset место в котором был клик перед началом перемещения.
     */
    public void setDragOffset(final Point2D dragOffset) {
        this.dragOffset = dragOffset;
    }

    /**
     * @return положение перед началом перемещения по X.
     */
    protected double getInitX() {
        return initX;
    }

    /**
     * @param initX положение перед началом перемещения по X.
     */
    public void setInitX(final double initX) {
        this.initX = initX;
    }

    /**
     * @return положение перед началом перемещения по Y.
     */
    protected double getInitY() {
        return initY;
    }

    /**
     * @param initY положение перед началом перемещения по Y.
     */
    public void setInitY(final double initY) {
        this.initY = initY;
    }

    /**
     * Процесс перемещения.
     */
    protected void processMove(final MouseEvent event) {

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
     * Подготовка к началу перемещения.
     */
    protected void processStartDrag(final MouseEvent event) {
        setInitX(getTranslateX());
        setInitY(getTranslateY());
        setDragOffset(new Point2D(event.getSceneX(), event.getSceneY()));
        toFront();
    }

    /**
     * Процесс завершения перемещения.
     */
    protected void processStopDrag(final MouseEvent event) {
        setInitX(0);
        setInitY(0);
        setDragOffset(null);
    }
}
