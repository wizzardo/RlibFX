package rlib.ui.util;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

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
}
