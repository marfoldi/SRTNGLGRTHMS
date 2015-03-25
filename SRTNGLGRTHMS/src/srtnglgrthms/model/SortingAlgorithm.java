package srtnglgrthms.model;

import java.util.Arrays;
import java.util.Queue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

public abstract class SortingAlgorithm {
	private static int[] numbers;
	protected static ObservableList<XYChart.Data<String,Integer>> data;
	protected static Queue<RecursiveParameter> recursiveCall;
	protected static ObservableList<Counter> counterData = FXCollections.observableArrayList();
	
	protected static void swap(int firstIndex, int secondIndex) {
		int temp;
		temp = data.get(firstIndex).getYValue();
		data.get(firstIndex).setYValue(data.get(secondIndex).getYValue());
		data.get(secondIndex).setYValue(temp);
	}
	
	public static int[] getNumbers() {
		return numbers;
	}
	
	public static int getMaximum() {
		return Arrays.stream(numbers).max().getAsInt();
	}
	
	public static ObservableList<Counter> getCounterData() {
		return counterData;
	}
	
	public static void setNumbers(int[] numbers) {
		SortingAlgorithm.numbers = numbers;
	}
	
	public static void setData(ObservableList<XYChart.Data<String,Integer>> data) {
		SortingAlgorithm.data = data;
	}
	
	public abstract void step();
	
	public abstract void setDefaults();
}
