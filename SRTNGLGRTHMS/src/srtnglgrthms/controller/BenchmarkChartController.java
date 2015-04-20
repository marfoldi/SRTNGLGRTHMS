package srtnglgrthms.controller;

import java.util.ArrayList;
import java.util.List;

import srtnglgrthms.model.BenchmarkData;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public class BenchmarkChartController {
	@FXML
	private BarChart<String, Integer> barChart;
	private static Series<String, Integer> compareCounter;
	private static Series<String, Integer> moveCounter;
	private static List<String> loadedItems;

	@FXML
	private void initialize() {
		compareCounter = new Series<>();
		moveCounter = new Series<>();
		loadedItems = new ArrayList<>();
		initChart();
	}

	@SuppressWarnings("unchecked")
	public void initChart() {
		compareCounter.setName("Összehasonlítások");
		moveCounter.setName("Mozgatások");
		barChart.setLegendVisible(true);
		barChart.getData().addAll(compareCounter, moveCounter);
	}

	public static void addElement(BenchmarkData data) {
		if(!alreadyLoaded(data.getName())) {
			loadedItems.add(data.getName());
			compareCounter.getData().add(new XYChart.Data<String, Integer>(data.getName(), data.getCompareCounter()));
			moveCounter.getData().add(new XYChart.Data<String, Integer>(data.getName(), data.getMoveCounter()));
		}
		else {
			int idx = 0;
			for(int i=0; i<loadedItems.size(); ++i) {
				if (loadedItems.get(i)==data.getName()) {
					idx = i;
				}
			}
			loadedItems.remove(idx);
			compareCounter.getData().remove(idx);
			moveCounter.getData().remove(idx);
		}
	}

	private static boolean alreadyLoaded(String name) {
		if(loadedItems.contains(name)) return true;
		else return false;
	}
}
