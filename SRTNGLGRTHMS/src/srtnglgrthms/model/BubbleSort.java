package srtnglgrthms.model;

import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

public class BubbleSort extends SortingAlgorithm {
	private static int j = 1;
	private static int i = 0;
	private static ObservableList<XYChart.Data<String,Integer>> data = SortingAlgorithm.getData();
	
	public static void BubbleStep() {
		setColor(j-1, "navy");
		setColor(j, "navy");
		if (data.get(j - 1).getYValue() > data.get(j).getYValue()) {
			swap(j-1, j);
		}
		j++;
		if (j == data.size()-i) {
			setColor(j-1, "red");
			for (int i = 0; i < j-1; i++) {
				setColor(i, "orange");
			}
			j = 1;
			i++;
		}
		for (int i = 0; i < j-2; i++) {
			setColor(i, "orange");
		}
	}
}
