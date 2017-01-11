package rlib.ui.window.popup.dialog;

import static javafx.geometry.Pos.CENTER;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.awt.Point;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import rlib.ui.util.FXUtils;

/**
 * The implementation of dialog base on popup.
 *
 * @author JavaSaBr
 */
public abstract class AbstractPopupDialog extends Popup {

    /**
     * The content container.
     */
    @NotNull
    private final VBox container;

    /**
     * The owner.
     */
    @Nullable
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
     * Configure the size of this dialog.
     *
     * @param container the root container.
     */
    protected void configureSize(@NotNull final VBox container) {
        FXUtils.setFixedSize(container, getSize());
    }

    /**
     * Create content of this dialog.
     */
    protected void createControls(@NotNull final VBox root) {
    }

    /**
     * @return The content container.
     */
    @NotNull
    protected VBox getContainer() {
        return container;
    }

    /**
     * @return the owner.
     */
    @Nullable
    protected AbstractPopupDialog getOwnerDialog() {
        return ownerDialog;
    }

    /**
     * @param ownerDialog the owner.
     */
    protected void setOwnerDialog(@Nullable final AbstractPopupDialog ownerDialog) {
        this.ownerDialog = ownerDialog;
    }

    /**
     * @return the dialog size.
     */
    @NotNull
    protected Point getSize() {
        return new Point(500, 500);
    }

    @Override
    public void hide() {
        super.hide();

        final AbstractPopupDialog ownerDialog = getOwnerDialog();

        if (ownerDialog != null) {
            final VBox container = ownerDialog.getContainer();
            container.setDisable(false);
        }

        setOwnerDialog(null);
    }
}
