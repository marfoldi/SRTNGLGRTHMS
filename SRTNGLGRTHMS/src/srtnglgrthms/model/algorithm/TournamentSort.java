package srtnglgrthms.model.algorithm;

import java.util.LinkedList;

import srtnglgrthms.controller.OverviewChartController;
import srtnglgrthms.controller.OverviewGraphController;
import srtnglgrthms.model.RecursiveParameter;
import srtnglgrthms.model.graph.Vertex;

public class TournamentSort extends GraphAlgorithm {
	private static int fillIndex;
	private static int recursiveCounter;
	private static int maxIndex;
	private static int outIndex;
	private static boolean colored;
	private static boolean isFound;
	
	private TournamentSort() {}
	
	private static class SortHolder {
        private static final TournamentSort INSTANCE = new TournamentSort();
    }
	
    public static TournamentSort getInstance() {
        return SortHolder.INSTANCE;
    }
	
	@Override
	public void setDefaults() {
		recursiveCall = new LinkedList<>();
		setDefaultGraph();
		fillIndex = vertices.length/2;
		recursiveCounter = vertices.length/2;
		maxIndex = 0;
		outIndex = 1;
		colored = false;
		isFound = false;
	}
	
	@Override
	public void step() {
		if(fillIndex==0) {
			setRestColor(vertices.length);
			fillIndex--;
		}
		while(fillIndex>=1) {
			setRestColor(vertices.length);
			if(!colored) {
				vertices[2*fillIndex].setColor("swap");
				vertices[2*fillIndex-1].setColor("swap");
				colored = true;
				return;
			}
			vertices[fillIndex-1].setNumber(vertices[2*fillIndex].getNumber()>vertices[2*fillIndex-1].getNumber() ? vertices[2*fillIndex].getNumber():vertices[2*fillIndex-1].getNumber());
			colored = false;
			OverviewGraphController.reloadGraph();
			OverviewGraphController.addVertices();
			if(fillIndex-1==0) {
				vertices[fillIndex-1].setColor("done");
				OverviewGraphController.getNumberList().get(checkedArray.length-outIndex).setYValue(vertices[0].getNumber());
				OverviewChartController.setColor(OverviewGraphController.getNumberList().get(checkedArray.length-outIndex).getNode(), "done");
				outIndex++;
			}
			else vertices[fillIndex-1].setColor("select");
			if(vertices[2*fillIndex].getNumber()>vertices[2*fillIndex-1].getNumber()) vertices[2*fillIndex].setColor("swap");
			else vertices[2*fillIndex-1].setColor("swap");
			fillIndex--;
			return;
		}
		if(recursiveCounter>=1) {
			if(maxIndex<vertices.length/2 && !isFound) {
				setRestColor(vertices.length);
				vertices[maxIndex].setColor("swap");
				maxIndex = vertices[maxIndex].getNumber()==vertices[2*maxIndex+1].getNumber()? 2*maxIndex+1 : 2*maxIndex+2;
				vertices[maxIndex].setColor("swap");
			}
			else if (!isFound) {
				setRestColor(vertices.length);
				vertices[maxIndex].setColor("done");
				vertices[maxIndex].setNumber(-1);
				isFound=true;
			}
			else {
				setRestColor(vertices.length);
				if(!colored) {
					if(maxIndex%2==0) maxIndex = maxIndex/2-1;
					else maxIndex=maxIndex/2;
					vertices[2*maxIndex+2].setColor("swap");
					vertices[2*maxIndex+1].setColor("swap");
					colored = true;
					return;
				}
				if(maxIndex>=0) {
					vertices[maxIndex].setNumber(vertices[2*maxIndex+1].getNumber()>vertices[2*maxIndex+2].getNumber() ? vertices[2*maxIndex+1].getNumber():vertices[2*maxIndex+2].getNumber());
					if(maxIndex==0) {
						OverviewGraphController.getNumberList().get(checkedArray.length-outIndex).setYValue(vertices[0].getNumber());
						OverviewChartController.setColor(OverviewGraphController.getNumberList().get(checkedArray.length-outIndex).getNode(), "done");
						outIndex++;
						vertices[maxIndex].setColor ("done");
					}
					else vertices[maxIndex].setColor ("select");
					if(vertices[2*maxIndex+1].getNumber()>vertices[2*maxIndex+2].getNumber()) vertices[2*maxIndex+1].setColor("swap");
					else vertices[2*maxIndex+2].setColor("swap");
					colored = false;
					OverviewGraphController.reloadGraph();
					OverviewGraphController.addVertices();
				}
			}
			OverviewGraphController.reloadGraph();
			OverviewGraphController.addVertices();
			if(maxIndex==0 && isFound) {
				recursiveCounter--;
				isFound = false;
			}
		}
		if(recursiveCounter==0) {
			for (Vertex vertex : vertices) {
				vertex.setColor("done");
			}
			for(int i=0; i<checkedArray.length; ++i) {
				OverviewChartController.setColor(ChartAlgorithm.getData().get(i).getNode(), "done");
			}
		}
	}
	
	
	public static Runnable sort = () -> {
	};


	@Override
	public void setDefaultGraph() {
		OverviewGraphController.setVertices(new Vertex[checkedArray.length*2-1]);
		vertices = OverviewGraphController.getVertices();
		vertices[0] = new Vertex(400, 20, vertexSize);
		int j = 0;
		recursiveCall.add(new RecursiveParameter(400, 20, 1.0));
		for(int i=1; i<vertices.length-1; i+=2) {
			if(!recursiveCall.isEmpty()) {
				RecursiveParameter nextParameters = recursiveCall.remove();
            	double x = nextParameters.getFirstParameter();
    			double y = nextParameters.getSecondParameter();
    			double delta = nextParameters.getThirdParameter();
				vertices[i] = new Vertex(x-xGap*delta, y+yGap, vertexSize);
				vertices[i+1] = new Vertex(x+xGap*delta, y+yGap, vertexSize);
				recursiveCall.add(new RecursiveParameter(x-xGap*delta, y+yGap, delta*0.5));
				recursiveCall.add(new RecursiveParameter(x+xGap*delta, y+yGap, delta*0.5));
				if(i>=checkedArray.length-1) {
					vertices[i].setNumber(checkedArray[j]);
					vertices[i+1].setNumber(checkedArray[j+1]);
					j+=2;
				}
			}
		}
		OverviewGraphController.addVertices();
	}
}