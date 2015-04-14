package srtnglgrthms.controller;

import srtnglgrthms.model.algorithm.GraphAlgorithm;
import srtnglgrthms.model.algorithm.SortingAlgorithm;
import srtnglgrthms.model.graph.Graph;
import srtnglgrthms.model.graph.Vertex;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class OverviewGraphController {
	@FXML
	AnchorPane graphPane;
	@FXML
	private GridPane numbersPane;
	@FXML
	private ScrollPane numbersScroll;
	private static Graph graph = new Graph();
	private static Vertex[] vertices;
	private static int vertexSize;
	
	@FXML
	private void initialize() {
		reloadGraph();
		vertexSize = 18;
		graphPane.getChildren().add(graph);
		createFields();
		GraphAlgorithm.setNumbersPane(numbersPane);
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
	
	private void createFields() {
		numbersPane.getChildren().clear();
		DoubleProperty wProperty = new SimpleDoubleProperty();
	    wProperty.bind(numbersPane.widthProperty());
	    wProperty.addListener(new ChangeListener<Object>() {
	        @Override
	        public void changed(ObservableValue<?> ov, Object oldValue, Object newValue) {
	         numbersScroll.setHvalue(numbersScroll.getHmax()); 
	        }
	    }) ;
		for (int i = 0; i < SortingAlgorithm.getNumbers().length; ++i) {
			TextField tf = new TextField();
			tf.setMaxWidth(50.0);
			numbersPane.add(tf, i, 0);
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
	
	public static int getVertexSize() {
		return vertexSize;
	}
}
