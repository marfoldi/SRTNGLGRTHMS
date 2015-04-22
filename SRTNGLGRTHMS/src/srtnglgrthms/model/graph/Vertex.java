package srtnglgrthms.model.graph;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * 
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public class Vertex extends Group {

	private final Circle circle;
	private int number;

	public Vertex(double centerX, double centerY, double radius, String color,
			int number) {
		circle = new Circle(centerX, centerY, radius);
		this.setColor(color);
		this.number = number;
	}

	public Vertex(double centerX, double centerY, double radius, int number) {
		this(centerX, centerY, radius, "default", number);
	}

	public Vertex(double centerX, double centerY, double radius, String color) {
		circle = new Circle(centerX, centerY, radius);
		this.setColor(color);
		this.number = -2;
	}

	public Vertex(double centerX, double centerY, double radius) {
		this(centerX, centerY, radius, "default");
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

	public Label getGraphics() {
		Label label;
		if (this.number != -2) {
			label = new Label(String.valueOf(number), circle);
		} else
			label = new Label("", circle);
		label.setContentDisplay(ContentDisplay.CENTER);
		label.layoutXProperty()
				.bind(Bindings.subtract(circle.centerXProperty(),
						circle.getRadius()));
		label.layoutYProperty()
				.bind(Bindings.subtract(circle.centerYProperty(),
						circle.getRadius()));
		return label;
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

	public void setColor(String color) {
		switch (color) {
		case "default":
			color = "#f3622d";
			break;
		case "swap":
			color = "#4258c9";
			;
			break;
		case "select":
			color = "#57b757";
			break;
		case "done":
			color = "#8C2D46";
			break;
		}
		circle.setFill(Color.valueOf(color));
	}

}
