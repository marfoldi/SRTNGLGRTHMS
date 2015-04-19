package srtnglgrthms.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import srtnglgrthms.model.BenchmarkData;
import srtnglgrthms.model.algorithm.SortingAlgorithm;

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
		try {
			nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
			compareColumn.setCellValueFactory(cellData -> cellData.getValue().compareCounterProperty());
			swapColumn.setCellValueFactory(cellData -> cellData.getValue().swapCounterProperty());
			tableView.setItems(SortingAlgorithm.getBenchmarkData());
			setMouseAction();
		} catch(NullPointerException npe) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			initialize();
		}
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
