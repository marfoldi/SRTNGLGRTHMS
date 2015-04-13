package srtnglgrthms.model.algorithm;

import srtnglgrthms.controller.OverviewGraphController;
import srtnglgrthms.model.graph.Graph;
import srtnglgrthms.model.graph.Vertex;

public abstract class GraphAlgorithm extends SortingAlgorithm {
	protected static int[] checkedArray;
	
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
}
