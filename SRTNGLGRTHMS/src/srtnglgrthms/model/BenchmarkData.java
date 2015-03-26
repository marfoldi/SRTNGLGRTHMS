package srtnglgrthms.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BenchmarkData {
	private StringProperty name;
	private IntegerProperty compareCounter;
	private IntegerProperty swapCounter;

	public BenchmarkData() {
		this(null, 0, 0);
	}

	public BenchmarkData(String name, int compareCounter, int swapCounter) {
		this.name = new SimpleStringProperty(name);
		this.compareCounter = new SimpleIntegerProperty(compareCounter);
		this.swapCounter = new SimpleIntegerProperty(swapCounter);
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public StringProperty nameProperty() {
		return name;
	}

	public int getCompareCounter() {
		return compareCounter.get();
	}

	public void setCompareCounter(int compareCounter) {
		this.compareCounter.set(compareCounter);
	}
	
	public void incCompareCounter() {
		this.compareCounter.set(this.compareCounter.get()+1);
	}

	public IntegerProperty compareCounterProperty() {
		return compareCounter;
	}
	
	public int getSwapCounter() {
		return swapCounter.get();
	}

	public void setSwapCounter(int swapCounter) {
		this.swapCounter.set(swapCounter);
	}
	
	public void incSwapCounter() {
		this.swapCounter.set(this.swapCounter.get()+1);
	}

	public IntegerProperty swapCounterProperty() {
		return swapCounter;
	}
}
