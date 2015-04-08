package srtnglgrthms.model;

import java.util.LinkedList;

import javafx.scene.paint.Color;
import srtnglgrthms.controller.OverviewGraphController;
import srtnglgrthms.model.graph.Vertex;

public class TournamentSort extends SortingAlgorithm implements GraphAlgorithm {
	
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
	}
	
	@Override
	public void step() {
		System.out.println("HI");
	}
	
	
	public static Runnable sort = () -> {
	};


	@Override
	public void setDefaultGraph() {
		int[] numbers = checkLength(SortingAlgorithm.getNumbers());
		OverviewGraphController.setVertices(new Vertex[numbers.length*2-1]);
		Vertex[] vertices = OverviewGraphController.getVertices();
		vertices[0] = new Vertex(400, 20, 15, Color.valueOf("#f3622d"));
		int j = 0;
		recursiveCall.add(new RecursiveParameter(400, 20, 1.0));
		for(int i=1; i<vertices.length-1; i+=2) {
			if(!recursiveCall.isEmpty()) {
				RecursiveParameter nextParameters = recursiveCall.remove();
            	double x = nextParameters.getFirstParameter();
    			double y = nextParameters.getSecondParameter();
    			double delta = nextParameters.getThirdParameter();
				vertices[i] = new Vertex(x-200*delta, y+100, 15, Color.valueOf("#f3622d"));
				vertices[i+1] = new Vertex(x+200*delta, y+100, 15, Color.valueOf("#f3622d"));
				recursiveCall.add(new RecursiveParameter(x-200*delta, y+100, delta*0.5));
				recursiveCall.add(new RecursiveParameter(x+200*delta, y+100, delta*0.5));
				if(i>=numbers.length-1) {
					vertices[i].setNumber(numbers[j]);
					vertices[i+1].setNumber(numbers[j+1]);
					j+=2;
				}
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
}