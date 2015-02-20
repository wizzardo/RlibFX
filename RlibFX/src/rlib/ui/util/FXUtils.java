package rlib.ui.util;

import java.awt.Point;
import java.util.Random;

import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

/**
 * Набор утильных методов для работы с JavaFX.
 * 
 * @author Ronn
 */
public class FXUtils {

	public static void addToPane(final Node node, final Pane parent) {
		final ObservableList<Node> children = parent.getChildren();
		children.add(node);
	}

	public static void removeToPane(final Node node, final Pane parent) {
		final ObservableList<Node> children = parent.getChildren();
		children.remove(node);
	}

	public static void addDebugBorderTo(final Node node) {
		node.setStyle("-fx-border-color: rgb(49, 89, 23); -fx-border-radius: 5;");
	}

	/**
	 * Добавление случайного цвета фона для отладки.
	 */
	public static void addDebugBackgroundColor(final Node node) {

		final Random random = new Random();

		final String color = "rgb(" + random.nextInt(255) + "," + random.nextInt(255) + ", " + random.nextInt(255) + ")";

		node.setStyle("-fx-background-color: " + color + ";");
	}

	public static void setFixedHeight(final Region region, final double height) {
		region.setMaxHeight(height);
		region.setMinHeight(height);
	}

	public static void setFixedWidth(final Region region, final double width) {
		region.setMaxWidth(width);
		region.setMinWidth(width);
	}

	public static void setFixedSize(final Region region, final double width, final double height) {
		region.setMaxHeight(height);
		region.setMinHeight(height);
		region.setMaxWidth(width);
		region.setMinWidth(width);
	}

	public static void setFixedSize(final Region region, final Point size) {
		region.setMaxHeight(size.getY());
		region.setMinHeight(size.getY());
		region.setMaxWidth(size.getX());
		region.setMinWidth(size.getX());
	}

	public static void bindFixedSize(final Region region, final ReadOnlyProperty<Double> widthProperty, final ReadOnlyProperty<Double> heightProperty) {
		region.minWidthProperty().bind(widthProperty);
		region.maxWidthProperty().bind(widthProperty);
		region.minHeightProperty().bind(heightProperty);
		region.maxHeightProperty().bind(heightProperty);
	}

	public static void bindFixedHeight(final Region region, final ReadOnlyProperty<Double> heightProperty) {
		region.minHeightProperty().bind(heightProperty);
		region.maxHeightProperty().bind(heightProperty);
	}

	public static void bindFixedWidth(final Region region, final ReadOnlyProperty<Double> widthProperty) {
		region.minWidthProperty().bind(widthProperty);
		region.maxWidthProperty().bind(widthProperty);
	}

	public static void bindFixedSize(final Region region, final ObservableValue<? extends Number> width, final ObservableValue<? extends Number> height) {
		region.minWidthProperty().bind(width);
		region.maxWidthProperty().bind(width);
		region.minHeightProperty().bind(height);
		region.maxHeightProperty().bind(height);
	}

	public static void bindFixedWidth(final Region region, final ObservableValue<? extends Number> width) {
		region.minWidthProperty().bind(width);
		region.maxWidthProperty().bind(width);
	}

	public static void bindFixedHeight(final Region region, final ObservableValue<? extends Number> height) {
		region.minHeightProperty().bind(height);
		region.maxHeightProperty().bind(height);
	}
}
