package srtnglgrthms.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class OverviewController {
	@FXML
	private Button stepBtn;
	@FXML
	private Button animBtn;
	
	Timeline tl;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		tl = new Timeline();
		initBtns();
		setAnimation();
	}

	private void setAnimation() {
		tl.getKeyFrames().add(
				new KeyFrame(Duration.millis(10),
						new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent actionEvent) {
								ListViewController.getMap().get(ListViewController.getSelectedItem()).run();
							}
						}));
	}
	
	private void initBtns() {
		stepBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ListViewController.getMap().get(ListViewController.getSelectedItem()).run();
			}
		});

		animBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tl.setCycleCount(Animation.INDEFINITE);
				tl.play();
			}
		});
	}
}