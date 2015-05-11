package srtnglgrthms.model.algorithm;

import srtnglgrthms.controller.OverviewChartController;
import srtnglgrthms.controller.OverviewGraphController;
import srtnglgrthms.controller.OverviewListController;
import srtnglgrthms.view.graph.Graph;
import srtnglgrthms.view.graph.Vertex;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;

/**
 *
 * @author <a href="mailto:marfoldi@caesar.elte.hu">M�rf�ldi P�ter Bence</a>
 */
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
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Figyelmeztet�s");
		alert.setHeaderText("Az algoritmus megfigegyel�sekor a sz�mok elt�rnek az eredeti bemenett�l!");
		switch (algorithm) {
		case "Kupacrendez�s": {
			if (numbers.length > 31) {
				int[] checkedArray = new int[31];
				System.arraycopy(numbers, 0, checkedArray, 0, 30);
				GraphAlgorithm.checkedArray = checkedArray;
				alert.setContentText("Mivel legfeljebb 31 cs�cssz�m� gr�fot k�pes a program megjelen�teni, ez�rt a "
						+ "sz�m�t�sban csak az els� 31 elem vesz r�szt!");

				alert.showAndWait();
				return checkedArray;
			}
			GraphAlgorithm.checkedArray = numbers;
			return numbers;
		}
		case "Versenyrendez�s": {
			int length = 1;
			int power = (int) Math.ceil(Math.log(numbers.length) / Math.log(2));
			for (int i = 0; i < power; ++i)
				length *= 2;
			if (length > 16) {
				length = 16;
				alert.setContentText("Mivel legfeljebb 31 cs�cssz�m� gr�fot k�pes a program megjelen�teni, ez�rt a "
						+ "sz�m�t�sban csak az els� 16 elem vesz r�szt!");

				alert.showAndWait();
			} else if (length != SortingAlgorithm.getNumbers().length) {
				alert.setContentText("Mivel nem kett� hatv�ny a bemenet hossza, ez�rt a "
						+ "sz�m�t�sban " + length + " elem fog r�sztvenni!");

				alert.showAndWait();
			}
			int[] checkedArray = new int[length];
			for (int i = numbers.length; i < length; ++i) {
				checkedArray[i] = -1;
			}
			if (length > numbers.length)
				System.arraycopy(numbers, 0, checkedArray, 0, length
						- (length - numbers.length));
			else
				System.arraycopy(numbers, 0, checkedArray, 0, length);
			GraphAlgorithm.checkedArray = checkedArray;
			if (numbers.length > 16) {
				return checkedArray;
			} else
				return numbers;
		}
		}
		GraphAlgorithm.checkedArray = numbers;
		return numbers;
	}

	@Override
	protected void swap(int firstIndex, int secondIndex) {
		int temp;
		temp = vertices[firstIndex].getNumber();
		vertices[firstIndex].setNumber(vertices[secondIndex].getNumber());
		vertices[secondIndex].setNumber(temp);
		if (OverviewListController.getSelectedItem().equals("Kupacrendez�s")) {
			OverviewGraphController
					.getNumberList()
					.get(firstIndex)
					.setYValue(
							OverviewGraphController.getNumberList()
									.get(secondIndex).getYValue());
			OverviewGraphController.getNumberList().get(secondIndex)
					.setYValue(temp);
		}
	}

	protected void setRestColor(int index) {
		for (int i = 0; i < index; ++i) {
			vertices[i].setColor("default");
			if (OverviewListController.getSelectedItem()
					.equals("Kupacrendez�s")) {
				OverviewChartController.setColor(OverviewGraphController
						.getNumberList().get(i).getNode(), "default");
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
