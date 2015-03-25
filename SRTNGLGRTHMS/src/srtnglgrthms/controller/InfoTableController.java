package srtnglgrthms.controller;

import srtnglgrthms.model.Counter;
import srtnglgrthms.model.SortingAlgorithm;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class InfoTableController {
	@FXML
	private TableView<Counter> tableView;
	@FXML
	private TableColumn<Counter, String> nameColumn;
	@FXML
	private TableColumn<Counter, Number> valueColumn;
	
	@FXML
    private void initialize() {
        // Initialize the info table with the two columns.
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		valueColumn.setCellValueFactory(cellData -> cellData.getValue().valueProperty());
		tableView.setItems(SortingAlgorithm.getCounterData());
    }
}
