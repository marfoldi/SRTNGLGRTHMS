package srtnglgrthms.model.algorithm;

import java.util.Arrays;
import java.util.Queue;

import srtnglgrthms.model.CounterData;
import srtnglgrthms.model.RecursiveParameter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public abstract class SortingAlgorithm {
	protected static int[] numbers;
	protected static Queue<RecursiveParameter> recursiveCall;
	protected static ObservableList<CounterData> counterData = FXCollections
			.observableArrayList();

	protected abstract void swap(int firstIndex, int secondIndex);

	public abstract void step();

	public abstract void setDefaults();

	public static int getMaximum() {
		return Arrays.stream(numbers).max().getAsInt();
	}

	public static ObservableList<CounterData> getCounterData() {
		return counterData;
	}

	public static int[] getNumbers() {
		return numbers;
	}

	public static void setNumbers(int[] numbers) {
		ChartAlgorithm.numbers = numbers;
	}
}
