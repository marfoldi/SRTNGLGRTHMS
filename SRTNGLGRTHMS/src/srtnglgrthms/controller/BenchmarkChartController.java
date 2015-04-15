package srtnglgrthms.controller;

import java.util.ArrayList;
import java.util.List;

import srtnglgrthms.model.BenchmarkData;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

public class BenchmarkChartController implements ChartController{
	@FXML
	private BarChart<String, Integer> barChart;
	private static Series<String, Integer> compareCounter;
	private static Series<String, Integer> swapCounter;
	private static List<String> loadedItems;
	
	@FXML
	private void initialize() {
		compareCounter = new Series<>();
		swapCounter = new Series<>();
		loadedItems = new ArrayList<>();
		initChart();
	}

	@SuppressWarnings("unchecked")
	public void initChart() {
		barChart.getData().addAll(compareCounter, swapCounter);
	}
	
	public static void addElement(BenchmarkData data) {
		if(!alreadyLoaded(data.getName())) {
			loadedItems.add(data.getName());
			compareCounter.getData().add(new XYChart.Data<String, Integer>(data.getName(), data.getCompareCounter()));
			swapCounter.getData().add(new XYChart.Data<String, Integer>(data.getName(), data.getSwapCounter()));
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
			swapCounter.getData().remove(idx);
		}
	}
	
	private static boolean alreadyLoaded(String name) {
		if(loadedItems.contains(name)) return true;
		else return false;
	}

	@Override
	public void displayLegend(Data<String, Number> data) {
		// TODO Auto-generated method stub
	}
	
}
