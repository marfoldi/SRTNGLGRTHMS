package srtnglgrthms.controller;


import srtnglgrthms.model.graph.Graph;
import srtnglgrthms.model.graph.Vertex;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class OverviewGraphController {
	@FXML
	AnchorPane pane;
	private Graph graph = new Graph();
	
	@FXML
	private void initialize() {
		final Vertex vertex1 = new Vertex(50, 50, 20, Color.rgb(156,216,255), 50);
		final Vertex vertex2 = new Vertex(100, 100, 20, Color.rgb(156,216,255), 0);
		final Vertex vertex3 = new Vertex(150, 150, 20, Color.rgb(156,216,255), 20);
		final Vertex vertex4 = new Vertex(0, 100, 20, Color.rgb(156,216,255), 20);
	
		graph.addVertex(vertex1);
		graph.addVertex(vertex2);
		graph.addVertex(vertex3);
		graph.addVertex(vertex4);
	
		graph.bindVertexes(vertex1, vertex2);
		graph.bindVertexes(vertex2, vertex3);
		graph.bindVertexes(vertex1, vertex4);
		pane.getChildren().add(graph);
	}
}
