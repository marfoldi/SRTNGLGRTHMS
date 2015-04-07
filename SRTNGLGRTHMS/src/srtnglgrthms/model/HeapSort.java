package srtnglgrthms.model;

import java.util.LinkedList;

import javafx.scene.paint.Color;
import srtnglgrthms.controller.OverviewGraphController;
import srtnglgrthms.model.graph.Vertex;

public class HeapSort extends SortingAlgorithm implements GraphAlgorithm {
	
	private HeapSort() {}
	
	private static class SortHolder {
        private static final HeapSort INSTANCE = new HeapSort();
    }
	
    public static HeapSort getInstance() {
        return SortHolder.INSTANCE;
    }
	
	@Override
	public void setDefaults() {
		recursiveCall = new LinkedList<>();
		setDefaultGraph();
	}
	
	@Override
	public void step() {
			System.out.println("HI");
	}

	@Override
	public void setDefaultGraph() {
		int[] numbers = SortingAlgorithm.getNumbers();
		OverviewGraphController.setVertices(new Vertex[numbers.length]);
		Vertex[] vertices = OverviewGraphController.getVertices();
		vertices[0] = new Vertex(400, 20, 15, Color.valueOf("#f3622d"), numbers[0]);
		recursiveCall.add(new RecursiveParameter(400, 20, 1.0));
		for(int i=1; i<vertices.length-1; i+=2) {
			if(!recursiveCall.isEmpty()) {
				RecursiveParameter nextParameters = recursiveCall.remove();
            	double x = nextParameters.getFirstParameter();
    			double y = nextParameters.getSecondParameter();
    			double delta = nextParameters.getThirdParameter();
				vertices[i] = new Vertex(x-200*delta, y+100, 15, Color.valueOf("#f3622d"), numbers[i]);
				vertices[i+1] = new Vertex(x+200*delta, y+100, 15, Color.valueOf("#f3622d"), numbers[i+1]);
				recursiveCall.add(new RecursiveParameter(x-200*delta, y+100, delta*0.5));
				recursiveCall.add(new RecursiveParameter(x+200*delta, y+100, delta*0.5));
			}
		}
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
}