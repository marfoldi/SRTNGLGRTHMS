package srtnglgrthms.controller;

import srtnglgrthms.model.SortingAlgorithmFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class OverviewController {
	@FXML
	private Button stepBtn;
	@FXML
	private Button animBtn;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		initBtns();
	}

	private void initBtns() {
		stepBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				(SortingAlgorithmFactory.getAlgorithm(ListViewController.getSelectedItem())).step();
			}
		});

		animBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				BarChartController.getAnimation().play();
			}
		});
	}
	
	
}