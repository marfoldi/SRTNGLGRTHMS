package srtnglgrthms;

import java.io.IOException;

import srtnglgrthms.controller.BaseController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * 
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public class MainApplication extends Application {

	private Stage primaryStage;
	private BorderPane baseLayout;
	private BorderPane menuLayout;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Rendezési algoritmusok szemléltetése");

		// Set the application icon.
		this.primaryStage.getIcons().add(
				new Image("file:resources/images/icon.png"));
		BaseController.setStage(primaryStage);
		initMenuLayout();
	}

	public void initMenuLayout() {
		try {
			// Load base layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApplication.class
					.getResource("view/MenuLayout.fxml"));
			menuLayout = (BorderPane) loader.load();

			// Show the scene containing the base layout.
			Scene scene = new Scene(menuLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initializes the base layout.
	 */
	public void initBaseLayout() {
		try {
			// Load base layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApplication.class
					.getResource("view/BaseLayout.fxml"));
			baseLayout = (BorderPane) loader.load();

			// Show the scene containing the base layout.
			Scene scene = new Scene(baseLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the main stage.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}