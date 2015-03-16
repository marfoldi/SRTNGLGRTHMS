package srtnglgrthms.model;

import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.Node;

public class BubbleSort extends SortingAlgorithm {
	private static int j = 1;
	private static int i = 0;
	private static ObservableList<XYChart.Data<String,Integer>> data = SortingAlgorithm.getData();
	
	public static void BubbleStep() {
		setColor(j-1, j, "navy", "navy");
		if (data.get(j - 1).getYValue() > data.get(j).getYValue()) {
			swap(j-1, j);
		}
		j++;
		if (j == data.size()-i) {
			Node n = data.get(j-1).getNode();
			n.setStyle("-fx-bar-fill: red;");
			for (int i = 0; i < j-1; i++) {
				n = data.get(i).getNode();
				n.setStyle("-fx-bar-fill: orange;");
			}
			j = 1;
			i++;
		}
		for (int i = 0; i < j-2; i++) {
			Node n = data.get(i).getNode();
			n.setStyle("-fx-bar-fill: orange;");
		}
	}
}
