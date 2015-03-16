package srtnglgrthms.model;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;

public class Algorithm {
	private static int[] numbers;
	private static ObservableList<XYChart.Data<String,Integer>> data;
	
	protected static void swap(int firstIndex, int secondIndex) {
		int temp;
		temp = data.get(firstIndex).getYValue();
		data.get(firstIndex).setYValue(data.get(secondIndex).getYValue());
		data.get(secondIndex).setYValue(temp);
	}
	
	protected static void setColor(int firstIndex, int secondIndex) {
		Node node = data.get(firstIndex).getNode();
		node.setStyle("-fx-bar-fill: navy;");
		node = data.get(secondIndex).getNode();
		node.setStyle("-fx-bar-fill: navy;");
	}

	public static int[] getNumbers() {
		return numbers;
	}
	
	public static void setNumbers(int[] numbers) {
		Algorithm.numbers = numbers;
	}
	
	public static void setData(ObservableList<XYChart.Data<String,Integer>> data) {
		Algorithm.data = data;
	}
	
	public static ObservableList<XYChart.Data<String,Integer>> getData() {
		return data;
	}
}
