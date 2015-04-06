package srtnglgrthms.model.graph;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;


public class Vertex extends Group {
	
	private final Circle circle;
	
	private int number;
	
	public Vertex(double centerX, double centerY, double radius, Paint fill, int number) {
		circle = new Circle(centerX, centerY, radius, fill);
		this.number = number;
	}
	
	public Vertex(double centerX, double centerY, double radius, Paint fill) {
		circle = new Circle(centerX, centerY, radius, fill);
	}
	
	public double getCenterX() {
		return circle.getCenterX();
	}
	
	public void setCenterX(double centerX) {
		circle.setCenterX(centerX);
	}
	
	public double getCenterY() {
		return circle.getCenterY();
	}
	
	public void setCenterY(double centerY) {
		circle.setCenterY(centerY);
	}
	
	public Label graphicRepresentaion() {
		Label label = new Label(String.valueOf(number), circle);

		label.setContentDisplay(ContentDisplay.CENTER);
		label.layoutXProperty().bind(Bindings.subtract(circle.centerXProperty(), circle.getRadius()));
		label.layoutYProperty().bind(Bindings.subtract(circle.centerYProperty(), circle.getRadius()));
		
		return label ;
	}

	public DoubleProperty centerXProperty() {
		return circle.centerXProperty();
	}
	
	public DoubleProperty centerYProperty() {
		return circle.centerYProperty();
	}

	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
}
