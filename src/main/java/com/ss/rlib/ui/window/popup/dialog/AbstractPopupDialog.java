package com.ss.rlib.ui.window.popup.dialog;

import static javafx.geometry.Pos.CENTER;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.ss.rlib.ui.util.FXUtils;

import java.awt.*;

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

    /**
     * Instantiates a new Abstract popup dialog.
     */
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
     *
     * @param root the root
     */
    protected void createControls(@NotNull final VBox root) {
    }

    /**
     * Gets container.
     *
     * @return The content container.
     */
    @NotNull
    protected VBox getContainer() {
        return container;
    }

    /**
     * Gets owner dialog.
     *
     * @return the owner.
     */
    @Nullable
    protected AbstractPopupDialog getOwnerDialog() {
        return ownerDialog;
    }

    /**
     * Sets owner dialog.
     *
     * @param ownerDialog the owner.
     */
    protected void setOwnerDialog(@Nullable final AbstractPopupDialog ownerDialog) {
        this.ownerDialog = ownerDialog;
    }

    /**
     * Gets size.
     *
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
