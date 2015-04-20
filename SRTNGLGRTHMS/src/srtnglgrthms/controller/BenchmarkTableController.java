package srtnglgrthms.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import srtnglgrthms.model.BenchmarkData;

public class BenchmarkTableController {
	@FXML
	private TableView<BenchmarkData> tableView;
	@FXML
	private TableColumn<BenchmarkData, String> nameColumn;
	@FXML
	private TableColumn<BenchmarkData, Number> compareColumn;
	@FXML
	private TableColumn<BenchmarkData, Number> moveColumn;
	@FXML
	private TableColumn<BenchmarkData, Number> swapColumn;

	@FXML
    private void initialize() {
        // Initialize the info table with the two columns.
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		compareColumn.setCellValueFactory(cellData -> cellData.getValue().compareCounterProperty());
		moveColumn.setCellValueFactory(cellData -> cellData.getValue().moveCounterProperty());
		swapColumn.setCellValueFactory(cellData -> cellData.getValue().swapCounterProperty());
		//tableView.setItems(SortingAlgorithm.getBenchmarkData());
		BenchmarkController.setTableController(this);
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
	
	public TableView<BenchmarkData> getTableView() {
		return tableView;
	}
}
