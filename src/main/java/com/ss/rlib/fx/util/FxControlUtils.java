package com.ss.rlib.fx.util;

import com.ss.rlib.fx.util.ObservableUtils.ChangeEventAppender;
import javafx.scene.control.TextInputControl;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * The utility class.
 *
 * @author JavaSaBr
 */
public class FxControlUtils {

    /**
     * Add handler of text changes.
     *
     * @param control the text control.
     * @param handler the handler.
     * @return the change event appender.
     */
    public static ChangeEventAppender<String> onTextChange(
            @NotNull TextInputControl control,
            @NotNull Consumer<String> handler
    ) {
        return ObservableUtils.onChange(control.textProperty(), handler);
    }
}
