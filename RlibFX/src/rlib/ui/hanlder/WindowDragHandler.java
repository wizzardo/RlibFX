package rlib.ui.hanlder;

import com.sun.istack.internal.NotNull;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;
import rlib.logging.Logger;
import rlib.logging.LoggerManager;

/**
 * The implementation of a handler to processing windows dragging.
 *
 * @author JavaSaBr
 */
public class WindowDragHandler {

    private static final Logger LOGGER = LoggerManager.getLogger(WindowDragHandler.class);

    public static void install(@NotNull final Node node) {
        new WindowDragHandler(node);
    }

    /**
     * The node.
     */
    private final Node node;

    /**
     * The started x coord.
     */
    private double initX;

    /**
     * The started y coord.
     */
    private double initY;

    /**
     * The drag offset.
     */
    private Point2D dragOffset;

    public WindowDragHandler(@NotNull final Node node) {
        this.node = node;
        this.node.setOnMousePressed(this::processStartDrag);
        this.node.setOnMouseDragged(this::processMove);
        this.node.setOnMouseReleased(this::processStopDrag);
    }

    /**
     * @return the drag offset.
     */
    @NotNull
    protected Point2D getDragOffset() {
        return dragOffset;
    }

    /**
     * @param dragOffset the drag offset.
     */
    public void setDragOffset(@NotNull final Point2D dragOffset) {
        this.dragOffset = dragOffset;
    }

    /**
     * @return the started x coord.
     */
    protected double getInitX() {
        return initX;
    }

    /**
     * @param initX the started x coord.
     */
    public void setInitX(final double initX) {
        this.initX = initX;
    }

    /**
     * @return the started y coord.
     */
    protected double getInitY() {
        return initY;
    }

    /**
     * @param initY the started y coord.
     */
    public void setInitY(final double initY) {
        this.initY = initY;
    }

    /**
     * @return the node.
     */
    @NotNull
    public Node getNode() {
        return node;
    }

    /**
     * Handle moving.
     */
    protected void processMove(@NotNull final MouseEvent event) {

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
     * Handle starting moving.
     */
    protected void processStartDrag(@NotNull final MouseEvent event) {

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
     * Handle finishing moving.
     */
    protected void processStopDrag(@NotNull final MouseEvent event) {

        if (LOGGER.isEnabledDebug()) {
            LOGGER.debug("processStopDrag -> " + event);
        }

        setInitX(0);
        setInitY(0);
        setDragOffset(null);
    }
}
