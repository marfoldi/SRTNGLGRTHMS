package srtnglgrthms.model.algorithm;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import srtnglgrthms.controller.OverviewGraphController;
import srtnglgrthms.model.graph.Graph;
import srtnglgrthms.model.graph.Vertex;

public abstract class GraphAlgorithm extends SortingAlgorithm {
	protected static int[] checkedArray;
	protected static GridPane numbersPane;
	
	public static final Graph graph = OverviewGraphController.getGraph();
	protected static Vertex[] vertices = OverviewGraphController.getVertices();
	
	public abstract void setDefaultGraph();
	public abstract int[] checkLength(int[] numbers);
	
	protected void swap(int firstIndex, int secondIndex) {
		int temp;
		temp = vertices[firstIndex].getNumber();
		vertices[firstIndex].setNumber(vertices[secondIndex].getNumber());
		vertices[secondIndex].setNumber(temp);
	}
	
	protected void setRestColor() {
		for (Vertex vertex : vertices) {
			vertex.setColor("default");
		}
	}
	
	public static void setNumbersPane(GridPane numbersPane) {
		GraphAlgorithm.numbersPane = numbersPane;
	}
	
	protected static void setNumberAtIndex(int i, int number) {
		ObservableList<Node> nodes = numbersPane.getChildren();
		((TextField)nodes.get(i)).setText(Integer.toString(number));
		//((TextField)nodes.get(i)).setStyle("-fx-background-color: #8C2D46;");
	}
}
