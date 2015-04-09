package srtnglgrthms.model.algorithm;

import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart.Data;

public abstract class ChartAlgorithm extends SortingAlgorithm {
	protected static ObservableList<Data<String, Number>> data;
	
	protected void swap(int firstIndex, int secondIndex) {
		int temp;
		temp = (int) data.get(firstIndex).getYValue();
		data.get(firstIndex).setYValue(data.get(secondIndex).getYValue());
		data.get(secondIndex).setYValue(temp);
	}
	
	public static ObservableList<Data<String, Number>> getData() {
		return data;
	}
	
	public static void setData(ObservableList<Data<String, Number>> observableList) {
		ChartAlgorithm.data = observableList;
	}
}
