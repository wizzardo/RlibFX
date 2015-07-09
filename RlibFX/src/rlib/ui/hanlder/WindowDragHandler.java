package rlib.ui.hanlder;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;

/**
 * Реализация обработчика перемещения окна черзе какой-то узел в нем.
 *
 * @author Ronn
 */
public class WindowDragHandler {

    public static final void install(Node node) {
        new WindowDragHandler(node);
    }

    /**
     * Узел через который осуществляется перемещение.
     */
    private final Node node;

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

    public WindowDragHandler(Node node) {
        this.node = node;
        this.node.setOnMousePressed(event -> processStartDrag(event));
        this.node.setOnMouseDragged(event -> processMove(event));
        this.node.setOnMouseReleased(event -> processStopDrag(event));
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

    public Node getNode() {
        return node;
    }

    /**
     * Процесс перемещения.
     */
    protected void processMove(final MouseEvent event) {

        final Node node = getNode();
        final Scene scene = node.getScene();
        final Window window = scene.getWindow();

        final Point2D dragOffset = getDragOffset();

        if (dragOffset == null) {
            return;
        }

        final double dragX = event.getScreenX() - dragOffset.getX();
        final double dragY = event.getScreenY() - getDragOffset().getY();

        final double newXPosition = initX + dragX;
        final double newYPosition = initY + dragY;

        window.setX(newXPosition);
        window.setY(newYPosition);
    }

    /**
     * Подготовка к началу перемещения.
     */
    protected void processStartDrag(final MouseEvent event) {

        final Node node = getNode();
        final Scene scene = node.getScene();
        final Window window = scene.getWindow();

        setInitX(window.getX());
        setInitY(window.getY());
        setDragOffset(new Point2D(event.getScreenX(), event.getScreenY()));
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
