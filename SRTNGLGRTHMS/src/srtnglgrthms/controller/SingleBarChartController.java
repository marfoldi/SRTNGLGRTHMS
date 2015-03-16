package srtnglgrthms.controller;

import srtnglgrthms.model.BubbleSort;
import srtnglgrthms.model.SortingAlgorithm;
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

public class SingleBarChartController implements ChartController {
	@FXML
	private BarChart<String, Integer> barChart;
	private static Series<String, Integer> series;
	private static Timeline animation;

	@FXML
	private void initialize() {
		series = new Series<>();
		animation = new Timeline();
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
						displayLabelForData(data);
					}
				}
			});
			series.getData().add(data);
		}
		SortingAlgorithm.setData(series.getData());
		barChart.getData().add(series);
	}

	@Override
	public void displayLabelForData(XYChart.Data<String, Integer> data) {
		final Node node = data.getNode();
		Text dataText = new Text(data.getYValue().toString());
		node.parentProperty().addListener(new ChangeListener<Parent>() {
			@Override
			public void changed(ObservableValue<? extends Parent> ov,
					Parent oldParent, Parent parent) {
				Group parentGroup = (Group) parent;
				parentGroup.getChildren().add(dataText);
			}
		});
		node.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
			@Override
			public void changed(ObservableValue<? extends Bounds> ov,
					Bounds oldBounds, Bounds bounds) {
				dataText.setLayoutX(Math.round(bounds.getMinX()
						+ bounds.getWidth() / 2 - dataText.prefWidth(-1) / 2));
				dataText.setLayoutY(Math.round(bounds.getMinY()
						- dataText.prefHeight(-1) * 0.5));
				dataText.setText(data.getYValue().toString());
			}
		});
	}

	private void setAnimation() {
		animation.getKeyFrames().add(
				new KeyFrame(Duration.millis(10),
						new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent actionEvent) {
								BubbleSort.BubbleStep();
							}
						}));
	}

	public static Series<String, Integer> getSeries() {
		return series;
	}
}
