package srtnglgrthms.model;

import java.util.Arrays;
import java.util.Queue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

public abstract class SortingAlgorithm {
	private static int[] numbers;
	protected static ObservableList<Data<String, Number>> data;
	protected static Queue<RecursiveParameter> recursiveCall;
	protected static ObservableList<CounterData> counterData = FXCollections.observableArrayList();
	protected static ObservableList<BenchmarkData> benchmarkData = FXCollections.observableArrayList();
	
	protected static void swap(int firstIndex, int secondIndex) {
		int temp;
		temp = (int) data.get(firstIndex).getYValue();
		data.get(firstIndex).setYValue(data.get(secondIndex).getYValue());
		data.get(secondIndex).setYValue(temp);
	}
	
	public static int[] getNumbers() {
		return numbers;
	}
	
	public static int getMaximum() {
		return Arrays.stream(numbers).max().getAsInt();
	}
	
	public static ObservableList<CounterData> getCounterData() {
		return counterData;
	}
	
	public static ObservableList<BenchmarkData> getBenchmarkData() {
		return benchmarkData;
	}
	
	public static void setNumbers(int[] numbers) {
		SortingAlgorithm.numbers = numbers;
	}
	
	public static ObservableList<Data<String, Number>> getData() {
		return data;
	}
	
	public static void setData(ObservableList<Data<String, Number>> observableList) {
		SortingAlgorithm.data = observableList;
	}
	
	public abstract void step();
	
	public abstract void setDefaults();
}
