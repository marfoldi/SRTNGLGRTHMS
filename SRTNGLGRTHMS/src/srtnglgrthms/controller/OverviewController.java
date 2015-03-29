package srtnglgrthms.controller;

import srtnglgrthms.model.SortingAlgorithm;
import srtnglgrthms.model.SortingAlgorithmFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class OverviewController {
	@FXML
	public Button stepBtn;
	@FXML
	public Button animBtn;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
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
		for (int i = 0; i < SortingAlgorithm.getData().size(); i++) {
			if(!(SortingAlgorithm.getData().get(i).getNode().getStyle().contains("-fx-bar-fill: #8C2D46;"))) {
				isDone = false;
				break;
			}
		}
		if(isDone) {
			stepBtn.setText("Újraindítás");
			animBtn.setVisible(false);
		}
	}
	
	protected void reloadButtons() {
		stepBtn.setText("Léptetés");
		animBtn.setVisible(true);
	}
	
	
}