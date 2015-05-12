package srtnglgrthms.controller;

import srtnglgrthms.model.algorithm.SortingAlgorithm;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.layout.AnchorPane;

/**
 * 
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public class OverviewDoubleChartController {
	@FXML
	private AnchorPane paneOne;
	@FXML
	private AnchorPane paneTwo;
	@FXML
	private static OverviewChartController controllerOne;
	@FXML
	private static OverviewChartController controllerTwo;
	private BarChart<String, Number> barChartOne;
	private BarChart<String, Number> barChartTwo;
	private static ObservableList<Data<String, Number>> listOne;
	private static ObservableList<Data<String, Number>> listTwo;

	@SuppressWarnings("unchecked")
	@FXML
	private void initialize() {
		controllerOne = new OverviewChartController();
		controllerTwo = new OverviewChartController();
		barChartOne = (BarChart<String, Number>) paneOne.getChildren().get(0);
		barChartTwo = (BarChart<String, Number>) paneTwo.getChildren().get(0);
		controllerOne.setBarChart(barChartOne);
		controllerTwo.setBarChart(barChartTwo);
		listOne = barChartOne.getData().get(0).dataProperty().getValue();
		listTwo = barChartTwo.getData().get(0).dataProperty().getValue();
		for (int i = 0; i < SortingAlgorithm.getNumbers().length; ++i) {
			listTwo.get(i).setYValue(0);
		}
	}
	
	public static void reloadLists() {
		for(int i=0; i < SortingAlgorithm.getNumbers().length; ++i) {
			listOne.get(i).setYValue(SortingAlgorithm.getNumbers()[i]);
			controllerOne.setColor(listOne.get(i).getNode(), "default");
		}
		for (int i = 0; i < SortingAlgorithm.getNumbers().length; ++i) {
			listTwo.get(i).setYValue(0);
		}
	}

	public static ObservableList<Data<String, Number>> getListOne() {
		return listOne;
	}

	public static ObservableList<Data<String, Number>> getListTwo() {
		return listTwo;
	}
}