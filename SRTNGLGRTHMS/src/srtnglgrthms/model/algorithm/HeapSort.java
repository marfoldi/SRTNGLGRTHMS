package srtnglgrthms.model.algorithm;

import java.util.LinkedList;

import srtnglgrthms.controller.OverviewGraphController;
import srtnglgrthms.model.RecursiveParameter;
import srtnglgrthms.model.graph.Vertex;

public class HeapSort extends GraphAlgorithm {
	
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
	}

	@Override
	public void setDefaultGraph() {
		checkedArray = checkLength(numbers);
		OverviewGraphController.setVertices(new Vertex[checkedArray.length]);
		Vertex[] vertices = OverviewGraphController.getVertices();
		vertices[0] = new Vertex(400, 20, 15, checkedArray[0]);
		recursiveCall.add(new RecursiveParameter(400, 20, 1.0));
		for(int i=1; i<vertices.length-1; i+=2) {
			if(!recursiveCall.isEmpty()) {
				RecursiveParameter nextParameters = recursiveCall.remove();
            	double x = nextParameters.getFirstParameter();
    			double y = nextParameters.getSecondParameter();
    			double delta = nextParameters.getThirdParameter();
				vertices[i] = new Vertex(x-200*delta, y+100, 15, checkedArray[i]);
				vertices[i+1] = new Vertex(x+200*delta, y+100, 15, checkedArray[i+1]);
				recursiveCall.add(new RecursiveParameter(x-200*delta, y+100, delta*0.5));
				recursiveCall.add(new RecursiveParameter(x+200*delta, y+100, delta*0.5));
			}
		}
		if(vertices.length%2==0) {
			RecursiveParameter nextParameters = recursiveCall.remove();
        	double x = nextParameters.getFirstParameter();
			double y = nextParameters.getSecondParameter();
			double delta = nextParameters.getThirdParameter();
			vertices[vertices.length-1] = new Vertex(x-200*delta, y+100, 15, checkedArray[vertices.length-1]);
			graph.bindVertexes(vertices[(vertices.length-1)/2], vertices[vertices.length-1]);
		}
		OverviewGraphController.addVertices();
	}

	@Override
	public int[] checkLength(int[] numbers) {
		if(numbers.length>31) {
			int[] checkedArray = new int[31];
			System.arraycopy(numbers, 0, checkedArray, 0, 30);
			return checkedArray;
		}
		else return numbers;
	}
}