package srtnglgrthms.model.algorithm;

import java.util.LinkedList;

import srtnglgrthms.controller.OverviewChartController;
import srtnglgrthms.controller.OverviewGraphController;
import srtnglgrthms.model.BenchmarkData;
import srtnglgrthms.model.CounterData;
import srtnglgrthms.model.RecursiveParameter;
import srtnglgrthms.model.graph.Vertex;

/**
 * 
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public class HeapSort extends GraphAlgorithm {
	private static int starterIndex;
	private static int downIndex;
	private static int recursiveCounter;
	private static boolean colored;
	private static boolean canSwap;
	private static boolean downIndexSetted;

	private HeapSort() {
	}

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
		starterIndex = vertices.length / 2 - 1;
		downIndex = starterIndex;
		recursiveCounter = vertices.length;
		colored = false;
		canSwap = true;
		downIndexSetted = false;
		counterData.clear();
		counterData.add(new CounterData("Összehasonlítások", "0"));
		counterData.add(new CounterData("Cserék", "0"));
	}

	@Override
	public void step() {
		if (starterIndex >= 0) {
			downIndex = buildHeap(downIndex, vertices.length - 1, true);
			if (downIndex != -1) {
				return;
			} else {
				starterIndex--;
				downIndex = starterIndex;
			}
		} else if (recursiveCounter >= 1) {
			if (!downIndexSetted) {
				downIndex = 0;
				colored = false;
				recursiveCounter--;
				downIndexSetted = true;
			}
			if (canSwap) {
				counterData.get(1).incValue();
				swap(0, recursiveCounter);
				vertices[0].setColor("swap");
				OverviewChartController.setColor(OverviewGraphController
						.getNumberList().get(0).getNode(), "swap");
				vertices[recursiveCounter].setColor("done");
				OverviewChartController.setColor(OverviewGraphController
						.getNumberList().get(recursiveCounter).getNode(),
						"done");
				OverviewGraphController.reloadGraph();
				OverviewGraphController.addVertices();
				canSwap = false;
				return;
			}
			OverviewGraphController.reloadGraph();
			OverviewGraphController.addVertices();
			downIndex = buildHeap(downIndex, recursiveCounter - 1, false);
			OverviewGraphController.reloadGraph();
			OverviewGraphController.addVertices();
			if (downIndex != -1) {
				canSwap = false;
				return;
			} else {
				canSwap = true;
				recursiveCounter--;
				downIndex = 0;
			}
		}
		if (recursiveCounter == 0) {
			vertices[recursiveCounter].setColor("done");
			OverviewChartController.setColor(OverviewGraphController
					.getNumberList().get(recursiveCounter).getNode(), "done");
		}
	}

	private int buildHeap(int startIndex, int endIndex, boolean firstRun) {
		setRestColor(recursiveCounter);
		if (2 * startIndex + 1 <= endIndex) {
			if (!colored) {
				try {
					vertices[startIndex].setColor("swap");
					OverviewChartController.setColor(OverviewGraphController
							.getNumberList().get(startIndex).getNode(), "swap");
					if (2 * startIndex + 2 < endIndex || firstRun
							&& vertices.length % 2 != 0) {
						OverviewChartController.setColor(
								OverviewGraphController.getNumberList()
										.get(2 * startIndex + 2).getNode(),
								"swap");
						vertices[2 * startIndex + 2].setColor("swap");
					}
					if (startIndex + 1 < endIndex) {
						OverviewChartController.setColor(
								OverviewGraphController.getNumberList()
										.get(2 * startIndex + 1).getNode(),
								"swap");
						vertices[2 * startIndex + 1].setColor("swap");
					}
					colored = true;
					return startIndex;
				} catch (ArrayIndexOutOfBoundsException aioobe) {
					setRestColor(recursiveCounter);
					OverviewGraphController.reloadGraph();
					OverviewGraphController.addVertices();
					colored = false;
					return -1;
				}
			}
			int ir;
			if (2 * startIndex + 2 <= endIndex)
				counterData.get(0).incValue();
			if (2 * startIndex + 2 > endIndex
					|| vertices[2 * startIndex + 1].getNumber() > vertices[2 * startIndex + 2]
							.getNumber()) {
				ir = 2 * startIndex + 1;
			} else
				ir = 2 * startIndex + 2;
			counterData.get(0).incValue();
			if (vertices[startIndex].getNumber() >= vertices[ir].getNumber()) {
				colored = false;
				return -1;
			} else {
				if (startIndex == 0) {
					vertices[startIndex].setColor("done");
					OverviewChartController.setColor(OverviewGraphController
							.getNumberList().get(startIndex).getNode(), "done");
				} else {
					vertices[startIndex].setColor("select");
					OverviewChartController.setColor(OverviewGraphController
							.getNumberList().get(startIndex).getNode(),
							"select");
				}
				OverviewChartController.setColor(OverviewGraphController
						.getNumberList().get(ir).getNode(), "swap");
				vertices[ir].setColor("swap");
				counterData.get(1).incValue();
				swap(startIndex, ir);
				startIndex = ir;
				OverviewGraphController.reloadGraph();
				OverviewGraphController.addVertices();
			}
		} else {
			colored = false;
			return -1;
		}
		colored = false;
		return startIndex;
	}

	@Override
	public void setDefaultGraph() {
		OverviewGraphController.setVertices(new Vertex[checkedArray.length]);
		vertices = OverviewGraphController.getVertices();
		;
		vertices[0] = new Vertex(400, 20, vertexSize, checkedArray[0]);
		recursiveCall.add(new RecursiveParameter(400, 20, 1.0));
		for (int i = 1; i < vertices.length - 1; i += 2) {
			if (!recursiveCall.isEmpty()) {
				RecursiveParameter nextParameters = recursiveCall.remove();
				double x = nextParameters.getFirstParameter();
				double y = nextParameters.getSecondParameter();
				double delta = nextParameters.getThirdParameter();
				vertices[i] = new Vertex(x - xGap * delta, y + yGap,
						vertexSize, checkedArray[i]);
				vertices[i + 1] = new Vertex(x + xGap * delta, y + yGap,
						vertexSize, checkedArray[i + 1]);
				recursiveCall.add(new RecursiveParameter(x - xGap * delta, y
						+ yGap, delta * 0.5));
				recursiveCall.add(new RecursiveParameter(x + xGap * delta, y
						+ yGap, delta * 0.5));
			}
		}
		if (vertices.length % 2 == 0) {
			RecursiveParameter nextParameters = recursiveCall.remove();
			double x = nextParameters.getFirstParameter();
			double y = nextParameters.getSecondParameter();
			double delta = nextParameters.getThirdParameter();
			vertices[vertices.length - 1] = new Vertex(x - xGap * delta, y
					+ yGap, vertexSize, checkedArray[vertices.length - 1]);
			graph.bindVertexes(vertices[(vertices.length - 1) / 2],
					vertices[vertices.length - 1]);
		}
		OverviewGraphController.addVertices();
	}

	static int swapCounter; // Increment this counter whenever a swap takes
							// place
	static int comparisonCounter; // Increment this counter whenever a
									// comparison takes place
	public static Runnable sort = () -> {
		swapCounter = 0;
		comparisonCounter = 0;
		int[] numbers = new int[SortingAlgorithm.getNumbers().length];
		System.arraycopy(SortingAlgorithm.getNumbers(), 0, numbers, 0,
				SortingAlgorithm.getNumbers().length);
		if (numbers.length >= 1) {
			buildStarterHeap(numbers);
			int recursiveCounter = numbers.length - 1;
			while (recursiveCounter >= 1) {
				swapCounter++;
				int temp = numbers[0];
				numbers[0] = numbers[recursiveCounter];
				numbers[recursiveCounter] = temp;
				buildHeap(numbers, 0, recursiveCounter - 1);
				recursiveCounter--;
			}
		}
		benchmarkData.add(new BenchmarkData("Kupacrendezés", comparisonCounter,
				3 * swapCounter, swapCounter));
	};

	private static void buildStarterHeap(int[] numbers) {
		int starterIndex = numbers.length / 2 - 1;
		while (starterIndex >= 0) {
			buildHeap(numbers, starterIndex, numbers.length - 1);
			starterIndex--;
		}
	}

	private static void buildHeap(int[] numbers, int begin, int end) {
		int index;
		while (2 * begin + 1 <= end) {
			if (2 * begin + 2 <= end)
				comparisonCounter++;
			if (2 * begin + 2 > end
					|| numbers[2 * begin + 1] > numbers[2 * begin + 2]) {
				index = 2 * begin + 1;
			} else
				index = 2 * begin + 2;
			comparisonCounter++;
			if (numbers[begin] >= numbers[index]) {
				return;
			} else {
				swapCounter++;
				int temp = numbers[begin];
				numbers[begin] = numbers[index];
				numbers[index] = temp;
				begin = index;
			}
		}
	}
}