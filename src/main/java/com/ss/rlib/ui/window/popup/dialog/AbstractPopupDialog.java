package com.ss.rlib.ui.window.popup.dialog;

import static javafx.geometry.Pos.CENTER;
import com.ss.rlib.ui.util.FXUtils;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

/**
 * The implementation of a dialog which is based on a popup.
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
        getContent().add(container);
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
     * @param root the root.
     */
    protected void createControls(@NotNull final VBox root) {
    }

    /**
     * Get the content container.
     *
     * @return the content container.
     */
    protected @NotNull VBox getContainer() {
        return container;
    }

    /**
     * Get the owner dialog.
     *
     * @return the owner dialog.
     */
    protected @Nullable AbstractPopupDialog getOwnerDialog() {
        return ownerDialog;
    }

    /**
     * Set the owner dialog.
     *
     * @param ownerDialog the owner dialog.
     */
    protected void setOwnerDialog(@Nullable final AbstractPopupDialog ownerDialog) {
        this.ownerDialog = ownerDialog;
    }

    /**
     * Get the dialog size.
     *
     * @return the dialog size.
     */
    protected @NotNull Point getSize() {
        return new Point(500, 500);
    }

    @Override
    public void hide() {
        super.hide();

        final AbstractPopupDialog dialog = getOwnerDialog();

        if (dialog != null) {
            final VBox container = dialog.getContainer();
            container.setDisable(false);
        }

        setOwnerDialog(null);
    }
}
