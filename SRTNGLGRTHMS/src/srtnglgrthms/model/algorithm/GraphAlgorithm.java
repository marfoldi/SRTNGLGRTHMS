package srtnglgrthms.model.algorithm;

import srtnglgrthms.controller.OverviewGraphController;
import srtnglgrthms.model.graph.Graph;

public abstract class GraphAlgorithm extends SortingAlgorithm {
	protected static int[] checkedArray;
	
	public static final Graph graph = OverviewGraphController.getGraph();
	public abstract void setDefaultGraph();
	public abstract int[] checkLength(int[] numbers);
	
	protected void swap(int firstIndex, int secondIndex) {
		// TODO Auto-generated method stub	
	}
}
