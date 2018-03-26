package com.ss.rlib.ui.hanlder;

import com.ss.rlib.logging.Logger;
import com.ss.rlib.logging.LoggerLevel;
import com.ss.rlib.logging.LoggerManager;
import com.ss.rlib.ui.RLib;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The implementation of a handler to process windows dragging.
 *
 * @author JavaSaBr
 */
public class WindowDragHandler {

    private static final Logger LOGGER = LoggerManager.getLogger(RLib.class);

    /**
     * Install the handler to the node.
     *
     * @param node the node.
     */
    public static void install(@NotNull final Node node) {
        new WindowDragHandler(node);
    }

    /**
     * The node.
     */
    @NotNull
    private final Node node;

    /**
     * The started X coord.
     */
    private double startX;

    /**
     * The started Y coord.
     */
    private double startY;

    /**
     * The drag offset.
     */
    @Nullable
    private Point2D dragOffset;

    public WindowDragHandler(@NotNull final Node node) {
        this.node = node;
        this.node.setOnMousePressed(this::processStartDrag);
        this.node.setOnMouseDragged(this::processMove);
        this.node.setOnMouseReleased(this::processStopDrag);
    }

    /**
     * Get the drag offset.
     *
     * @return the drag offset.
     */
    protected @Nullable Point2D getDragOffset() {
        return dragOffset;
    }

    /**
     * Set the drag offset.
     *
     * @param dragOffset the drag offset.
     */
    public void setDragOffset(@Nullable final Point2D dragOffset) {
        this.dragOffset = dragOffset;
    }

    /**
     * Get the start X coord.
     *
     * @return the start X coord.
     */
    protected double getStartX() {
        return startX;
    }

    /**
     * Set the start X coord.
     *
     * @param startX the start X coord.
     */
    public void setStartX(final double startX) {
        this.startX = startX;
    }

    /**
     * Get start Y coord.
     *
     * @return the start Y coord.
     */
    protected double getStartY() {
        return startY;
    }

    /**
     * Set the start Y coord.
     *
     * @param startY the start Y coord.
     */
    public void setStartY(final double startY) {
        this.startY = startY;
    }

    /**
     * Get the handled node.
     *
     * @return the handled node.
     */
    public @NotNull Node getNode() {
        return node;
    }

    /**
     * Handle moving.
     *
     * @param event the event
     */
    protected void processMove(@NotNull final MouseEvent event) {

        LOGGER.debug(this, event, ev -> "processMove -> " + ev);

        final Node node = getNode();
        final Scene scene = node.getScene();
        final Window window = scene.getWindow();

        final Point2D dragOffset = getDragOffset();
        if (dragOffset == null) {
            LOGGER.debug(this, "processMove -> dragOffset -> null");
            return;
        }

        LOGGER.debug(this, dragOffset, offset -> "processMove -> dragOffset -> " + offset);

        final double dragX = event.getScreenX() - dragOffset.getX();
        final double dragY = event.getScreenY() - dragOffset.getY();

        LOGGER.debug(this, dragOffset, event, (offset, ev) ->
                "processMove -> dragXY -> " + (ev.getScreenX() - offset.getX()) + "-" + (ev.getScreenY() - offset.getY()));

        final double newXPos = startX + dragX;
        final double newYPos = startY + dragY;

        if (LOGGER.isEnabled(LoggerLevel.DEBUG)) {
            LOGGER.debug(this, "processMove -> newXY -> " + newXPos + ", " + newYPos);
        }

        window.setX(newXPos);
        window.setY(newYPos);
    }

    /**
     * Handle starting moving.
     *
     * @param event the event.
     */
    protected void processStartDrag(@NotNull final MouseEvent event) {

        LOGGER.debug(this, event, ev -> "processStartDrag -> " + ev);

        final Node node = getNode();
        final Scene scene = node.getScene();
        final Window window = scene.getWindow();

        setStartX(window.getX());
        setStartY(window.getY());

        LOGGER.debug(this, this, handler ->
                "processStartDrag -> initXY -> " + handler.getStartX() + ", " + handler.getStartY());

        final Point2D dragOffset = new Point2D(event.getScreenX(), event.getScreenY());

        LOGGER.debug(this, dragOffset, offset -> "processStartDrag -> dragOffset -> " + offset);

        setDragOffset(dragOffset);
    }

    /**
     * Handle finishing moving.
     *
     * @param event the event
     */
    protected void processStopDrag(@NotNull final MouseEvent event) {
        LOGGER.debug(this, event, ev -> "processStopDrag -> " + ev);
        setStartX(0);
        setStartY(0);
        setDragOffset(null);
    }
}
