package srtnglgrthms.controller;

import java.io.IOException;

import srtnglgrthms.MainApplication;
import srtnglgrthms.model.algorithm.raw.SortingSortThread;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * 
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public class MenuController {
	@FXML
	BorderPane menuPane;

	@FXML
	private void manualBtnHandler(ActionEvent event) {
		try {
			SplitPane inputLayout;
			FXMLLoader loader = new FXMLLoader();
			// Load input layout from fxml file.
			loader.setLocation(MainApplication.class
					.getResource("view/ManualInputLayout.fxml"));
			inputLayout = (SplitPane) loader.load();
			// Show the scene containing the input layout.
			Scene scene = new Scene(inputLayout);
			Stage stage = (Stage) menuPane.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void randomBtnHandler(ActionEvent event) {
		try {
			AnchorPane randomLayout;
			FXMLLoader loader = new FXMLLoader();
			// Load input layout from fxml file.
			loader.setLocation(MainApplication.class
					.getResource("view/RandomInputLayout.fxml"));
			randomLayout = (AnchorPane) loader.load();
			// Show the scene containing the input layout.
			Scene scene = new Scene(randomLayout);
			Stage stage = (Stage) menuPane.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void fileBtnHandler(ActionEvent event) {
		FileInputController.setStage((Stage) menuPane.getScene().getWindow());
		FileInputController.openChooser();
	}

	/**
	 * Opens an about dialog.
	 */
	@FXML
	private void handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Névjegy");
		alert.setHeaderText("Névjegy");
		alert.setContentText("Készítette: Márföldi Péter Bence\n\nEötvös Loránd Tudományegyetem\nInformatikai Kar\n2015\n\nGitHub: https://github.com/marfoldi/SRTNLGRTHMS");

		alert.showAndWait();
	}

	/**
	 * Closes the application.
	 */
	@SuppressWarnings("deprecation")
	@FXML
	private void handleExit() {
		if (BenchmarkController.getSortingThreads() != null) {
			for (SortingSortThread sortingThread : BenchmarkController
					.getSortingThreads()) {
				sortingThread.stop();
			}
		}
		System.exit(0);
	}
}
