package srtnglgrthms.controller;

import srtnglgrthms.model.SortingAlgorithmFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ListViewController {
	@FXML
	private ListView<String> algorithmList;
	private static String currentItem;
	
	@FXML
	private void initialize() {
		setList();
		algorithmList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		        currentItem = newValue;
		        BarChartController.reloadSeries();
		        SortingAlgorithmFactory.getAlgorithm(currentItem).setDefaults();
		    }
		});
	}
	
	private void setList() {
		ObservableList<String> algorithms =FXCollections.observableArrayList (
			    "Buborékrendezés", "Beszúrórendezés", "Shell rendezés", "Gyorsrendezés", "Radix \"elõre\"");
		algorithmList.setItems(algorithms);
	}
	
	public static String getSelectedItem() {
		return currentItem;
	}
}
