package com.ss.rlib.ui.hanlder;

import com.ss.rlib.logging.Logger;
import com.ss.rlib.logging.LoggerManager;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;
import org.jetbrains.annotations.NotNull;

/**
 * The implementation of a handler to processing windows dragging.
 *
 * @author JavaSaBr
 */
public class WindowDragHandler {

    private static final Logger LOGGER = LoggerManager.getLogger(WindowDragHandler.class);

    /**
     * Install.
     *
     * @param node the node
     */
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

    /**
     * Instantiates a new Window drag handler.
     *
     * @param node the node
     */
    public WindowDragHandler(@NotNull final Node node) {
        this.node = node;
        this.node.setOnMousePressed(this::processStartDrag);
        this.node.setOnMouseDragged(this::processMove);
        this.node.setOnMouseReleased(this::processStopDrag);
    }

    /**
     * Gets drag offset.
     *
     * @return the drag offset.
     */
    @NotNull
    protected Point2D getDragOffset() {
        return dragOffset;
    }

    /**
     * Sets drag offset.
     *
     * @param dragOffset the drag offset.
     */
    public void setDragOffset(@NotNull final Point2D dragOffset) {
        this.dragOffset = dragOffset;
    }

    /**
     * Gets init x.
     *
     * @return the started x coord.
     */
    protected double getInitX() {
        return initX;
    }

    /**
     * Sets init x.
     *
     * @param initX the started x coord.
     */
    public void setInitX(final double initX) {
        this.initX = initX;
    }

    /**
     * Gets init y.
     *
     * @return the started y coord.
     */
    protected double getInitY() {
        return initY;
    }

    /**
     * Sets init y.
     *
     * @param initY the started y coord.
     */
    public void setInitY(final double initY) {
        this.initY = initY;
    }

    /**
     * Gets node.
     *
     * @return the node.
     */
    @NotNull
    public Node getNode() {
        return node;
    }

    /**
     * Handle moving.
     *
     * @param event the event
     */
    protected void processMove(@NotNull final MouseEvent event) {

        LOGGER.debug(event, mouseEvent -> "processMove -> " + mouseEvent);

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
     *
     * @param event the event
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
     *
     * @param event the event
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
