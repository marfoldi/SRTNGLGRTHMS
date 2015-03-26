package srtnglgrthms.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import srtnglgrthms.model.BenchmarkData;
import srtnglgrthms.model.SortingAlgorithm;

public class BenchmarkTableController {
	@FXML
	private TableView<BenchmarkData> tableView;
	@FXML
	private TableColumn<BenchmarkData, String> nameColumn;
	@FXML
	private TableColumn<BenchmarkData, Number> compareColumn;
	@FXML
	private TableColumn<BenchmarkData, Number> swapColumn;
	
	@FXML
    private void initialize() {
        // Initialize the info table with the two columns.
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		compareColumn.setCellValueFactory(cellData -> cellData.getValue().compareCounterProperty());
		swapColumn.setCellValueFactory(cellData -> cellData.getValue().swapCounterProperty());
		tableView.setItems(SortingAlgorithm.getBenchmarkData());
		setMouseAction();
    }
	
	private void setMouseAction() {
		tableView.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		            BenchmarkChartController.addElement(tableView.getSelectionModel().getSelectedItem());
		        }
		    }
		});
	}
}
