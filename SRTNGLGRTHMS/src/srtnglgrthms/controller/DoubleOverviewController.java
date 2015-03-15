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
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.util.Duration;
import srtnglgrthms.model.Algorithms;
import srtnglgrthms.model.BackwardRadix;
import srtnglgrthms.model.ForwardRadix;

public class DoubleOverviewController {
	@FXML
	private BarChart<String, Integer> barChart;
	@FXML
	private BarChart<String, Integer> barChart2;
	@FXML
	private Button stepBtn;
	@FXML
	private Button animBtn;
	@FXML
	private ListView<String> algorithmList;

	XYChart.Series<String, Integer> series;
	XYChart.Series<String, Integer> series2;
	Timeline tl;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		series = new XYChart.Series<>();
		series2 = new XYChart.Series<>();
		tl = new Timeline();
		initChart();
		initBtns();
		initList();
		setAnimation();
	}
	
	private void initChart() {
		int[] numbers = Algorithms.getNumbers();
		for(int i=0; i<numbers.length; ++i) {
			series.getData().add(new XYChart.Data<>("t[" + i + "]", numbers[i]));
			series2.getData().add(new XYChart.Data<>("t[" + i + "]", 0));
		}
		
		barChart.getData().add(series);
		barChart2.getData().add(series2);
		for (int i = 0; i < series.getData().size(); i++) {
			Node n = series.getData().get(i).getNode();
			n.setStyle("-fx-bar-fill: orange;");
		}

	}
	
	
	private void initList() {
        List<String> values = Arrays.asList(
        		"Buborékrendezés", "Beszúró rendezés", "Kupacrendezés", "Versenyrendezés", "Gyorsrendezés", "Shell rendezés",
        		"Radix \"elõre\"", "Radix \"hátra\"");
        algorithmList.setItems(FXCollections.observableList(values));
	}
	
	private void setAnimation() {
		tl.getKeyFrames().add(
				new KeyFrame(Duration.millis(100),
						new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent actionEvent) {
								//BubbleSort.BubbleStep(series.getData());
								//InsertionSort.InsertionStep(series.getData());
								BackwardRadix.RadixStep(series.getData(), series2.getData());
							}
						}));
	}
	
	private void initBtns() {	
		stepBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//BubbleSort.BubbleStep(series.getData());
				//InsertionSort.InsertionStep(series.getData());
				BackwardRadix.RadixStep(series.getData(), series2.getData());
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