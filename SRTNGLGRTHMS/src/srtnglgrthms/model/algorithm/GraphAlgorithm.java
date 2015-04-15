package srtnglgrthms.model.algorithm;

import javafx.scene.layout.GridPane;
import srtnglgrthms.controller.OverviewGraphController;
import srtnglgrthms.model.graph.Graph;
import srtnglgrthms.model.graph.Vertex;

public abstract class GraphAlgorithm extends SortingAlgorithm {
	protected static int[] checkedArray;
	protected static GridPane numbersPane;
	protected static final int vertexSize = 18;
	protected static final int xGap = 200;
	protected static final int yGap = 60;
	
	public static final Graph graph = OverviewGraphController.getGraph();
	protected static Vertex[] vertices = OverviewGraphController.getVertices();
	
	public abstract void setDefaultGraph();
	
	public static int[] checkLength(int[] numbers, String algorithm) {
		switch(algorithm) {
		case "Kupacrendezés": {
			if(numbers.length>31) {
				int[] checkedArray = new int[31];
				System.arraycopy(numbers, 0, checkedArray, 0, 30);
				GraphAlgorithm.checkedArray = checkedArray;
				return checkedArray;
			}
			GraphAlgorithm.checkedArray = numbers;
			return numbers;
		}
		case "Versenyrendezés": {
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
			GraphAlgorithm.checkedArray = checkedArray;
			return checkedArray;
		}
		}
		GraphAlgorithm.checkedArray = numbers;
		return numbers;
	}
	
	protected void swap(int firstIndex, int secondIndex) {
		int temp;
		temp = vertices[firstIndex].getNumber();
		vertices[firstIndex].setNumber(vertices[secondIndex].getNumber());
		vertices[secondIndex].setNumber(temp);
	}
	
	protected void setRestColor(int index) {
		for (int i=0; i<index; ++i) {
			vertices[i].setColor("default");
		}
	}
	
	public static int[] getCheckedArray() {
		return checkedArray;
	}
	public static void setCheckedArray(int[] checkedArray) {
		GraphAlgorithm.checkedArray = checkedArray;
	}
}
