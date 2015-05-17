package srtnglgrthms.controller;

import java.util.ArrayList;
import java.util.List;

import srtnglgrthms.model.algorithm.raw.BenchmarkData;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

/**
 * 
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public class BenchmarkChartController {
	@FXML
	private BarChart<String, Long> barChart;
	private static Series<String, Long> compareCounter;
	private static Series<String, Long> moveCounter;
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
		if (!loadedItems.contains(data.getName())) {
			loadedItems.add(data.getName());
			compareCounter.getData().add(
					new XYChart.Data<String, Long>(data.getName(), data
							.getCompareCounter()));
			moveCounter.getData().add(
					new XYChart.Data<String, Long>(data.getName(), data
							.getMoveCounter()));
		} else {
			int idx = 0;
			for (int i = 0; i < loadedItems.size(); ++i) {
				if (loadedItems.get(i) == data.getName()) {
					idx = i;
				}
			}
			loadedItems.remove(idx);
			compareCounter.getData().remove(idx);
			moveCounter.getData().remove(idx);
		}
	}
}
