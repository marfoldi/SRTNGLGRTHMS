package srtnglgrthms.model.algorithm;

import java.util.LinkedList;

import srtnglgrthms.controller.OverviewGraphController;
import srtnglgrthms.model.RecursiveParameter;
import srtnglgrthms.model.graph.Vertex;

public class HeapSort extends GraphAlgorithm {
	private static int starterIndex;
	private static int downIndex;
	private static int recursiveCounter;
	private static boolean colored;
	private static boolean canSwap;
	
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
		starterIndex = vertices.length/2-1;
		downIndex = starterIndex;
		recursiveCounter = vertices.length-1;
		colored = false;
		canSwap = true;
	}
	
	@Override
	public void step() {
		if(starterIndex>=0) {
			downIndex = buildHeap(downIndex, vertices.length-1);
			if(downIndex != -1) {
				return;
			}
			else {
				starterIndex--;
				downIndex = starterIndex;
			}
		}
		else if(recursiveCounter>=0) {
			if(canSwap) swap(0, recursiveCounter);
			OverviewGraphController.reloadGraph();
			OverviewGraphController.addVertices();
			downIndex = buildHeap(0, recursiveCounter-1);
			OverviewGraphController.reloadGraph();
			OverviewGraphController.addVertices();
			if(downIndex != -1) {
				canSwap = false;
				return;
			}
			else {
				canSwap = true;
				recursiveCounter--;
			}
		}
	}
	
	private int buildHeap(int starterIndex, int endIndex) {
		setRestColor();
		if(2*starterIndex<=endIndex) {
			if(!colored) {
				try {
					vertices[starterIndex].setColor ("swap");
					vertices[2*starterIndex+2].setColor("swap");
					vertices[2*starterIndex+1].setColor("swap");
					colored = true;
					return starterIndex;
				} catch (ArrayIndexOutOfBoundsException aioobe) {
					setRestColor();
					OverviewGraphController.reloadGraph();
					OverviewGraphController.addVertices();
					colored = false;
					return -1;
				}
			}
			int ir;
			if(2*starterIndex+2>endIndex || vertices[2*starterIndex+1].getNumber()>vertices[2*starterIndex+2].getNumber()) {
				ir = 2*starterIndex+1;
			}
			else ir = 2*starterIndex+2;
			if(vertices[starterIndex].getNumber()>=vertices[ir].getNumber()) {
				colored = false;
				return -1;
			}
			else {
				if(starterIndex == 0) vertices[starterIndex].setColor ("done");
				else vertices[starterIndex].setColor ("select");
				vertices[ir].setColor("swap");
				swap(starterIndex, ir);
				starterIndex = ir;
				OverviewGraphController.reloadGraph();
				OverviewGraphController.addVertices();
			}
		}
		else {
			colored = false;
			return -1;
		}
		colored = false;
		return starterIndex;
	}

	@Override
	public void setDefaultGraph() {
		checkedArray = checkLength(numbers);
		OverviewGraphController.setVertices(new Vertex[checkedArray.length]);
		vertices = OverviewGraphController.getVertices();
		int vertexSize = OverviewGraphController.getVertexSize();
		vertices[0] = new Vertex(400, 20, vertexSize, checkedArray[0]);
		recursiveCall.add(new RecursiveParameter(400, 20, 1.0));
		for(int i=1; i<vertices.length-1; i+=2) {
			if(!recursiveCall.isEmpty()) {
				RecursiveParameter nextParameters = recursiveCall.remove();
            	double x = nextParameters.getFirstParameter();
    			double y = nextParameters.getSecondParameter();
    			double delta = nextParameters.getThirdParameter();
				vertices[i] = new Vertex(x-200*delta, y+90, vertexSize, checkedArray[i]);
				vertices[i+1] = new Vertex(x+200*delta, y+90, vertexSize, checkedArray[i+1]);
				recursiveCall.add(new RecursiveParameter(x-200*delta, y+90, delta*0.5));
				recursiveCall.add(new RecursiveParameter(x+200*delta, y+90, delta*0.5));
			}
		}
		if(vertices.length%2==0) {
			RecursiveParameter nextParameters = recursiveCall.remove();
        	double x = nextParameters.getFirstParameter();
			double y = nextParameters.getSecondParameter();
			double delta = nextParameters.getThirdParameter();
			vertices[vertices.length-1] = new Vertex(x-200*delta, y+90, vertexSize, checkedArray[vertices.length-1]);
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