package srtnglgrthms.model;

import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.Node;

public class BubbleSort {
	private static int j = 1;
	private static int i = 0;
	public static void BubbleStep(ObservableList<XYChart.Data<String,Integer>> data) {
		int temp;
		Node node = data.get(j - 1).getNode();
		node.setStyle("-fx-bar-fill: navy;");
		node = data.get(j).getNode();
		node.setStyle("-fx-bar-fill: navy;");
		if (data.get(j - 1).getYValue() > data.get(j)
				.getYValue()) {
			temp = data.get(j - 1).getYValue();
			data.get(j - 1)
					.setYValue(data.get(j).getYValue());
			data.get(j).setYValue(temp);
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
