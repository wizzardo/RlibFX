package com.ss.rlib.fx.test.window.popup.dialog;

import com.ss.rlib.fx.CssClasses;
import com.ss.rlib.fx.control.dialog.ControlDialogSupport;
import com.ss.rlib.fx.control.dialog.DefaultControlDialog;
import com.ss.rlib.fx.util.FxUtils;
import com.ss.rlib.fx.window.popup.dialog.SimplePopupDialog;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;

public class SimpleControlDialogTest extends Application {

    private static class TestDialog extends DefaultControlDialog {

        @Override
        protected void fillContent(@NotNull GridPane content) {
            super.fillContent(content);

            var textArea = new TextArea("This is a text area.");
            textArea.prefWidthProperty()
                    .bind(content.widthProperty());
            textArea.prefHeightProperty()
                    .bind(content.heightProperty());

            content.add(textArea, 0, 0);
        }

        @Override
        protected void fillActions(@NotNull GridPane actions) {
            super.fillActions(actions);

            var okButton = new Button("Ok");
            var cancelButton = new Button("Cancel");

            actions.add(okButton, 0, 0);
            actions.add(cancelButton, 1, 0);

            FxUtils.addClass(okButton, CssClasses.BUTTON_ACTION)
                    .addClass(cancelButton, CssClasses.BUTTON_ACTION);
        }
    }

    @Override
    public void start(@NotNull Stage stage) throws Exception {

        var root = new StackPane();
        var scene = new Scene(root);
        scene.getStylesheets()
                .add(CssClasses.CSS_FILE);

        var button = new Button("Create a dialog");
        button.setOnAction(event -> openDialog(scene));

        FxUtils.addChild(root, button);

        ControlDialogSupport.addSupport(scene);

        stage.setScene(scene);
        stage.setWidth(1024);
        stage.setHeight(768);
        stage.show();
    }

    private void openDialog(@NotNull Scene scene) {
        var dialog = new TestDialog();
        dialog.applySize(200, 200);
        dialog.show(scene);
    }
}
