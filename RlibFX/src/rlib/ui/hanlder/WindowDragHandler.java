package rlib.ui.hanlder;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;
import rlib.logging.Logger;
import rlib.logging.LoggerManager;

/**
 * Реализация обработчика перемещения окна черзе какой-то узел в нем.
 *
 * @author Ronn
 */
public class WindowDragHandler {

    private static final Logger LOGGER = LoggerManager.getLogger(WindowDragHandler.class);

    public static void install(Node node) {
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

    public WindowDragHandler(final Node node) {
        this.node = node;
        this.node.setOnMousePressed(this::processStartDrag);
        this.node.setOnMouseDragged(this::processMove);
        this.node.setOnMouseReleased(this::processStopDrag);
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

        if (LOGGER.isEnabledDebug()) {
            LOGGER.debug("processMove -> " + event);
        }

        final Node node = getNode();
        final Scene scene = node.getScene();
        final Window window = scene.getWindow();

        final Point2D dragOffset = getDragOffset();

        if (LOGGER.isEnabledDebug()) {
            LOGGER.debug("processMove -> dragOffset -> " + dragOffset);
        }

        if (dragOffset == null) return;

        final double dragX = event.getScreenX() - dragOffset.getX();
        final double dragY = event.getScreenY() - getDragOffset().getY();

        if (LOGGER.isEnabledDebug()) {
            LOGGER.debug("processMove -> dragXY -> " + dragX + ", " + dragY);
        }

        final double newXPosition = initX + dragX;
        final double newYPosition = initY + dragY;

        if (LOGGER.isEnabledDebug()) {
            LOGGER.debug("processMove -> newXY -> " + newXPosition + ", " + newYPosition);
        }

        window.setX(newXPosition);
        window.setY(newYPosition);
    }

    /**
     * Подготовка к началу перемещения.
     */
    protected void processStartDrag(final MouseEvent event) {

        if (LOGGER.isEnabledDebug()) {
            LOGGER.debug("processStartDrag -> " + event);
        }

        final Node node = getNode();
        final Scene scene = node.getScene();
        final Window window = scene.getWindow();

        setInitX(window.getX());
        setInitY(window.getY());

        if (LOGGER.isEnabledDebug()) {
            LOGGER.debug("processStartDrag -> initXY -> " + getInitX() + ", " + getInitY());
        }

        setDragOffset(new Point2D(event.getScreenX(), event.getScreenY()));

        if (LOGGER.isEnabledDebug()) {
            LOGGER.debug("processStartDrag -> dragOffset -> " + getDragOffset());
        }
    }

    /**
     * Процесс завершения перемещения.
     */
    protected void processStopDrag(final MouseEvent event) {

        if (LOGGER.isEnabledDebug()) {
            LOGGER.debug("processStopDrag -> " + event);
        }

        setInitX(0);
        setInitY(0);
        setDragOffset(null);
    }
}
