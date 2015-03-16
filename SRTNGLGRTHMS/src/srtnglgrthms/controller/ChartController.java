package srtnglgrthms.controller;

import javafx.scene.chart.XYChart;

public interface ChartController {
	public void initChart();
	public void displayLabelForData(XYChart.Data<String, Integer> data);
}
