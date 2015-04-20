package srtnglgrthms.model.algorithm;

import java.util.LinkedList;

//MOZGATÁS MINDIG N, ÖSSZEHASONLÍTÁS NEM KELL, HA -1;




import srtnglgrthms.controller.OverviewChartController;
import srtnglgrthms.controller.OverviewGraphController;
import srtnglgrthms.model.BenchmarkData;
import srtnglgrthms.model.CounterData;
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
		recursiveCounter = OverviewGraphController.getNumberList().size()-1;
		maxIndex = 0;
		outIndex = 1;
		colored = false;
		isFound = false;
		counterData.clear();
		counterData.add(new CounterData("Összehasonlítások", "0"));
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
			if(vertices[2*fillIndex].getNumber()!=-1 && vertices[2*fillIndex-1].getNumber()!=-1) counterData.get(0).incValue();
			vertices[fillIndex-1].setNumber(vertices[2*fillIndex].getNumber()>vertices[2*fillIndex-1].getNumber() ? vertices[2*fillIndex].getNumber():vertices[2*fillIndex-1].getNumber());
			colored = false;
			OverviewGraphController.reloadGraph();
			OverviewGraphController.addVertices();
			if(fillIndex-1==0) {
				vertices[fillIndex-1].setColor("done");
				OverviewGraphController.getNumberList().get(OverviewGraphController.getNumberList().size()-outIndex).setYValue(vertices[0].getNumber());
				OverviewChartController.setColor(OverviewGraphController.getNumberList().get(OverviewGraphController.getNumberList().size()-outIndex).getNode(), "done");
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
					if(vertices[2*maxIndex+1].getNumber()!=-1 && vertices[2*maxIndex+2].getNumber()!=-1) counterData.get(0).incValue();
					vertices[maxIndex].setNumber(vertices[2*maxIndex+1].getNumber()>vertices[2*maxIndex+2].getNumber() ? vertices[2*maxIndex+1].getNumber():vertices[2*maxIndex+2].getNumber());
					if(maxIndex==0) {
						OverviewGraphController.getNumberList().get(OverviewGraphController.getNumberList().size()-outIndex).setYValue(vertices[0].getNumber());
						OverviewChartController.setColor(OverviewGraphController.getNumberList().get(OverviewGraphController.getNumberList().size()-outIndex).getNode(), "done");
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
		}
	}


	public static Runnable sort = () -> {
		int length = 1;
		int power = (int) Math.ceil(Math.log(numbers.length)/Math.log(2));
		for(int i=0; i<power; ++i) length*=2;
		int[] numbers = new int[length];
		for(int i=SortingAlgorithm.getNumbers().length; i<length; ++i) {
			numbers[i] = -1;
		}
		if(length>SortingAlgorithm.getNumbers().length) System.arraycopy(SortingAlgorithm.getNumbers(), 0, numbers, 0, length-(length-SortingAlgorithm.getNumbers().length));
		else System.arraycopy(SortingAlgorithm.getNumbers(), 0, numbers, 0, length);
		int[] finalNumbers = new int[length*2-1];
		for(int i=0; i<length; ++i) {
			finalNumbers[i] = -2;
		}
		int j=0;
		for(int i=length; i<finalNumbers.length; ++i) {
			finalNumbers[i] = numbers[j];
			j++;
		}
	    int comparisonCounter=0; //Increment this counter whenever a comparison takes place
		for(int i=finalNumbers.length/2; i>=1; --i) {
			if(finalNumbers[2*i]!=-1 && finalNumbers[2*i-1]!=-1) comparisonCounter++;
			finalNumbers[i-1] = finalNumbers[2*i]>finalNumbers[2*i-1] ? finalNumbers[2*i] : finalNumbers[2*i-1];
		}
		int recursiveCounter = SortingAlgorithm.getNumbers().length-1;
		while (recursiveCounter>=1) {
			maxIndex = 0;
			while(maxIndex<SortingAlgorithm.getNumbers().length/2) {
				maxIndex = finalNumbers[maxIndex]==finalNumbers[2*maxIndex+1] ? 2*maxIndex+1 : 2*maxIndex+2;
				finalNumbers[maxIndex] = -1;
			}
			if(maxIndex%2==0) maxIndex = maxIndex/2-1;
			else maxIndex=maxIndex/2;
			while(maxIndex>=0) {
				if(finalNumbers[2*maxIndex+1]!=-1 && finalNumbers[2*maxIndex+2]!=-1) comparisonCounter++;
				finalNumbers[maxIndex] = finalNumbers[2*maxIndex+1]>finalNumbers[2*maxIndex+2] ? finalNumbers[2*maxIndex+1] : finalNumbers[2*maxIndex+2];
				if(maxIndex%2==0) maxIndex = maxIndex/2-1;
				else maxIndex=maxIndex/2;
			}
			recursiveCounter--;
		}
		benchmarkData.add(new BenchmarkData("Versenyrendezés", comparisonCounter, 0, 0));
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