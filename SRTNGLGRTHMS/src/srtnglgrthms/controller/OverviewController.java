package srtnglgrthms.controller;

import java.util.Arrays;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.util.Duration;
import srtnglgrthms.model.BubbleSort;

public class OverviewController {
	//@FXML
	//private BarChart<String, Integer> barChart;
	@FXML
	private Button stepBtn;
	@FXML
	private Button animBtn;
	@FXML
	private ListView<String> algorithmList;

	XYChart.Series<String, Integer> series;
	Timeline tl;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		series = SingleBarChartController.getSeries();
		tl = new Timeline();
		initBtns();
		initList();
		setAnimation();
	}

	private void initList() {
		List<String> values = Arrays.asList("Buborékrendezés",
				"Beszúró rendezés", "Kupacrendezés", "Versenyrendezés",
				"Gyorsrendezés", "Shell rendezés", "Radix \"elõre\"",
				"Radix \"hátra\"");
		algorithmList.setItems(FXCollections.observableList(values));
	}

	private void setAnimation() {
		tl.getKeyFrames().add(
				new KeyFrame(Duration.millis(10),
						new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent actionEvent) {
								 BubbleSort.BubbleStep();
								// InsertionSort.InsertionStep(series.getData());
								//ForwardRadix.RadixStep(series.getData());
							}
						}));
	}
	
	private void initBtns() {
		stepBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				 BubbleSort.BubbleStep();
				// InsertionSort.InsertionStep(series.getData());
				//ForwardRadix.RadixStep(series.getData());
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