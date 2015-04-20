package srtnglgrthms.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BenchmarkData {
	private StringProperty name;
	private IntegerProperty compareCounter;
	private IntegerProperty moveCounter;
	private IntegerProperty swapCounter;

	public BenchmarkData() {
		this(null, 0, 0, 0);
	}

	public BenchmarkData(String name, int compareCounter, int moveCounter, int swapCounter) {
		this.name = new SimpleStringProperty(name);
		this.compareCounter = new SimpleIntegerProperty(compareCounter);
		this.moveCounter = new SimpleIntegerProperty(moveCounter);
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

	public IntegerProperty compareCounterProperty() {
		return compareCounter;
	}

	public int getMoveCounter() {
		return moveCounter.get();
	}

	public void setMoveCounter(int moveCounter) {
		this.moveCounter.set(moveCounter);
	}

	public IntegerProperty moveCounterProperty() {
		return moveCounter;
	}

	public int getSwapCounter() {
		return swapCounter.get();
	}

	public void setSwapCounter(int swapCounter) {
		this.swapCounter.set(swapCounter);
	}

	public IntegerProperty swapCounterProperty() {
		return swapCounter;
	}
}
