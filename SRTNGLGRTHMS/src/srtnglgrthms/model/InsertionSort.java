package srtnglgrthms.model;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;

public class InsertionSort {
	private static int j = 1;
	private static int i = j-1;

	public static void InsertionStep(ObservableList<XYChart.Data<String,Integer>> data) {
		for (int i = 0; i < data.size(); i++) {
			Node n = data.get(i).getNode();
			n.setStyle("-fx-bar-fill: orange;");
		}
		while ((i < 0) || ( data.get(i).getYValue() <= data.get(i+1).getYValue() )) {
			j++;
			i=j-1;
		}
		if ((i > -1) && ( data.get(i).getYValue() > data.get(i+1).getYValue() )) {
			int temp = data.get(i+1).getYValue();
			data.get(i+1).setYValue(data.get(i).getYValue());
			Node node = data.get(i+1).getNode();
			node.setStyle("-fx-bar-fill: navy;");
			data.get(i).setYValue(temp);
			node = data.get(i).getNode();
			node.setStyle("-fx-bar-fill: red;");
			i--;
		}
	}
}
