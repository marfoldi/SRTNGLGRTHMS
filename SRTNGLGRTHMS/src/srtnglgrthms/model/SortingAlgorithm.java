package srtnglgrthms.model;

import java.util.Queue;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;

public abstract class SortingAlgorithm {
	private static int[] numbers;
	protected static ObservableList<XYChart.Data<String,Integer>> data;
	protected static Queue<RecursiveParameter> recursiveCall;
	
	protected static void swap(int firstIndex, int secondIndex) {
		int temp;
		temp = data.get(firstIndex).getYValue();
		data.get(firstIndex).setYValue(data.get(secondIndex).getYValue());
		data.get(secondIndex).setYValue(temp);
	}
	
	protected static void setColor(int index, String color) {
		Node node = data.get(index).getNode();
		if(color.equals("default")) {
			node.setStyle("-fx-bar-fill: #f3622d");
		}
		else node.setStyle("-fx-bar-fill: " + color.toString() + ";");
	}
	
	protected static void setColor(int index, Color color) {
		Node node = data.get(index).getNode();
		node.setStyle("-fx-bar-fill: " + color.toString() + ";");
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
	
	public abstract void step();
}
