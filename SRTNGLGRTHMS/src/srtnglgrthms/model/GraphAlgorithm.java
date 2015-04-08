package srtnglgrthms.model;

import srtnglgrthms.controller.OverviewGraphController;
import srtnglgrthms.model.graph.Graph;

public interface GraphAlgorithm {
	public static final Graph graph = OverviewGraphController.getGraph();
	public void setDefaultGraph();
	public int[] checkLength(int[] numbers);
}
