package srtnglgrthms.controller;

import srtnglgrthms.model.CounterData;
import srtnglgrthms.model.algorithm.SortingAlgorithm;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class InfoTableController {
	@FXML
	private TableView<CounterData> tableView;
	@FXML
	private TableColumn<CounterData, String> nameColumn;
	@FXML
	private TableColumn<CounterData, String> valueColumn;
	
	@FXML
    private void initialize() {
        // Initialize the info table with the two columns.
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		valueColumn.setCellValueFactory(cellData -> cellData.getValue().valueProperty());
		tableView.setItems(SortingAlgorithm.getCounterData());
    }
}
