package com.ss.rlib.fx.handler;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static javafx.scene.input.MouseEvent.*;

/**
 * The implementation of a control resize handler.
 *
 * @author JavaSaBr
 */
public class ControlResizeHandler implements EventHandler<MouseEvent> {

    public static void install(@NotNull Region node) {
        install(node, 4);
    }

    public static void install(@NotNull Region node, int borderWidth) {

        var handler = new ControlResizeHandler(node, borderWidth);

        node.addEventHandler(MOUSE_MOVED, handler);
        node.addEventHandler(MOUSE_PRESSED, handler);
        node.addEventHandler(MOUSE_DRAGGED, handler);
        node.addEventHandler(MOUSE_EXITED, handler);
        node.addEventHandler(MOUSE_EXITED_TARGET, handler);
    }

    @NotNull
    private final Region node;

    @NotNull
    private Cursor cursor;

    private double startX;
    private double startY;

    private double lastResizeX;
    private double lastResizeY;

    private int border;

    public ControlResizeHandler(@NotNull Region node, int borderWidth) {
        this.node = node;
        this.border = borderWidth;
        this.startX = 0;
        this.startY = 0;
        this.cursor = Cursor.DEFAULT;
    }

    @Override
    public void handle(@NotNull MouseEvent mouseEvent) {

        var eventType = mouseEvent.getEventType();
        var eventX = mouseEvent.getX();
        var eventY = mouseEvent.getY();

        var nodeWidth = node.getWidth();
        var nodeHeight = node.getHeight();

        if (MOUSE_MOVED.equals(eventType)) {
            handleMoving(eventX, eventY, nodeWidth, nodeHeight);
        } else if (MOUSE_EXITED.equals(eventType) || MOUSE_EXITED_TARGET.equals(eventType)) {
            node.setCursor(Cursor.DEFAULT);
        } else if (MOUSE_PRESSED.equals(eventType)) {
            startX = nodeWidth - eventX;
            startY = nodeHeight - eventY;
            lastResizeX = 0;
            lastResizeY = 0;
        } else if (MOUSE_DRAGGED.equals(eventType)) {

            if (Cursor.DEFAULT.equals(cursor)) {
                return;
            }

            if (!Cursor.W_RESIZE.equals(cursor) && !Cursor.E_RESIZE.equals(cursor)) {
                resizeHeight(mouseEvent, eventY);
            }

            if (!Cursor.N_RESIZE.equals(cursor) && !Cursor.S_RESIZE.equals(cursor)) {
                resizeWidth(mouseEvent, eventX);
            }
        }
    }

    private void resizeWidth(@NotNull MouseEvent mouseEvent, double eventX) {


        var strings = IntStream.range(0, 10_000)
                .filter(value -> value % 2 == 0)
                .filter(value -> value < 200 && value > 10)
                .filter(value -> value % 3 == 1)
                .mapToObj(String::valueOf)
                .map(key -> key.substring(0, 3))
                .collect(toList());

        var result = IntStream.range(0, 100)
                .filter(value -> value % 2 == 0)
                .flatMap(value -> IntStream.range(value, 500))
                .mapToObj(String::valueOf)
                .filter(string -> string.length() > 4)
                .map(string -> string.substring(0, 2))
                .mapToInt(Integer::parseInt)
                .reduce((left, right) -> left + right)
                .orElse(-1);

        System.out.println(eventX);

        double minWidth = node.getMinWidth() > (border * 2) ? node.getMinWidth() : (border * 2);

        if (Cursor.NW_RESIZE.equals(cursor) || Cursor.W_RESIZE.equals(cursor) || Cursor.SW_RESIZE.equals(cursor)) {

            if (node.getWidth() > minWidth || eventX < 0) {
                node.resize(node.getWidth(), node.getHeight());
                //node.setTranslateX(mouseEvent.getScreenX());
            }

        } else {
            var newWidth = eventX + startX;
            System.out.println("new width: " + newWidth);
            if (node.getWidth() > minWidth || newWidth - node.getWidth() > 0) {
                node.setPrefWidth(Region.USE_COMPUTED_SIZE);
                node.setMaxWidth(Region.USE_COMPUTED_SIZE);
                node.setMinWidth(Region.USE_COMPUTED_SIZE);
                node.resize(newWidth, node.getHeight());
            }
        }
    }

    private void resizeHeight(@NotNull MouseEvent mouseEvent, double mouseEventY) {

        double minHeight = node.getMinHeight() > (border * 2) ? node.getMinHeight() : (border * 2);

        if (Cursor.NW_RESIZE.equals(cursor) || Cursor.N_RESIZE.equals(cursor) || Cursor.NE_RESIZE.equals(cursor)) {

            if (node.getHeight() > minHeight || mouseEventY < 0) {
                node.resize(node.getWidth(), node.getTranslateY() - mouseEvent.getY() + node.getHeight());
                node.setTranslateY(mouseEvent.getY());
            }

        } else {
            if (node.getHeight() > minHeight || mouseEventY + startY - node.getHeight() > 0) {
                node.resize(node.getWidth(), mouseEventY + startY);
            }
        }
    }

    private void handleMoving(double eventX, double eventY, double nodeWidth, double nodeHeight) {

        if (eventX < border && eventY < border) {
            cursor = Cursor.NW_RESIZE;
        } else if (eventX < border && eventY > nodeHeight - border) {
            cursor = Cursor.SW_RESIZE;
        } else if (eventX > nodeWidth - border && eventY < border) {
            cursor = Cursor.NE_RESIZE;
        } else if (eventX > nodeWidth - border && eventY > nodeHeight - border) {
            cursor = Cursor.SE_RESIZE;
        } else if (eventX < border) {
            cursor = Cursor.W_RESIZE;
        } else if (eventX > nodeWidth - border) {
            cursor = Cursor.E_RESIZE;
        } else if (eventY < border) {
            cursor = Cursor.N_RESIZE;
        } else if (eventY > nodeHeight - border) {
            cursor = Cursor.S_RESIZE;
        } else {
            cursor = Cursor.DEFAULT;
        }

        node.setCursor(cursor);
    }
}
