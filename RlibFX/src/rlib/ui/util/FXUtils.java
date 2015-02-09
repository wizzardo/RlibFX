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

	public static void addToPane(Node node, Pane parent) {
		ObservableList<Node> children = parent.getChildren();
		children.add(node);
	}

	public static void removeToPane(Node node, Pane parent) {
		ObservableList<Node> children = parent.getChildren();
		children.remove(node);
	}

	public static void addDebugBorderTo(Node node) {
		node.setStyle("-fx-border-color: rgb(49, 89, 23); -fx-border-radius: 5;");
	}

	/**
	 * Добавление случайного цвета фона для отладки.
	 */
	public static void addDebugBackgroundColor(Node node) {

		Random random = new Random();

		String color = "rgb(" + random.nextInt(255) + "," + random.nextInt(255) + ", " + random.nextInt(255) + ")";

		node.setStyle("-fx-background-color: " + color + ";");
	}

	public static void setFixedHeight(Region region, double height) {
		region.setMaxHeight(height);
		region.setMinHeight(height);
	}

	public static void setFixedWidth(Region region, double width) {
		region.setMaxWidth(width);
		region.setMinWidth(width);
	}

	public static void setFixedSize(Region region, double width, double height) {
		region.setMaxHeight(height);
		region.setMinHeight(height);
		region.setMaxWidth(width);
		region.setMinWidth(width);
	}

	public static void setFixedSize(Region region, Point size) {
		region.setMaxHeight(size.getY());
		region.setMinHeight(size.getY());
		region.setMaxWidth(size.getX());
		region.setMinWidth(size.getX());
	}

	public static void bindFixedSize(Region region, ReadOnlyProperty<Double> widthProperty, ReadOnlyProperty<Double> heightProperty) {
		region.minWidthProperty().bind(widthProperty);
		region.maxWidthProperty().bind(widthProperty);
		region.minHeightProperty().bind(heightProperty);
		region.maxHeightProperty().bind(heightProperty);
	}

	public static void bindFixedHeight(Region region, ReadOnlyProperty<Double> heightProperty) {
		region.minHeightProperty().bind(heightProperty);
		region.maxHeightProperty().bind(heightProperty);
	}

	public static void bindFixedWidth(Region region, ReadOnlyProperty<Double> widthProperty) {
		region.minWidthProperty().bind(widthProperty);
		region.maxWidthProperty().bind(widthProperty);
	}

	public static void bindFixedSize(Region region, ObservableValue<? extends Number> width, ObservableValue<? extends Number> height) {
		region.minWidthProperty().bind(width);
		region.maxWidthProperty().bind(width);
		region.minHeightProperty().bind(height);
		region.maxHeightProperty().bind(height);
	}

	public static void bindFixedWidth(Region region, ObservableValue<? extends Number> width) {
		region.minWidthProperty().bind(width);
		region.maxWidthProperty().bind(width);
	}

	public static void bindFixedHeight(Region region, ObservableValue<? extends Number> height) {
		region.minHeightProperty().bind(height);
		region.maxHeightProperty().bind(height);
	}
}
