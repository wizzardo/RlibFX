package rlib.ui.component.impl;

import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

/**
 * Реализация простой панельки для принятия drag элементов.
 *
 * @author Ronn
 */
public class SimpleDroppedPanel extends Pane {

    public SimpleDroppedPanel() {
        setOnDragOver(this::processOnDragOver);
        setOnDragEntered(this::processOnDragEntered);
        setOnDragExited(this::processOnDragExited);
        setOnDragDropped(this::processOnDragDropped);
    }

    /**
     * Принятие режима трансфера перемещаемого объекта.
     */
    protected void acceptTransfersMode(final DragEvent event) {
        event.acceptTransferModes(TransferMode.COPY);
    }

    /**
     * Обработка завершения перемещения объекта на этот элемент.
     */
    protected void processOnDragDropped(final DragEvent event) {
        event.setDropCompleted(true);
        event.consume();
    }

    /**
     * Обработка вхождения перемещаемого объекта в зону этого объекта.
     */
    protected void processOnDragEntered(final DragEvent event) {
        event.consume();
    }

    /**
     * Обработка выхода перемещаемого объекта из зоны этого.
     */
    protected void processOnDragExited(final DragEvent event) {
        event.consume();
    }

    /**
     * Обработка нахождения перемещаемого объекта над этим.
     */
    protected void processOnDragOver(final DragEvent event) {

        final Object source = event.getGestureSource();

        if (source != this) {
            acceptTransfersMode(event);
        }

        event.consume();
    }
}
