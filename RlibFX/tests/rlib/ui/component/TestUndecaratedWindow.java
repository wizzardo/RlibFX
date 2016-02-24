package rlib.ui.component;

import javafx.application.Application;
import javafx.stage.Stage;
import rlib.ui.page.UIPage;
import rlib.ui.page.impl.AbstractUIPage;
import rlib.ui.window.UIWindow;
import rlib.ui.window.impl.UndecoratedUIWindow;
import rlib.util.array.Array;
import rlib.util.array.ArrayFactory;

/**
 * @author ronn
 */
public class TestUndecaratedWindow extends Application {

	private static final Array<Class<? extends UIPage>> AVAILABLE_PAGE = ArrayFactory.newArray(Class.class);

	public static class TestPage extends AbstractUIPage {

	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		UIWindow window = new UndecoratedUIWindow(primaryStage, AVAILABLE_PAGE);
		window.setTitle("SayoFXTool");
		window.showPage(TestPage.class);
	}

}
