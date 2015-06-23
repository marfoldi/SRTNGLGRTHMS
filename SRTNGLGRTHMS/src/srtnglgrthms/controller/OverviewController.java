package srtnglgrthms.controller;

import srtnglgrthms.model.algorithm.ChartAlgorithm;
import srtnglgrthms.model.algorithm.SortingAlgorithm;
import srtnglgrthms.model.algorithm.SortingAlgorithmFactory;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.util.Duration;

/**
 * 
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public class OverviewController {
	@FXML
	private Button stepBtn;
	@FXML
	private Button animBtn;
	@FXML
	public SplitPane displayPane;
	private static Timeline animation;
	private static int animationSpeed;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		stepBtn.setDisable(true);
		animBtn.setDisable(true);
		animation = new Timeline();
		animation.setCycleCount(Animation.INDEFINITE);
		animationSpeed = (int) ((1.0/SortingAlgorithm.getNumbers().length)*5000);
		setAnimation();
		initBtns();
		OverviewListController.setParentController(this);
	}

	private void initBtns() {
		stepBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				switch (stepBtn.getText()) {
				case "Léptetés": {
					(SortingAlgorithmFactory
							.getAlgorithm(OverviewListController
									.getSelectedItem())).step();
					checkButtons();
					break;
				}
				case "Újraindítás": {
					OverviewChartController.reloadSeries();
					SortingAlgorithmFactory.getAlgorithm(
							OverviewListController.getSelectedItem())
							.setDefaults();
					reloadButtons();
					break;
				}
				}
			}
		});

		animBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				switch (animBtn.getText()) {
				case "Lejátszás": {
					animation.play();
					animBtn.setText("Megállítás");
					break;
				}
				case "Megállítás": {
					animation.stop();
					animBtn.setText("Lejátszás");
					break;
				}
				}
			}
		});
	}

	private void setAnimation() {
		animation.getKeyFrames().add(
				new KeyFrame(Duration.millis(animationSpeed),
						new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent actionEvent) {
								(SortingAlgorithmFactory
										.getAlgorithm(OverviewListController
												.getSelectedItem())).step();
								checkButtons();
							}
						}));
	}

	protected void checkButtons() {
		boolean isDone = true;
		for (int i = 0; i < ChartAlgorithm.getData().size(); i++) {
			if (!(ChartAlgorithm.getData().get(i).getNode().getStyle()
					.contains("-fx-bar-fill: #8C2D46;"))) {
				isDone = false;
				break;
			}
		}
		if (isDone) {
			stepBtn.setText("Újraindítás");
			animation.stop();
			animBtn.setVisible(false);
		}
	}

	protected void reloadButtons() {
		stepBtn.setDisable(false);
		animBtn.setDisable(false);
		stepBtn.setText("Léptetés");
		animBtn.setText("Lejátszás");
		animBtn.setVisible(true);
	}

	public static Timeline getAnimation() {
		return animation;
	}
}