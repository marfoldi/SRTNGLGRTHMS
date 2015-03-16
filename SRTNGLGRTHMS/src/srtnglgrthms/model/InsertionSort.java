package srtnglgrthms.model;

import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

public class InsertionSort extends SortingAlgorithm{
	private static int j = 1;
	private static int i = j-1;
	private static ObservableList<XYChart.Data<String,Integer>> data = SortingAlgorithm.getData();

	public static void InsertionStep() {
		while ((i < 0) || ( data.get(i).getYValue() <= data.get(i+1).getYValue() )) {
			j++;
			i=j-1;
		}
		if ((i > -1) && ( data.get(i).getYValue() > data.get(i+1).getYValue() )) {
			swap(i+1, i);
			setColor(i+1, i, "navy", "red");
			i--;
		}
	}
}
