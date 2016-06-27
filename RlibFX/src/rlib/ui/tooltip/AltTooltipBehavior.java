package rlib.ui.tooltip;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableMap;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;
import javafx.util.Duration;

/**
 * Реализация альтернативного поведения Tooltip, с активацией по правому клику мышки.
 *
 * @author Ronn
 */
public class AltTooltipBehavior {

    private static final AltTooltipBehavior INSTANCE = new AltTooltipBehavior(new Duration(10000));

    private static String TOOLTIP_PROP_KEY = "javafx.scene.control.Tooltip";

    private static int TOOLTIP_XOFFSET = 10;
    private static int TOOLTIP_YOFFSET = 7;

    /**
     * Установка всплывающей подсказки для указанного элемента.
     *
     * @param node    элемент которому надо добавить подсказку.
     * @param tooltip добавляемая подсказка.
     */
    public static void addTooltip(final Node node, final Tooltip tooltip) {
        INSTANCE.install(node, tooltip);
    }

    /**
     * Добавление всплывающей подсказки для указанного элемента вместе с стандартным поведением.
     *
     * @param node    элемент которому добавляем подсказку.
     * @param tooltip добавляемая подсказка.
     */
    public static void addTooltipWithStandard(final Node node, final Tooltip tooltip) {
        INSTANCE.install(node, tooltip);
        Tooltip.install(node, tooltip);
    }

    /**
     * Получение текущей подсказки у указнного элемента.
     *
     * @param node интересуемый элемент.
     * @return подсказка у этого элемента либо <code>null</code> если ее у него нету.
     */
    public static Tooltip getToolTip(final Node node) {
        final ObservableMap<Object, Object> properties = node.getProperties();
        return (Tooltip) properties.get(TOOLTIP_PROP_KEY);
    }

    /**
     * Удаление подсказки у указанногоэ лемента.
     *
     * @param node элемент у которого удаляем подсказку.
     * @return удаленная подсказка у элемента.
     */
    public static Tooltip removeToolTip(final Node node) {

        final ObservableMap<Object, Object> properties = node.getProperties();
        final Tooltip tooltip = (Tooltip) properties.get(TOOLTIP_PROP_KEY);

        INSTANCE.uninstall(node);

        return tooltip;
    }

    /**
     * Удаление всплывающей подсказки у указанного элемента и в случае стандартной тоже.
     *
     * @param node элемент у которого удаляем подсказку.
     * @return удаленная подсказка.
     */
    public static Tooltip removeToolTipWithStandard(final Node node) {

        final ObservableMap<Object, Object> properties = node.getProperties();
        final Tooltip tooltip = (Tooltip) properties.get(TOOLTIP_PROP_KEY);

        INSTANCE.uninstall(node);

        Tooltip.uninstall(node, tooltip);

        return tooltip;
    }

    /**
     * таймер закрытия тултипа
     */
    private final Timeline hideTimer = new Timeline();
    /**
     * The Node with a tooltip over which the mouse is hovering. There can only be one of these at a
     * time.
     */
    private Node hoveredNode;
    /**
     * The tooltip that is currently activated. There can only be one of these at a time.
     */
    private Tooltip activatedTooltip;
    /**
     * The tooltip that is currently visible. There can only be one of these at a time.
     */
    private Tooltip visibleTooltip;
    /**
     * Registers for mouse exit events. If the ACTIVATION_TIMER is running then this will simply
     * stop it. If the HIDE_TIMER is running then this will stop the HIDE_TIMER, hide the tooltip,
     * and start the LEFT_TIMER.
     */
    private final EventHandler<MouseEvent> leavingHandler = event -> processLeaving();
    /**
     * Registers for mouse click, press, release, drag events. If any of these occur, then the
     * tooltip is hidden (if it is visible), it is deactivated, and any and all timers are stopped.
     */
    private final EventHandler<MouseEvent> killHandler = event -> proccessKill(event);
    /**
     * The last position of the mouse, in screen coordinates.
     */
    private double lastMouseX;
    private double lastMouseY;
    /**
     * обработчик активации тултипа при клилке правой кнопкой мыши
     */
    private final EventHandler<? super MouseEvent> activationHandler = this::processActivate;
    /**
     * Registers for mouse move events only. When the mouse is moved, this handler will detect it
     * and decide whether to start the ACTIVATION_TIMER (if the ACTIVATION_TIMER is not started),
     * restart the ACTIVATION_TIMER (if ACTIVATION_TIMER is running), or skip the ACTIVATION_TIMER
     * and just show the tooltip (if the LEFT_TIMER is running).
     */
    private final EventHandler<MouseEvent> moveHandler = this::processMove;

    public AltTooltipBehavior(final Duration visibleDuration) {
        hideTimer.getKeyFrames().add(new KeyFrame(visibleDuration));
        hideTimer.setOnFinished(event -> {
            // Hide the currently visible tooltip.
            visibleTooltip.hide();
            visibleTooltip = null;
            hoveredNode = null;
        });
    }

    /**
     * Gets the top level window associated with this node.
     *
     * @param node the node
     * @return the top level window
     */
    private Window getWindow(final Node node) {
        final Scene scene = node == null ? null : node.getScene();
        return scene == null ? null : scene.getWindow();
    }

    private void install(final Node node, final Tooltip tooltip) {

        if (node == null) {
            return;
        }

        node.addEventHandler(MouseEvent.MOUSE_MOVED, moveHandler);
        node.addEventHandler(MouseEvent.MOUSE_EXITED, leavingHandler);
        node.addEventHandler(MouseEvent.MOUSE_PRESSED, activationHandler);
        node.addEventHandler(MouseEvent.MOUSE_PRESSED, killHandler);

        final ObservableMap<Object, Object> properties = node.getProperties();
        properties.put(TOOLTIP_PROP_KEY, tooltip);
    }

    /**
     * Gets whether the entire window hierarchy is visible for this node.
     *
     * @param node the node to check
     * @return true if entire hierarchy is visible
     */
    private boolean isWindowHierarchyVisible(final Node node) {

        boolean treeVisible = node != null;
        Parent parent = node == null ? null : node.getParent();

        while (parent != null && treeVisible) {
            treeVisible = parent.isVisible();
            parent = parent.getParent();
        }

        return treeVisible;
    }

    protected void proccessKill(final MouseEvent event) {

        if (event != null && event.getButton() == MouseButton.SECONDARY) {
            return;
        }

        hideTimer.stop();

        if (visibleTooltip != null) {
            visibleTooltip.hide();
        }

        hoveredNode = null;
        activatedTooltip = null;
        visibleTooltip = null;
    }

    protected void processActivate(final MouseEvent event) {

        final MouseButton button = event.getButton();

        if (button != MouseButton.SECONDARY) {
            return;
        }

        final Window owner = getWindow(hoveredNode);
        final boolean treeVisible = isWindowHierarchyVisible(hoveredNode);

        if (owner != null && owner.isShowing() && treeVisible) {

            double x = lastMouseX;
            double y = lastMouseY;

            final NodeOrientation nodeOrientation = hoveredNode.getEffectiveNodeOrientation();
            activatedTooltip.getScene().setNodeOrientation(nodeOrientation);

            if (nodeOrientation == NodeOrientation.RIGHT_TO_LEFT) {
                x -= activatedTooltip.getWidth();
            }

            activatedTooltip.show(owner, x + TOOLTIP_XOFFSET, y + TOOLTIP_YOFFSET);

            if (y + TOOLTIP_YOFFSET > activatedTooltip.getAnchorY()) {
                activatedTooltip.hide();
                y -= activatedTooltip.getHeight();
                activatedTooltip.show(owner, x + TOOLTIP_XOFFSET, y);
            }

            visibleTooltip = activatedTooltip;
            hoveredNode = null;
            hideTimer.playFromStart();
        }

        activatedTooltip = null;
    }

    protected void processLeaving() {

        if (hideTimer.getStatus() == Timeline.Status.RUNNING) {

            hideTimer.stop();

            if (visibleTooltip != null) {
                visibleTooltip.hide();
            }
        }

        hoveredNode = null;
        activatedTooltip = null;
        visibleTooltip = null;
    }

    protected void processMove(final MouseEvent event) {

        lastMouseX = event.getScreenX();
        lastMouseY = event.getScreenY();

        if (hideTimer.getStatus() == Timeline.Status.RUNNING) {
            return;
        }

        hoveredNode = (Node) event.getSource();

        final ObservableMap<Object, Object> properties = hoveredNode.getProperties();
        final Tooltip tooltip = (Tooltip) properties.get(TOOLTIP_PROP_KEY);

        if (tooltip == null) {
            return;
        }

        final Window owner = getWindow(hoveredNode);

        if (owner == null) {
            return;
        }

        final boolean treeVisible = isWindowHierarchyVisible(hoveredNode);

        if (owner != null && treeVisible) {
            activatedTooltip = tooltip;
        }
    }

    private void uninstall(final Node node) {

        if (node == null) {
            return;
        }

        node.removeEventHandler(MouseEvent.MOUSE_MOVED, moveHandler);
        node.removeEventHandler(MouseEvent.MOUSE_EXITED, leavingHandler);
        node.removeEventHandler(MouseEvent.MOUSE_PRESSED, activationHandler);
        node.removeEventHandler(MouseEvent.MOUSE_PRESSED, killHandler);

        final ObservableMap<Object, Object> properties = node.getProperties();
        final Tooltip tooltip = (Tooltip) properties.get(TOOLTIP_PROP_KEY);

        if (tooltip == null) {
            return;
        }

        properties.remove(TOOLTIP_PROP_KEY);

        if (tooltip.equals(visibleTooltip) || tooltip.equals(activatedTooltip)) {
            killHandler.handle(null);
        }
    }
}