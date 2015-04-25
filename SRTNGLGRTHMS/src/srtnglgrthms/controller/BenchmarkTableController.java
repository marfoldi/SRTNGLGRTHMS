package srtnglgrthms.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import srtnglgrthms.model.BenchmarkData;

/**
 * 
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
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
	private static String selectedItem;

	@FXML
	private void initialize() {
		// Initialize the info table with the two columns.
		tableView.setPlaceholder(new Label("Az adatok betöltése folyamatban van..."));
		nameColumn.prefWidthProperty().bind(
				tableView.widthProperty().multiply(0.328));
		compareColumn.prefWidthProperty().bind(
				tableView.widthProperty().multiply(0.255));
		moveColumn.prefWidthProperty().bind(
				tableView.widthProperty().multiply(0.250));
		swapColumn.prefWidthProperty().bind(
				tableView.widthProperty().multiply(0.158));

		nameColumn.setCellValueFactory(cellData -> cellData.getValue()
				.nameProperty());
		compareColumn.setCellValueFactory(cellData -> cellData.getValue()
				.compareCounterProperty());
		moveColumn.setCellValueFactory(cellData -> cellData.getValue()
				.moveCounterProperty());
		swapColumn.setCellValueFactory(cellData -> cellData.getValue()
				.swapCounterProperty());
		// tableView.setItems(SortingAlgorithm.getBenchmarkData());
		BenchmarkController.setTableController(this);
		setMouseAction();
		tableView.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Object>() {
					@Override
					public void changed(ObservableValue<?> observableValue,
							Object oldValue, Object newValue) {
						// Check whether item is selected and set value of
						// selected item to Label
						if (tableView.getSelectionModel().getSelectedItem() != null) {
							selectedItem = tableView.getSelectionModel()
									.getSelectedItem().getName();
						}
					}
				});
	}

	private void setMouseAction() {
		tableView.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					BenchmarkChartController.addElement(tableView
							.getSelectionModel().getSelectedItem());
				}
			}
		});
	}

	public TableView<BenchmarkData> getTableView() {
		return tableView;
	}

	public static String getSelectedItem() {
		return selectedItem;
	}
}
