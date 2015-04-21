package srtnglgrthms.controller;

import java.io.IOException;

import srtnglgrthms.MainApplication;
import srtnglgrthms.model.algorithm.GraphAlgorithm;
import srtnglgrthms.model.algorithm.SortingAlgorithm;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BaseController {
	@FXML
	private TabPane tabPane;

	private BorderPane menuLayout;
	private static Stage stage;

	@FXML
	private void initialize() {
		if(SortingAlgorithm.getNumbers().length>100) {
			SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
			selectionModel.select(1);
			tabPane.getTabs().remove(0);
		}
	}

    /**
     * Closes the application.
     */
    @FXML
    private void handleReturnToMenu() {
    	OverviewController.getAnimation().stop();
    	SortingAlgorithm.getBenchmarkData().clear();
    	SortingAlgorithm.getCounterData().clear();
    	GraphAlgorithm.setShowedHeapWarning(false);
    	GraphAlgorithm.setShowedTournamentWarning(false);
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
	        alert.setTitle("Az algoritmusról");
	        alert.setHeaderText(OverviewListController.getSelectedItem());
	        alert.setContentText("Leírás\n");

	        alert.showAndWait();
    	}
    	else {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Hiba");
    		alert.setHeaderText("Hiba történt");
    		alert.setContentText("Nincs kiválasztott algoritmus!");

    		alert.showAndWait();
    	}
    }

    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Névjegy");
        alert.setHeaderText("Névjegy");
        alert.setContentText("Készítette: Márföldi Péter Bence\nGitHub: https://github.com/marfoldi/SRTNLGRTHMS");

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
