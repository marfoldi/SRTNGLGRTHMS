package srtnglgrthms.controller;

import srtnglgrthms.model.CounterData;
import srtnglgrthms.model.algorithm.SortingAlgorithm;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OverviewTableController {
	@FXML
	private TableView<CounterData> tableView;
	@FXML
	private TableColumn<CounterData, String> nameColumn;
	@FXML
	private TableColumn<CounterData, String> valueColumn;
	
	@FXML
    private void initialize() {
        // Initialize the counter info table with the two columns.
		tableView.setPlaceholder(new Label("Nincs megjelen�thet� tulajdons�g"));
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		valueColumn.setCellValueFactory(cellData -> cellData.getValue().valueProperty());
		tableView.setItems(SortingAlgorithm.getCounterData());
    }
}