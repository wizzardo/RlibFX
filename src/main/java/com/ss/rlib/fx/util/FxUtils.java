package com.ss.rlib.fx.util;

import javafx.beans.value.ObservableValue;
import javafx.css.Styleable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * The utility class.
 *
 * @author JavaSaBr
 */
public class FxUtils {

    public static class CssClassAppender {

        /**
         * Add the css class to the styleable object.
         *
         * @param styleable the styleable object.
         * @param className the css class.
         * @return the css class appender.
         */
        public CssClassAppender addClass(@NotNull Styleable styleable, @NotNull String className) {
            styleable.getStyleClass().add(className);
            return this;
        }

        /**
         * Add css classes to the styleable object.
         *
         * @param styleable  the styleable object.
         * @param classNames the css classes.
         * @return the css class appender.
         */
        public @NotNull CssClassAppender addClass(@NotNull Styleable styleable, @NotNull String... classNames) {
            styleable.getStyleClass().addAll(classNames);
            return this;
        }
    }

    public static class ChildrenAppender {

        /**
         * Add the node to the parent.
         *
         * @param parent the parent.
         * @param node   the node.
         * @return the child appender.
         */
        public @NotNull ChildrenAppender addChild(@NotNull Pane parent, @NotNull Node node) {
            parent.getChildren().add(node);
            return this;
        }

        /**
         * Add the nodes to the parent.
         *
         * @param parent the parent.
         * @param nodes  the nodes.
         * @return the child appender.
         */
        public @NotNull ChildrenAppender addChild(@NotNull Pane parent, @NotNull Node... nodes) {
            parent.getChildren().addAll(nodes);
            return this;
        }
    }

    @NotNull
    private static final CssClassAppender CLASS_APPENDER = new CssClassAppender();

    @NotNull
    private static final ChildrenAppender CHILDREN_APPENDER = new ChildrenAppender();

    /**
     * Add the css class to the styleable object.
     *
     * @param styleable the styleable object.
     * @param className the css class.
     * @return the css class appender.
     */
    public static @NotNull CssClassAppender addClass(@NotNull Styleable styleable, @NotNull String className) {
        styleable.getStyleClass().add(className);
        return CLASS_APPENDER;
    }

    /**
     * Add css classes to the styleable object.
     *
     * @param styleable  the styleable object.
     * @param classNames the css classes.
     * @return the css class appender.
     */
    public static @NotNull CssClassAppender addClass(@NotNull Styleable styleable, @NotNull String... classNames) {
        styleable.getStyleClass().addAll(classNames);
        return CLASS_APPENDER;
    }

    /**
     * Add the node to the parent.
     *
     * @param parent the parent.
     * @param node   the node.
     * @return the child appender.
     */
    public static @NotNull ChildrenAppender addChild(@NotNull Pane parent, @NotNull Node node) {
        parent.getChildren().add(node);
        return CHILDREN_APPENDER;
    }

    /**
     * Add the nodes to the parent.
     *
     * @param parent the parent.
     * @param nodes  the nodes.
     * @return the child appender.
     */
    public static @NotNull ChildrenAppender addChild(@NotNull Pane parent, @NotNull Node... nodes) {
        parent.getChildren().addAll(nodes);
        return CHILDREN_APPENDER;
    }
}
