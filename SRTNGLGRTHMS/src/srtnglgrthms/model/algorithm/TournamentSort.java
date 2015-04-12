package srtnglgrthms.model.algorithm;

import java.util.LinkedList;

import srtnglgrthms.controller.OverviewGraphController;
import srtnglgrthms.model.RecursiveParameter;
import srtnglgrthms.model.graph.Vertex;

public class TournamentSort extends GraphAlgorithm {
	private static Vertex[] vertices;
	private static int j;
	private static int r;
	private static int i;
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
		j = (vertices.length)/2;
		r = (vertices.length)/2;
		i = 0;
		colored = false;
		isFound = false;
	}
	
	@Override
	public void step() {
		if(j==0) {
			setRestColor();
			j--;
		}
		while(j>=1) {
			setRestColor();
			if(!colored) {
				vertices[2*j].setColor("swap");
				vertices[2*j-1].setColor("swap");
				colored = true;
				return;
			}
			vertices[j-1].setNumber(vertices[2*j].getNumber()>vertices[2*j-1].getNumber() ? vertices[2*j].getNumber():vertices[2*j-1].getNumber());
			colored = false;
			OverviewGraphController.reloadGraph();
			OverviewGraphController.addVertices();
			if(j-1==0) vertices[j-1].setColor("select");
			else vertices[j-1].setColor("done");
			vertices[2*j].setColor("done");
			vertices[2*j-1].setColor("done");
			j--;
			return;
		}
		if(r>=0) {
			if(i<vertices.length/2 && !isFound) {
				setRestColor();
				vertices[i].setColor("swap");
				i = vertices[i].getNumber()==vertices[2*i+1].getNumber()? 2*i+1 : 2*i+2;
				vertices[i].setColor("swap");
			}
			else if (!isFound) {
				setRestColor();
				vertices[i].setColor("done");
				vertices[i].setNumber(-1);
				isFound=true;
			}
			else {
				setRestColor();
				if(!colored) {
					if(i%2==0) i = i/2-1;
					else i=i/2;
					vertices[i].setColor ("done");
					vertices[2*i+2].setColor("swap");
					vertices[2*i+1].setColor("swap");
					colored = true;
					return;
				}
				if(i>=0) {
					vertices[i].setNumber(vertices[2*i+1].getNumber()>vertices[2*i+2].getNumber() ? vertices[2*i+1].getNumber():vertices[2*i+2].getNumber());
					if(i==0) vertices[i].setColor ("select");
					else vertices[i].setColor ("done");
					vertices[2*i+2].setColor("done");
					vertices[2*i+1].setColor("done");
					colored = false;
					OverviewGraphController.reloadGraph();
					OverviewGraphController.addVertices();
				}
			}
			OverviewGraphController.reloadGraph();
			OverviewGraphController.addVertices();
			if(i==0 && isFound) {
				r--;
				isFound = false;
			}
		}
		if(r==-1) {
			for (Vertex vertex : vertices) {
				vertex.setColor("done");
			}
		}
	}
	
	
	public static Runnable sort = () -> {
	};


	@Override
	public void setDefaultGraph() {
		checkedArray = checkLength(numbers);
		OverviewGraphController.setVertices(new Vertex[checkedArray.length*2-1]);
		vertices = OverviewGraphController.getVertices();
		vertices[0] = new Vertex(400, 20, 15);
		int j = 0;
		recursiveCall.add(new RecursiveParameter(400, 20, 1.0));
		for(int i=1; i<vertices.length-1; i+=2) {
			if(!recursiveCall.isEmpty()) {
				RecursiveParameter nextParameters = recursiveCall.remove();
            	double x = nextParameters.getFirstParameter();
    			double y = nextParameters.getSecondParameter();
    			double delta = nextParameters.getThirdParameter();
				vertices[i] = new Vertex(x-200*delta, y+100, 15);
				vertices[i+1] = new Vertex(x+200*delta, y+100, 15);
				recursiveCall.add(new RecursiveParameter(x-200*delta, y+100, delta*0.5));
				recursiveCall.add(new RecursiveParameter(x+200*delta, y+100, delta*0.5));
				if(i>=checkedArray.length-1) {
					vertices[i].setNumber(checkedArray[j]);
					vertices[i+1].setNumber(checkedArray[j+1]);
					j+=2;
				}
			}
		}
		OverviewGraphController.addVertices();
	}
	
	@Override
	public int[] checkLength(int[] numbers) {
		int length = 1;
		int power = (int) Math.ceil(Math.log(numbers.length)/Math.log(2));
		for(int i=0; i<power; ++i) length*=2;
		if(length>16) length = 16;
		int[] checkedArray = new int[length];
		for(int i=numbers.length; i<length; ++i) {
			checkedArray[i] = -1;
		}
		if(length>numbers.length) System.arraycopy(numbers, 0, checkedArray, 0, length-(length-numbers.length));
		else System.arraycopy(numbers, 0, checkedArray, 0, length);
		return checkedArray;
	}
	
	private void setRestColor() {
		for (Vertex vertex : vertices) {
			vertex.setColor("default");
		}
	}
}