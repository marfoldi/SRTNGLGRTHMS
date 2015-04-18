package srtnglgrthms.controller;

import java.io.IOException;

import srtnglgrthms.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
     * Opens an about algotihm dialog.
     */
    @FXML
    private void handleAlgorithm() {
    	if(OverviewListController.getSelectedItem()!=null) {
	        Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Az algoritmusr�l");
	        alert.setHeaderText(OverviewListController.getSelectedItem());
	        alert.setContentText("Le�r�s\n");

	        alert.showAndWait();
    	}
    	else {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Hiba");
    		alert.setHeaderText("Hiba t�rt�nt");
    		alert.setContentText("Nincs kiv�lasztott algoritmus!");

    		alert.showAndWait();
    	}
    }

    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("N�vjegy");
        alert.setHeaderText("N�vjegy");
        alert.setContentText("K�sz�tette: M�rf�ldi P�ter Bence\nGitHub: https://github.com/marfoldi/SRTNLGRTHMS");

        alert.showAndWait();
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