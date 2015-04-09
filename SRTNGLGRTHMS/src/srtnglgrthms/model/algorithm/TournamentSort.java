package srtnglgrthms.model.algorithm;

import java.util.LinkedList;

import srtnglgrthms.controller.OverviewGraphController;
import srtnglgrthms.model.RecursiveParameter;
import srtnglgrthms.model.graph.Vertex;

public class TournamentSort extends GraphAlgorithm {
	private static Vertex[] vertices;
	private static int j;
	//private static int r;
	private static boolean colored;
	
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
		//r = (vertices.length)/2;
		colored = false;
	}
	
	@Override
	public void step() {
		if(j==0) {
			System.out.println(vertices[0].getNumber());
			setRestColor();
			j--;
		}
		while(j>=1) {
			setRestColor();
			if(!colored) {
				vertices[2*j].setColor("swap");
				vertices[2*j-1].setColor("swap");
				colored = true;
				break;
			}
			vertices[j-1].setNumber(vertices[2*j].getNumber()>vertices[2*j-1].getNumber() ? vertices[2*j].getNumber():vertices[2*j-1].getNumber());
			colored = false;
			OverviewGraphController.reloadGraph();
			OverviewGraphController.addVertices();
			vertices[2*j].setColor("done");
			vertices[2*j-1].setColor("done");
			vertices[j-1].setColor("done");
			j--;
			break;
		}
		/*while(r>=1) {
			int i = 0;
			while(i<(vertices.length)/2) {
				i = vertices[2*i]==vertices[i]? 2*i : 2*i+1;
			}
			vertices[i].setNumber(-1);
			i=i/2-1;
			while(i>=1) {
				vertices[i].setNumber(vertices[2*i].getNumber()>vertices[2*i+1].getNumber() ? vertices[2*i].getNumber():vertices[2*i+1].getNumber());
			}
		}*/
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