package rlib.ui.util;

import java.awt.Point;

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
}
