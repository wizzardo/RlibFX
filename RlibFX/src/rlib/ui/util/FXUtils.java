package rlib.ui.util;

import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import java.awt.*;
import java.util.Random;

/**
 * Набор утильных методов для работы с JavaFX.
 *
 * @author Ronn
 */
public class FXUtils {

    /**
     * Добавление к узлу CSS класса.
     *
     * @param node      узел к которому добавляем CSS класс.
     * @param className название CSS класса.
     */
    public static void addClassTo(Node node, String className) {
        node.getStyleClass().add(className);
    }

    /**
     * Добавление случайного цвета фона для отладки.
     */
    public static void addDebugBackgroundColor(final Node node) {

        final Random random = new Random();

        final String color = "rgb(" + random.nextInt(255) + "," + random.nextInt(255) + ", " + random.nextInt(255) + ")";

        node.setStyle("-fx-background-color: " + color + ";");
    }

    /**
     * Добавление для дебага бордера узлу, что бы дебажить лаяутинг.
     *
     * @param node узел для которого добавляется бордер.
     */
    public static void addDebugBorderTo(final Node node) {
        node.setStyle("-fx-border-color: red; -fx-border-width: 3px;");
    }

    /**
     * Добавление узла к дочерним элементам панели.
     *
     * @param node   добавляемый узел.
     * @param parent панель, в которой добавляется узел.
     */
    public static void addToPane(final Node node, final Pane parent) {
        final ObservableList<Node> children = parent.getChildren();
        children.add(node);
    }

    /**
     * Привязка высоты компонента к внешнему свойству.
     *
     * @param region         компонент, чью высоту нужно привязать к свойству.
     * @param heightProperty свойство с высотой.
     */
    public static void bindFixedHeight(final Region region, final ReadOnlyProperty<Double> heightProperty) {
        region.minHeightProperty().bind(heightProperty);
        region.maxHeightProperty().bind(heightProperty);
    }

    /**
     * Привязка высоты компонента к внешнему свойству.
     *
     * @param region компонент, чью высоту нужно привязать к свойству.
     * @param height свойство с высотой.
     */
    public static void bindFixedHeight(final Region region, final ObservableValue<? extends Number> height) {
        region.minHeightProperty().bind(height);
        region.maxHeightProperty().bind(height);
    }

    /**
     * Привязка размера компонента к внешним свойствам ширины и высоты.
     *
     * @param region         компонент, чей размер нужно привязать к внешним свойствам.
     * @param widthProperty  свойство с шириной.
     * @param heightProperty свойство с высотой.
     */
    public static void bindFixedSize(final Region region, final ReadOnlyProperty<Double> widthProperty, final ReadOnlyProperty<Double> heightProperty) {
        region.minWidthProperty().bind(widthProperty);
        region.maxWidthProperty().bind(widthProperty);
        region.minHeightProperty().bind(heightProperty);
        region.maxHeightProperty().bind(heightProperty);
    }

    /**
     * Привязка размера компонента к внешним свойствам ширины и высоты.
     *
     * @param region компонент, чей размер нужно привязать к внешним свойствам.
     * @param width  свойство с шириной.
     * @param height свойство с высотой.
     */
    public static void bindFixedSize(final Region region, final ObservableValue<? extends Number> width, final ObservableValue<? extends Number> height) {
        region.minWidthProperty().bind(width);
        region.maxWidthProperty().bind(width);
        region.minHeightProperty().bind(height);
        region.maxHeightProperty().bind(height);
    }

    /**
     * Привязка ширины компонента к внешнему свойству.
     *
     * @param region        компонент, чью ширину нужно привязать к свойству.
     * @param widthProperty свойство с шириной.
     */
    public static void bindFixedWidth(final Region region, final ReadOnlyProperty<Double> widthProperty) {
        region.minWidthProperty().bind(widthProperty);
        region.maxWidthProperty().bind(widthProperty);
    }

    /**
     * Привязка ширины компонента к внешнему свойству.
     *
     * @param region компонент, чью ширину нужно привязать к свойству.
     * @param width  свойство с шириной.
     */
    public static void bindFixedWidth(final Region region, final ObservableValue<? extends Number> width) {
        region.minWidthProperty().bind(width);
        region.maxWidthProperty().bind(width);
    }

    /**
     * Удаление узла из дочерних элементов панели.
     *
     * @param node   удаляемый узел.
     * @param parent панель у которой удаляется узел.
     */
    public static void removeToPane(final Node node, final Pane parent) {
        final ObservableList<Node> children = parent.getChildren();
        children.remove(node);
    }

    /**
     * Установка виксированной высоты для компонента.
     *
     * @param region компонент, котророму устанавливается фиксированная высота.
     * @param height значение фиксированной высоты.
     */
    public static void setFixedHeight(final Region region, final double height) {
        region.setMaxHeight(height);
        region.setMinHeight(height);
    }

    /**
     * Установка фиксированного размера для компонента.
     *
     * @param region компонент, которому надо установить фиксированный размер.
     * @param width  значение фиксированной ширины.
     * @param height значение фиксированной высоты.
     */
    public static void setFixedSize(final Region region, final double width, final double height) {
        region.setMaxHeight(height);
        region.setMinHeight(height);
        region.setMaxWidth(width);
        region.setMinWidth(width);
    }

    /**
     * Установка фиксированного размера для компонента.
     *
     * @param region компонент, которому надо установить фиксированный размер.
     * @param size   значение фиксированного размера.
     */
    public static void setFixedSize(final Region region, final Point size) {
        region.setMaxHeight(size.getY());
        region.setMinHeight(size.getY());
        region.setMaxWidth(size.getX());
        region.setMinWidth(size.getX());
    }

    /**
     * Установка фиксированной широты для компонента.
     *
     * @param region компонент, которому надо зафиксировать ширину.
     * @param width  значение фиксированной ширины.
     */
    public static void setFixedWidth(final Region region, final double width) {
        region.setMaxWidth(width);
        region.setMinWidth(width);
    }
}
