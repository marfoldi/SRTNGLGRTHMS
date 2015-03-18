package srtnglgrthms.controller;

import srtnglgrthms.model.SortingAlgorithm;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class BarChartController implements ChartController {
	@FXML
	private BarChart<String, Integer> barChart;
	private static Series<String, Integer> series;
	private static Timeline animation;

	@FXML
	private void initialize() {
		series = new Series<>();
		animation = new Timeline();
		animation.setCycleCount(Animation.INDEFINITE);
		initChart();
		setAnimation();
	}

	@Override
	public void initChart() {
		int[] numbers = SortingAlgorithm.getNumbers();
		series.getData().clear();
		barChart.getData().clear();
		for (int i = 0; i < numbers.length; ++i) {
			final XYChart.Data<String, Integer> data = new XYChart.Data<>("t["
					+ i + "]", numbers[i]);
			data.nodeProperty().addListener(new ChangeListener<Node>() {
				public void changed(ObservableValue<? extends Node> ov,
						Node oldNode, final Node node) {
					if (node != null) {
						// setNodeStyle(data);
						displayLegend(data);
					}
				}
			});
			series.getData().add(data);
		}
		SortingAlgorithm.setData(series.getData());
		barChart.getData().add(series);
	}

	@Override
	public void displayLegend(XYChart.Data<String, Integer> data) {
		final Node node = data.getNode();
		Text barValue = new Text(data.getYValue().toString());
		//Text barValue = new Text(Integer.toBinaryString(data.getYValue()));
		node.parentProperty().addListener(new ChangeListener<Parent>() {
			@Override
			public void changed(ObservableValue<? extends Parent> ov,
					Parent oldParent, Parent parent) {
				((Group) parent).getChildren().add(barValue);
			}
		});
		node.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
			@Override
			public void changed(ObservableValue<? extends Bounds> ov,
					Bounds oldBounds, Bounds bounds) {
				barValue.setLayoutX(Math.round(bounds.getMinX()
						+ bounds.getWidth() / 2 - barValue.prefWidth(-1) / 2));
				barValue.setLayoutY(Math.round(bounds.getMinY()
						- barValue.prefHeight(-1) * 0.5));
				barValue.setText(data.getYValue().toString());
				//barValue.setText(Integer.toBinaryString(data.getYValue()));
			}
		});
	}

	private void setAnimation() {
		animation.getKeyFrames().add(
				new KeyFrame(Duration.millis(10),
						new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent actionEvent) {
								(SortingAlgorithmFactory.getAlgorithm(ListViewController.getSelectedItem())).step();
							}
						}));
	}

	public static Series<String, Integer> getSeries() {
		return series;
	}
	
	public static Timeline getAnimation() {
		return animation;
	}
}
