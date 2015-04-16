package srtnglgrthms.controller;

import java.io.IOException;

import srtnglgrthms.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BaseController {
	private BorderPane menuLayout;
	private static Stage stage;
	
    /**
     * Closes the application.
     */
    @FXML
    private void handleReturnToMenu() {
    	 try {
             // Load base layout from fxml file.
             FXMLLoader loader = new FXMLLoader();
             loader.setLocation(MainApplication.class.getResource("view/MenuLayout.fxml"));
             menuLayout = (BorderPane) loader.load();

             // Show the scene containing the base layout.
             Scene scene = new Scene(menuLayout);
             stage.setScene(scene);
             stage.show();
         } catch (IOException e) {
             e.printStackTrace();
         }
    }
    
    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
    
    public static void setStage(Stage stage) {
    	BaseController.stage=stage;
    }
	
}
