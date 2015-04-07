package srtnglgrthms.controller;

import srtnglgrthms.model.RadixAlgorithm;
import srtnglgrthms.model.SortingAlgorithm;
import srtnglgrthms.model.SortingAlgorithmFactory;
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

public class OverviewChartController implements ChartController {
	@FXML
	private BarChart<String, Integer> barChart;
	@FXML
	private static OverviewController parentController;
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
		/*
		 * Node chartArea = barChart.lookup(".chart-plot-background"); Bounds
		 * chartAreaBounds =
		 * chartArea.localToScene(chartArea.getBoundsInLocal());
		 * System.out.println(chartAreaBounds);
		 */
		barChart.getData().add(series);
	}

	public static void reloadSeries() {
		int[] numbers = SortingAlgorithm.getNumbers();
		for (int i = 0; i < numbers.length; ++i) {
			series.getData().get(i).setYValue(numbers[i]);
			setColor(series.getData().get(i).getNode(), "default");
		}
	}

	public static void setColor(Node node, String color) {
		switch (color) {
		case "default":
			node.setStyle("-fx-bar-fill: #f3622d;");
			break;
		case "swap":
			node.setStyle("-fx-bar-fill: #4258c9;");
			break;
		case "select":
			node.setStyle("-fx-bar-fill: #57b757;");
			break;
		case "done":
			node.setStyle("-fx-bar-fill: #8C2D46;");
			break;
		default:
			node.setStyle("-fx-bar-fill: " + color.toString() + ";");
			break;
		}

	}

	public static String getRandomColor() {
		return "#" + Integer.toHexString((int) (Math.random() * 16777215));
	}

	@Override
	public void displayLegend(XYChart.Data<String, Integer> data) {
		final Node node = data.getNode();
		Text barValue;
		if (OverviewListController.getSelectedItem() != null
				&& (OverviewListController.getSelectedItem().equals(
						"Radix \"elõre\"") || OverviewListController
						.getSelectedItem().equals("Radix \"vissza\""))) {
			barValue = new Text(RadixAlgorithm.fillWithZeros(Integer.toBinaryString(data
					.getYValue())));
		} else
			barValue = new Text(data.getYValue().toString());
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
						- barValue.prefHeight(-1) * 0.3));
				if (OverviewListController.getSelectedItem() != null
						&& (OverviewListController.getSelectedItem().equals(
								"Radix \"elõre\"") || OverviewListController
								.getSelectedItem().equals("Radix \"vissza\""))) {
					barValue.setText(RadixAlgorithm.fillWithZeros(Integer
							.toBinaryString(data.getYValue())));
				} else
					barValue.setText(data.getYValue().toString());
			}
		});
	}

	private void setAnimation() {
		animation.getKeyFrames().add(
				new KeyFrame(Duration.millis(10),
						new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent actionEvent) {
								(SortingAlgorithmFactory
										.getAlgorithm(OverviewListController
												.getSelectedItem())).step();
								parentController.checkButtons();
							}
						}));
	}

	public static void setParentController(OverviewController parentController) {
		OverviewChartController.parentController = parentController;
	}

	public static Series<String, Integer> getSeries() {
		return series;
	}

	public static Timeline getAnimation() {
		return animation;
	}
}
