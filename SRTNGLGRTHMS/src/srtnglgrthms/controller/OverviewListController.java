package srtnglgrthms.controller;

import srtnglgrthms.model.SortingAlgorithmFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class OverviewListController {
	@FXML
	private ListView<String> algorithmList;
	@FXML 
	private static OverviewController parentController;
	private static String currentValue;
	
	@FXML
	private void initialize() {
		setList();
		algorithmList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		    	OverviewListController.currentValue = newValue;
		        OverviewChartController.reloadSeries();
		        
		        parentController.reloadButtons();
		        SortingAlgorithmFactory.getAlgorithm(currentValue).setDefaults();
		    }
		});
	}
	
	private void setList() {
		ObservableList<String> algorithms =FXCollections.observableArrayList (
			    "Buborékrendezés", "Beszúrórendezés", "Shell rendezés", "Gyorsrendezés", "Radix \"elõre\"");
		algorithmList.setItems(algorithms);
	}
	
	public static String getSelectedItem() {
		return currentValue;
	}
	
	public static void setParentController(OverviewController parentController) {
		OverviewListController.parentController=parentController;
	}
}
