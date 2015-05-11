package srtnglgrthms.controller;

import java.io.IOException;

import srtnglgrthms.MainApplication;
import srtnglgrthms.model.algorithm.SortingAlgorithm;
import srtnglgrthms.model.algorithm.raw.SortingThread;
import srtnglgrthms.model.info.InfoFlyWeightFactory;
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

/**
 *
 * @author <a href="mailto:marfoldi@caesar.elte.hu">M�rf�ldi P�ter Bence</a>
 */
public class BaseController {
	@FXML
	private TabPane tabPane;

	private BorderPane menuLayout;
	private static Stage stage;

	@FXML
	private void initialize() {
		if (SortingAlgorithm.getNumbers().length > 100) {
			SingleSelectionModel<Tab> selectionModel = tabPane
					.getSelectionModel();
			selectionModel.select(1);
			tabPane.getTabs().remove(0);
		}
	}

	/**
	 * Closes the application.
	 */
	@SuppressWarnings("deprecation")
	@FXML
	private void handleReturnToMenu() {
		OverviewController.getAnimation().stop();
		BenchmarkController.getBenchmarkDataList().clear();
		SortingAlgorithm.getCounterData().clear();
		if(BenchmarkController.getSortingThreads() != null) {
			for(SortingThread sortingThread : BenchmarkController.getSortingThreads()) {
				sortingThread.stop();
			}
		}
		try {
			// Load base layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApplication.class
					.getResource("view/MenuLayout.fxml"));
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
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		if ((tabPane.getTabs().size() == 1 || selectionModel.getSelectedIndex() == 1)
				&& BenchmarkTableController.getSelectedItem() != null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Az algoritmusr�l");
			alert.setHeaderText(BenchmarkTableController.getSelectedItem());
			try {
				alert.setContentText(InfoFlyWeightFactory.getInfo(BenchmarkTableController.getSelectedItem()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			alert.showAndWait();
		} else if (tabPane.getTabs().size() == 2
				&& selectionModel.getSelectedIndex() == 0
				&& OverviewListController.getSelectedItem() != null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Az algoritmusr�l");
			alert.setHeaderText(OverviewListController.getSelectedItem());
			try {
				alert.setContentText(InfoFlyWeightFactory.getInfo(OverviewListController.getSelectedItem()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			alert.showAndWait();
		} else {
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
		alert.setContentText("K�sz�tette: M�rf�ldi P�ter Bence\n\nE�tv�s Lor�nd Tudom�nyegyetem\nInformatikai Kar\n2015\n\nGitHub: https://github.com/marfoldi/SRTNLGRTHMS");

		alert.showAndWait();
	}

	/**
	 * Closes the application.
	 */
	@SuppressWarnings("deprecation")
	@FXML
	private void handleExit() {
		if(BenchmarkController.getSortingThreads() != null) {
			for(SortingThread sortingThread : BenchmarkController.getSortingThreads()) {
				sortingThread.stop();
			}
		}
		System.exit(0);
	}

	public static void setStage(Stage stage) {
		BaseController.stage = stage;
	}

}
