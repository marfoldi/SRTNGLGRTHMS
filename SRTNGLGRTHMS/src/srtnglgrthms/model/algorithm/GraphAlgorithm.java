package srtnglgrthms.model.algorithm;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import srtnglgrthms.controller.OverviewChartController;
import srtnglgrthms.controller.OverviewGraphController;
import srtnglgrthms.controller.OverviewListController;
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
		case "Kupacrendez�s": {
			if(numbers.length>31) {
				int[] checkedArray = new int[31];
				System.arraycopy(numbers, 0, checkedArray, 0, 30);
				GraphAlgorithm.checkedArray = checkedArray;
				Alert alert = new Alert(AlertType.WARNING);
		        alert.setTitle("Figyelmeztet�s");
		        alert.setHeaderText("Az algoritmusban haszn�lt sz�mok elt�rnek a bemenett�l");
		        alert.setContentText("Mivel legfeljebb 31 elem� gr�fot k�pes a program megjelen�teni,\nez�rt a"
		        		+ "sz�m�t�sban csak az els� 31 elem vesz r�szt!");

		        alert.showAndWait();
				return checkedArray;
			}
			GraphAlgorithm.checkedArray = numbers;
			return numbers;
		}
		case "Versenyrendez�s": {
			int length = 1;
			int power = (int) Math.ceil(Math.log(numbers.length)/Math.log(2));
			for(int i=0; i<power; ++i) length*=2;
			if(length>16) {
				length = 16;
				Alert alert = new Alert(AlertType.WARNING);
		        alert.setTitle("Figyelmeztet�s");
		        alert.setHeaderText("Az algoritmusban haszn�lt sz�mok elt�rnek a bemenett�l");
		        alert.setContentText("Mivel legfeljebb 16 elem� gr�fot k�pes a program megjelen�teni,\nez�rt a"
		        		+ "sz�m�t�sban csak az els� 16 elem vesz r�szt!");

		        alert.showAndWait();
			}
			else if(length!=SortingAlgorithm.getNumbers().length) {
				Alert alert = new Alert(AlertType.WARNING);
		        alert.setTitle("Figyelmeztet�s");
		        alert.setHeaderText("Az algoritmusban haszn�lt sz�mok elt�rnek a bemenett�l");
		        alert.setContentText("Mivel nem n�gyzetsz�m a bemenet hossza,\nez�rt a"
		        		+ "sz�m�t�sban " + length + " sz�m fog r�szvenni!");

		        alert.showAndWait();
			}
			int[] checkedArray = new int[length];
			for(int i=numbers.length; i<length; ++i) {
				checkedArray[i] = -1;
			}
			if(length>numbers.length) System.arraycopy(numbers, 0, checkedArray, 0, length-(length-numbers.length));
			else System.arraycopy(numbers, 0, checkedArray, 0, length);
			GraphAlgorithm.checkedArray = checkedArray;
			if(numbers.length>16) {
				return checkedArray;
			}
			else return numbers;
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
		if(OverviewListController.getSelectedItem().equals("Kupacrendez�s")) {
			OverviewGraphController.getNumberList().get(firstIndex).setYValue(OverviewGraphController.getNumberList().get(secondIndex).getYValue());
			OverviewGraphController.getNumberList().get(secondIndex).setYValue(temp);
		}
	}

	protected void setRestColor(int index) {
		for (int i=0; i<index; ++i) {
			vertices[i].setColor("default");
			if(OverviewListController.getSelectedItem().equals("Kupacrendez�s")) {
				OverviewChartController.setColor(OverviewGraphController.getNumberList().get(i).getNode(), "default");
			}
		}
	}

	public static int[] getCheckedArray() {
		return checkedArray;
	}
	public static void setCheckedArray(int[] checkedArray) {
		GraphAlgorithm.checkedArray = checkedArray;
	}
}
