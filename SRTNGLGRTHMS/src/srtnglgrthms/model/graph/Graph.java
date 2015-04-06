package srtnglgrthms.model.graph;

import javafx.scene.Group;

public class Graph extends Group {
	public void addVertex(Vertex vertex) {
		getChildren().add(new Group(vertex.graphicRepresentaion()));
	}
	
	public void bindVertexes(Vertex vertexA, Vertex vertexB)  {
		Edge edge = createEdge(vertexA, vertexB);	
		edge.startXProperty().bind(vertexA.centerXProperty()); 
		edge.startYProperty().bind(vertexA.centerYProperty());
		edge.endXProperty().bind(vertexB.centerXProperty());
		edge.endYProperty().bind(vertexB.centerYProperty());
		edge.toBack();
	}

	public Edge createEdge(Vertex vertexA, Vertex vertexB) {
		Edge edge = new Edge(vertexA.getCenterX(), vertexA.getCenterY(), vertexB.getCenterX(), vertexB.getCenterY());
		getChildren().add(edge);
		return edge;
	}	
}
