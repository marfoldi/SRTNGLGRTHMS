package srtnglgrthms.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CounterData {
	private StringProperty name;
	private StringProperty value;

	public CounterData() {
		this(null, null);
	}

	public CounterData(String name, String value) {
		this.name = new SimpleStringProperty(name);
		this.value = new SimpleStringProperty(value);
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

	public String getValue() {
		return value.get();
	}

	public void setValue(String value) {
		this.value.set(value);
	}
	
	public void incValue() {
		this.value.set(Integer.toString(Integer.parseInt(this.value.get())+1));
	}

	public StringProperty valueProperty() {
		return value;
	}
	
	public void decValue() {
		this.value.set(Integer.toString(Integer.parseInt(this.value.get())-1));
	}

}
