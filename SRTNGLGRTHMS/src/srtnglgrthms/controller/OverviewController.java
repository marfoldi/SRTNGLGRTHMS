package srtnglgrthms.controller;

import srtnglgrthms.model.SortingAlgorithmFactory;
import srtnglgrthms.model.algorithm.ChartAlgorithm;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;

public class OverviewController {
	@FXML
	private Button stepBtn;
	@FXML
	private Button animBtn;
	@FXML
	public SplitPane displayPane;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		stepBtn.setDisable(true);
		animBtn.setDisable(true);
		initBtns();
		OverviewListController.setParentController(this);
		OverviewChartController.setParentController(this);
	}

	private void initBtns() {
		stepBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				switch(stepBtn.getText()) {
					case "Léptetés": {
						(SortingAlgorithmFactory.getAlgorithm(OverviewListController.getSelectedItem())).step();
						checkButtons();
						break;
					}
					case "Újraindítás": {
						OverviewChartController.reloadSeries();
				        SortingAlgorithmFactory.getAlgorithm(OverviewListController.getSelectedItem()).setDefaults();
				        reloadButtons();
				        break;
					}
				}
			}
		});

		animBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				OverviewChartController.getAnimation().play();
			}
		});
	}
	
	protected void checkButtons() {
		boolean isDone = true;
		for (int i = 0; i < ChartAlgorithm.getData().size(); i++) {
			if(!(ChartAlgorithm.getData().get(i).getNode().getStyle().contains("-fx-bar-fill: #8C2D46;"))) {
				isDone = false;
				break;
			}
		}
		if(isDone) {
			stepBtn.setText("Újraindítás");
			OverviewChartController.getAnimation().stop();
			animBtn.setVisible(false);
		}
	}
	
	protected void reloadButtons() {
		stepBtn.setDisable(false);
		animBtn.setDisable(false);
		stepBtn.setText("Léptetés");
		animBtn.setVisible(true);
	}
}