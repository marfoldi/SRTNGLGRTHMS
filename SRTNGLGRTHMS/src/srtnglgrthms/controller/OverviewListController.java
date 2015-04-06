package srtnglgrthms.controller;

import java.io.IOException;

import srtnglgrthms.MainApplication;
import srtnglgrthms.model.SortingAlgorithmFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class OverviewListController {
	@FXML
	private ListView<String> algorithmList;
	private static OverviewController parentController;
	private static String currentValue;

	@FXML
	private void initialize() {
		setList();
		algorithmList.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<String>() {
					@Override
					public void changed(
							ObservableValue<? extends String> observable,
							String oldValue, String newValue) {
						OverviewChartController.getAnimation().stop();
						OverviewListController.currentValue = newValue;
						OverviewChartController.reloadSeries();
						parentController.reloadButtons();
						if (newValue.equals("Kupac rendez�s")
								|| newValue.equals("Versenyrendez�s")
								|| newValue.equals("Radix \"vissza\"")) {
							if (newValue.equals("Kupac rendez�s")
									|| newValue.equals("Versenyrendez�s")) {
								FXMLLoader loader = new FXMLLoader();
								loader.setLocation(MainApplication.class
										.getResource("view/OverViewGraphLayout.fxml"));
								try {
									((AnchorPane) parentController.displayPane
											.getItems().get(0)).getChildren()
											.setAll(loader.load());
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						} else {
							FXMLLoader loader = new FXMLLoader();
							loader.setLocation(MainApplication.class
									.getResource("view/OverViewChartLayout.fxml"));
							try {
								((AnchorPane) parentController.displayPane
										.getItems().get(0)).getChildren()
										.setAll(loader.load());
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							SortingAlgorithmFactory.getAlgorithm(currentValue)
									.setDefaults();
						}
					}
				});
	}

	private void setList() {
		ObservableList<String> algorithms = FXCollections.observableArrayList(
				"Bubor�krendez�s", "Besz�r�rendez�s", "Shell rendez�s",
				"Gyorsrendez�s", "Kupac rendez�s", "Versenyrendez�s",
				"Radix \"el�re\"");
		algorithmList.setItems(algorithms);
	}

	public static String getSelectedItem() {
		return currentValue;
	}

	public static void setParentController(OverviewController parentController) {
		OverviewListController.parentController = parentController;
	}
}
