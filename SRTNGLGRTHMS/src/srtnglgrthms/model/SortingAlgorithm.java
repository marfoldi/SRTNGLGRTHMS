package srtnglgrthms.model;

import java.util.Queue;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;

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
		switch(color) {
			case "default":	node.setStyle("-fx-bar-fill: #f3622d;");
							break;
			case "swap":	node.setStyle("-fx-bar-fill: #4258c9;");
							break;
			case "select":	node.setStyle("-fx-bar-fill: #57b757;");
							break;
			case "done":	node.setStyle("-fx-bar-fill: #8C2D46;");
							break;
			default:		node.setStyle("-fx-bar-fill: " + color.toString() + ";");
							break;
		}
			
	}
	
	public static String getRandomColor() {
		return "#" + Integer.toHexString((int)(Math.random()*16777215));
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
