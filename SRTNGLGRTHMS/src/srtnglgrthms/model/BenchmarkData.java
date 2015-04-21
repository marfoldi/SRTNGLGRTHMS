package srtnglgrthms.model;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public class BenchmarkData {
	private StringProperty name;
	private LongProperty compareCounter;
	private LongProperty moveCounter;
	private LongProperty swapCounter;

	public BenchmarkData() {
		this(null, 0, 0, 0);
	}

	public BenchmarkData(String name, long compareCounter, long moveCounter, long swapCounter) {
		this.name = new SimpleStringProperty(name);
		this.compareCounter = new SimpleLongProperty(compareCounter);
		this.moveCounter = new SimpleLongProperty(moveCounter);
		this.swapCounter = new SimpleLongProperty(swapCounter);
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

	public long getCompareCounter() {
		return compareCounter.get();
	}

	public void setCompareCounter(long compareCounter) {
		this.compareCounter.set(compareCounter);
	}

	public LongProperty compareCounterProperty() {
		return compareCounter;
	}

	public long getMoveCounter() {
		return moveCounter.get();
	}

	public void setMoveCounter(long moveCounter) {
		this.moveCounter.set(moveCounter);
	}

	public LongProperty moveCounterProperty() {
		return moveCounter;
	}

	public long getSwapCounter() {
		return swapCounter.get();
	}

	public void setSwapCounter(long swapCounter) {
		this.swapCounter.set(swapCounter);
	}

	public LongProperty swapCounterProperty() {
		return swapCounter;
	}
}
