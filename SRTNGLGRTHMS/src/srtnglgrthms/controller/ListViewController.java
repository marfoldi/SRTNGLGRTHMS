package srtnglgrthms.controller;

import java.util.HashMap;
import java.util.Map;

import srtnglgrthms.model.BubbleSort;
import srtnglgrthms.model.InsertionSort;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ListViewController {
	@FXML
	private ListView<String> algorithmList;
	private static Map<String, Runnable> algorithmMap;
	private static String currentItem;
	
	@FXML
	private void initialize() {
		createMap();
		setList();
		algorithmList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		        currentItem = newValue;
		    }
		});
	}
	
	private void createMap() {
		algorithmMap = new HashMap<>();
		algorithmMap.put("Bubor�krendez�s", ()-> {BubbleSort.BubbleStep();});
		algorithmMap.put("Besz�r�rendez�s", ()-> {InsertionSort.InsertionStep();});
		/*algorithms.put("Kupacrendez�s", null);
		algorithms.put("Versenyrendez�s", null);
		algorithms.put("Gyorsrendez�s", null);
		algorithms.put("Shell rendez�s", null);
		algorithms.put("Radix \"el�re\"", null);
		algorithms.put("Radix \"h�tra\"", null);*/
	}
	
	private void setList() {
		algorithmList.setItems(FXCollections.observableArrayList(algorithmMap.keySet()));
	}
	
	public static Map<String, Runnable> getMap() {
		return algorithmMap;
	}
	
	public static String getSelectedItem() {
		return currentItem;
	}
}
