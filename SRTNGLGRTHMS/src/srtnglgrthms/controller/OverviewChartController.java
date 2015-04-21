package srtnglgrthms.controller;

import srtnglgrthms.model.algorithm.ChartAlgorithm;
import srtnglgrthms.model.algorithm.GraphAlgorithm;
import srtnglgrthms.model.algorithm.RadixAlgorithm;
import srtnglgrthms.model.algorithm.SortingAlgorithm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.text.Text;

/**
 * 
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public class OverviewChartController {
	@FXML
	private CategoryAxis yAxis = new CategoryAxis();
	@FXML
	private NumberAxis xAxis = new NumberAxis();
	@FXML
	private BarChart<String, Number> barChart = new BarChart<String, Number>(
			yAxis, xAxis);
	private static Series<String, Number> series;
	private static int numbers[];

	@FXML
	private void initialize() {
		xAxis.setAutoRanging(false);
		xAxis.setUpperBound(SortingAlgorithm.getMaximum() - SortingAlgorithm.getMaximum()%10 + 30);
		series = new Series<>();
		setNumbersArray();
		initChart(numbers);
	}

	private void setNumbersArray() {
		if(SortingAlgorithm.getNumbers().length<=100) {
		if(OverviewListController.getSelectedItem() != null) {
			switch(OverviewListController.getSelectedItem()) {
				case "Kupacrendezés" : {
					numbers = GraphAlgorithm.checkLength(SortingAlgorithm.getNumbers(), "Kupacrendezés");
					break;
				}
				case "Versenyrendezés" : {
					numbers = GraphAlgorithm.checkLength(SortingAlgorithm.getNumbers(), "Versenyrendezés");
					break;
				}
				default : {
					numbers = SortingAlgorithm.getNumbers();
					break;
				}
			}
		}
		else numbers = SortingAlgorithm.getNumbers();
		} else numbers = new int[] {0};
	}

	public void initChart(int[] numbers) {
		for (int i = 0; i < numbers.length; ++i) {
			final Data<String, Number> data = new XYChart.Data<>(
					"t[" + i + "]", numbers[i]);
			data.nodeProperty().addListener(new ChangeListener<Node>() {
				public void changed(ObservableValue<? extends Node> ov,
						Node oldNode, final Node node) {
					if (node != null) {
						displayLegend(data);
					}
				}
			});
			series.getData().add(data);
		}
		ChartAlgorithm.setData(series.getData());
		barChart.getData().add(series);
	}

	public static void reloadSeries() {
		for (int i = 0; i < numbers.length; ++i) {
			if(OverviewListController.getSelectedItem().equals("Versenyrendezés")) {
				series.getData().get(i).setYValue(0);
			}
			else series.getData().get(i).setYValue(numbers[i]);
			setColor(series.getData().get(i).getNode(), "default");
		}
	}

	public static void setColor(Node node, String color) {
		node.setOpacity(1);
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
		case "fade":
			node.setOpacity(0.5);
			break;
		default:
			node.setStyle("-fx-bar-fill: " + color.toString() + ";");
			break;
		}
	}

	public static String getRandomColor() {
		return String.format("#%02x%02x%02X", (int) (Math.random() * 256),
				(int) (Math.random() * 256), (int) (Math.random() * 256));
	}

	public void displayLegend(Data<String, Number> data) {
		final Node node = data.getNode();
		Text barValue;
		if (OverviewListController.getSelectedItem() != null
				&& (OverviewListController.getSelectedItem().equals(
						"Radix \"elõre\"") || OverviewListController
						.getSelectedItem().equals("Radix \"vissza\""))) {
			barValue = new Text(RadixAlgorithm.fillWithZeros(Integer
					.toBinaryString((int) data.getYValue())));
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
							.toBinaryString((int) data.getYValue())));
				} else
					barValue.setText(data.getYValue().toString());
			}
		});
	}

	public static Series<String, Number> getSeries() {
		return series;
	}

	public BarChart<String, Number> getBarChart() {
		return barChart;
	}

	public void setBarChart(BarChart<String, Number> barChart) {
		this.barChart = barChart;
	}
}
