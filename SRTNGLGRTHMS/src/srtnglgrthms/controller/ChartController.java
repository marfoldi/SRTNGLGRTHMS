package srtnglgrthms.controller;

import javafx.scene.chart.XYChart;

public interface ChartController {
	public void initChart();
	public void displayLegend(XYChart.Data<String, Integer> data);
}
