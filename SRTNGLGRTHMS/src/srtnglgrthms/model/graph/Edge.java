package srtnglgrthms.model.graph;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Edge extends Line {
	public Edge(double startX, double startY, double endX, double endY) {
		super(startX, startY, endX, endY);
		setStroke(Color.BLACK);
        setStrokeWidth(2);
	}
}
