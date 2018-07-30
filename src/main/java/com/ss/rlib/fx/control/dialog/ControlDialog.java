package com.ss.rlib.fx.control.dialog;

import com.ss.rlib.fx.util.FxUtils;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Implementing a dialog like a control to be inside a main window.
 *
 * @author JavaSaBr
 */
public abstract class ControlDialog<H extends Parent, C extends Parent, A extends Parent> extends VBox {

    /**
     * The dialog's header.
     */
    @NotNull
    private final H header;

    /**
     * The dialog's content container.
     */
    @NotNull
    private final C container;

    /**
     * The dialog's actions container.
     */
    @NotNull
    private final A actions;

    /**
     * The owner dialog.
     */
    @Nullable
    private ControlDialog<?, ?, ?> ownerDialog;

    /**
     * The flat about that this dialog was full constructed.
     */
    private volatile boolean ready;

    private final BooleanProperty showing;

    private ControlDialog() {
        this.showing = new SimpleBooleanProperty(this, "showing", false);
        this.header = createHeader();
        this.container = createContainer();
        this.actions = createActions();
    }

    public @NotNull BooleanProperty showingProperty() {
        return showing;
    }

    /**
     * Create a header container.
     *
     * @return the header container.
     */
    protected abstract @NotNull H createHeader();

    /**
     * Create a content container.
     *
     * @return the content container.
     */
    protected abstract @NotNull C createContainer();

    /**
     * Create an actions container.
     *
     * @return the actions container.
     */
    protected abstract @NotNull A createActions();

    /**
     * Construct content of this dialog after constructor.
     */
    public void postConstruct() {

        if (ready) {
            return;
        }

        configure();
        fillHeader(header);
        fillContent(container);
        fillActions(actions);

        ready = true;
    }

    /**
     * Apply the size to this dialog.
     *
     * @param size the new size.
     */
    public void applySize(@NotNull Point2D size) {
        applySize(size.getX(), size.getY());
    }

    /**
     * Apply the size to this dialog.
     *
     * @param width  the new width.
     * @param height the new height.
     */
    public void applySize(double width, double height) {
        FxUtils.setFixedSize(this, width, height);
    }

    /**
     * Configure this dialog.
     */
    protected void configure() {
    }

    protected void fillHeader(@NotNull H header) {

    }

    protected void fillContent(@NotNull C content) {

    }

    protected void fillActions(@NotNull A actions) {

    }

    /**
     * Show this dialog on the scene by the coords.
     *
     * @param scene the scene.
     * @param x the X coord.
     * @param y the Y coord.
     */
    public void show(@NotNull Scene scene, double x, double y) {

        if (showing.get()) {
            return;
        }

        ControlDialogSupport.getDialogsLayer(scene)
                .getChildren()
                .add(this);

        setTranslateX(x);
        setTranslateY(y);

        if (ownerDialog != null) {
            ownerDialog.setDisabled(true);
        }

        showing.setValue(true);
    }

    /**
     * Hide this dialog.
     */
    public void hide() {

        if (!showing.get()) {
            return;
        }

        ControlDialogSupport.getDialogsLayer(getScene())
                .getChildren()
                .remove(this);

        if (ownerDialog != null) {
            ownerDialog.setDisabled(false);
            ownerDialog = null;
        }

        showing.setValue(false);
    }
}
