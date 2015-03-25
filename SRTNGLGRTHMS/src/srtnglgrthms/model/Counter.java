package srtnglgrthms.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Counter {
	private StringProperty name;
	private IntegerProperty value;

	public Counter() {
		this(null, 0);
	}

	public Counter(String name, int value) {
		this.name = new SimpleStringProperty(name);
		this.value = new SimpleIntegerProperty(value);
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

	public int getValue() {
		return value.get();
	}

	public void setValue(int value) {
		this.value.set(value);
	}
	
	public void incValue() {
		this.value.set(this.value.get()+1);
	}

	public IntegerProperty valueProperty() {
		return value;
	}

}
