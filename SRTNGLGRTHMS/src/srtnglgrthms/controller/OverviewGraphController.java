package srtnglgrthms.controller;

import srtnglgrthms.model.graph.Graph;
import srtnglgrthms.model.graph.Vertex;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class OverviewGraphController {
	@FXML
	AnchorPane pane;
	private static Graph graph = new Graph();
	private static Vertex[] vertices;
	
	@FXML
	private void initialize() {
		reloadGraph();
		pane.getChildren().add(graph);
	}
	
	public static void addVertices() {
		for(Vertex vertex : vertices) {
			graph.addVertex(vertex);
		}
		for(int i=0; i<vertices.length-1; ++i) {
			if(2*i+1<vertices.length-1) {
				graph.bindVertexes(vertices[i], vertices[2*i+1]);
				graph.bindVertexes(vertices[i], vertices[2*i+2]);
			}
		}
	}
	
	public static void reloadGraph() {
		graph.getChildren().clear();
	}
	
	public static Graph getGraph() {
		return graph;
	}
	
	public static void setVertices(Vertex[] vertices) {
		OverviewGraphController.vertices = vertices;
	}
	
	public static Vertex[] getVertices() {
		return vertices;
	}
}
