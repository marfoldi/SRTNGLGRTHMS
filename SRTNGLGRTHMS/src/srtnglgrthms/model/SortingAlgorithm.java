package srtnglgrthms.model;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;

public class SortingAlgorithm {
	private static int[] numbers;
	private static ObservableList<XYChart.Data<String,Integer>> data;
	
	protected static void swap(int firstIndex, int secondIndex) {
		int temp;
		temp = data.get(firstIndex).getYValue();
		data.get(firstIndex).setYValue(data.get(secondIndex).getYValue());
		data.get(secondIndex).setYValue(temp);
	}
	
	protected static void setColor(int index, String color) {
		Node node = data.get(index).getNode();
		node.setStyle("-fx-bar-fill: " + color + ";");
	}

	public static int[] getNumbers() {
		return numbers;
	}
	
	public static void setNumbers(int[] numbers) {
		SortingAlgorithm.numbers = numbers;
	}
	
	public static void setData(ObservableList<XYChart.Data<String,Integer>> data) {
		SortingAlgorithm.data = data;
	}
	
	public static ObservableList<XYChart.Data<String,Integer>> getData() {
		return data;
	}
}
