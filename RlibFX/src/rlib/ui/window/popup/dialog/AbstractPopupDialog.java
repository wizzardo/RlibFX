package rlib.ui.window.popup.dialog;

import static javafx.geometry.Pos.CENTER;

import java.awt.Point;

import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import rlib.ui.page.UIPage;
import rlib.ui.util.FXUtils;
import rlib.ui.window.UIWindow;

/**
 * Базовая реализация всплывающего диалога.
 * 
 * @author Ronn
 */
public abstract class AbstractPopupDialog extends Popup {

	public AbstractPopupDialog() {

		final VBox container = new VBox();
		container.setAlignment(CENTER);

		createControls(container);
		configureSize(container);

		final ObservableList<Node> content = getContent();
		content.add(container);
	}

	/**
	 * Отобразить диалог в центре страницы.
	 * 
	 * @param page страница, на которой отображается диалог.
	 */
	public void show(UIPage page) {

		final Pane rootNode = page.getRootNode();
		rootNode.setDisable(true);

		final Point sizeOffset = new Point(getSize());
		sizeOffset.setLocation(sizeOffset.getX() / 2, sizeOffset.getY() / 2);

		final Point2D offset = rootNode.localToScene(new Point2D(0, 0));

		final UIWindow window = page.getWindow();

		final double anchorX = window.getX() + offset.getX() - sizeOffset.getX();
		final double anchorY = window.getY() + offset.getY() - sizeOffset.getY();

		show(this, anchorX, anchorY);
	}

	/**
	 * Конфигурирование размера диалога.
	 * 
	 * @param container рутовый контейнер диалога.
	 */
	protected void configureSize(final VBox container) {
		FXUtils.setFixedSize(container, getSize());
	}

	/**
	 * @return размер диалога.
	 */
	protected Point getSize() {
		return new Point(500, 500);
	}

	/**
	 * Создание контролв в диалоге.
	 */
	protected void createControls(VBox root) {

	}
}
