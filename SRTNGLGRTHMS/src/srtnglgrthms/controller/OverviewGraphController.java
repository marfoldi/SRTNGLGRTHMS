package srtnglgrthms.controller;

import srtnglgrthms.model.graph.Graph;
import srtnglgrthms.model.graph.Vertex;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.layout.AnchorPane;

public class OverviewGraphController {
	@FXML
	AnchorPane graphPane;
	@FXML
	private AnchorPane chartPane;
	@FXML
	private OverviewChartController chartController;
	private BarChart<String, Number> barChart;
	private static Graph graph = new Graph();
	private static Vertex[] vertices;
	private static ObservableList<Data<String, Number>> numberList;
	
	@SuppressWarnings("unchecked")
	@FXML
	private void initialize() {
		reloadGraph();
		graphPane.getChildren().add(graph);
		chartController = new OverviewChartController();
		barChart = (BarChart<String, Number>) chartPane.getChildren().get(0);
		chartController.setBarChart(barChart);
		numberList = barChart.getData().get(0).dataProperty().getValue();
		if(OverviewListController.getSelectedItem().equals("Versenyrendezés")) {
			for(int i=0; i<numberList.size(); ++i) {
				numberList.get(i).setYValue(0);
			}
		}
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
		if(OverviewListController.getSelectedItem().equals("Kupacrendezés") && vertices.length%2==0) {
			graph.bindVertexes(vertices[(vertices.length-1)/2], vertices[vertices.length-1]);
		}
	}
	
	public static ObservableList<Data<String, Number>> getNumberList() {
		return numberList;
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
