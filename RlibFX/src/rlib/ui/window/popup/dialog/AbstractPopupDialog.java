package rlib.ui.window.popup.dialog;

import java.awt.*;

import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import rlib.ui.page.UIPage;
import rlib.ui.util.FXUtils;
import rlib.ui.window.UIWindow;

import static javafx.geometry.Pos.CENTER;

/**
 * Базовая реализация всплывающего диалога.
 *
 * @author Ronn
 */
public abstract class AbstractPopupDialog extends Popup {

    /**
     * Контейнер контента диалога.
     */
    private final VBox container;

    /**
     * Страница на которой был показан диалог.
     */
    private UIPage page;

    /**
     * Вызывающий диалог.
     */
    private AbstractPopupDialog ownerDialog;

    public AbstractPopupDialog() {

        container = new VBox();
        container.setAlignment(CENTER);

        createControls(container);
        configureSize(container);

        final ObservableList<Node> content = getContent();
        content.add(container);
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
     * Создание контролв в диалоге.
     */
    protected void createControls(final VBox root) {

    }

    /**
     * @return контейнер контента диалога.
     */
    protected VBox getContainer() {
        return container;
    }

    /**
     * @return вызывающий диалог.
     */
    protected AbstractPopupDialog getOwnerDialog() {
        return ownerDialog;
    }

    /**
     * @param ownerDialog вызывающий диалог.
     */
    protected void setOwnerDialog(final AbstractPopupDialog ownerDialog) {
        this.ownerDialog = ownerDialog;
    }

    /**
     * @return страница на которой был показан диалог.
     */
    protected UIPage getPage() {
        return page;
    }

    /**
     * @param page страница на которой был показан диалог.
     */
    protected void setPage(final UIPage page) {
        this.page = page;
    }

    /**
     * @return размер диалога.
     */
    protected Point getSize() {
        return new Point(500, 500);
    }

    @Override
    public void hide() {
        super.hide();

        final UIPage page = getPage();

        if (page != null) {
            final Pane rootNode = page.getRootNode();
            rootNode.setDisable(false);
        }

        final AbstractPopupDialog ownerDialog = getOwnerDialog();

        if (ownerDialog != null) {
            final VBox container = ownerDialog.getContainer();
            container.setDisable(false);
        }

        setPage(null);
        setOwnerDialog(null);
    }

    /**
     * Отобразить диалог в центре страницы над родительским диалогом.
     *
     * @param ownerDialog родительский диалог..
     */
    public void show(final AbstractPopupDialog ownerDialog) {
        setOwnerDialog(ownerDialog);

        final UIPage page = ownerDialog.getPage();

        final VBox container = ownerDialog.getContainer();
        container.setDisable(true);

        final Pane rootNode = page.getRootNode();

        final Point sizeOffset = new Point(getSize());
        sizeOffset.setLocation(sizeOffset.getX() / 2, sizeOffset.getY() / 2);

        final Point2D offset = rootNode.localToScene(new Point2D(0, 0));

        final double offsetX = rootNode.getWidth() / 2;
        final double offsetY = rootNode.getHeight() / 2;

        final UIWindow window = page.getWindow();
        final Stage owner = window.getOwner();

        final double anchorX = window.getX() + offset.getX() + offsetX - sizeOffset.getX();
        final double anchorY = window.getY() + offset.getY() + offsetY - sizeOffset.getY();

        show(owner, anchorX, anchorY);
    }

    /**
     * Отобразить диалог в центре страницы.
     *
     * @param page страница, на которой отображается диалог.
     */
    public void show(final UIPage page) {
        setPage(page);

        final Pane rootNode = page.getRootNode();
        rootNode.setDisable(true);

        final Point sizeOffset = new Point(getSize());
        sizeOffset.setLocation(sizeOffset.getX() / 2, sizeOffset.getY() / 2);

        final Point2D offset = rootNode.localToScene(new Point2D(0, 0));

        final double offsetX = rootNode.getWidth() / 2;
        final double offsetY = rootNode.getHeight() / 2;

        final UIWindow window = page.getWindow();
        final Stage owner = window.getOwner();

        final double anchorX = window.getX() + offset.getX() + offsetX - sizeOffset.getX();
        final double anchorY = window.getY() + offset.getY() + offsetY - sizeOffset.getY();

        show(owner, anchorX, anchorY);
    }
}
