package srtnglgrthms.model.algorithm;

import java.util.LinkedList;

import srtnglgrthms.controller.OverviewChartController;
import srtnglgrthms.model.BenchmarkData;
import srtnglgrthms.model.CounterData;
import srtnglgrthms.model.RecursiveParameter;

/**
 * 
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public class QuickSort extends ChartAlgorithm {
	private static int begin;
	private static int end;
	private static int partitionIndex;
	private static int partitionHelpIndex;
	private static int pivot;
	private static int pivotIndex;
	private static boolean partitioned;

	private QuickSort() {
	}

	private static class SortHolder {
		private static final QuickSort INSTANCE = new QuickSort();
	}

	public static QuickSort getInstance() {
		return SortHolder.INSTANCE;
	}

	public void setDefaults() {
		begin = 0;
		end = numbers.length - 1;
		partitionIndex = begin;
		partitionHelpIndex = begin;
		partitioned = false;
		pivotIndex = end;
		pivot = data.get(pivotIndex).getYValue().intValue();
		recursiveCall = new LinkedList<>();
		counterData.clear();
		counterData.add(new CounterData("Összehasonlítások", "0"));
		counterData.add(new CounterData("Cserék", "0"));
		counterData.add(new CounterData("Vezérelem", "t["
				+ Integer.toString(pivotIndex) + "]"));
	}

	public void step() {
		setRestColor(begin, end);
		OverviewChartController.setColor(data.get(pivotIndex).getNode(),
				"select");
		counterData.get(2).setValue("t[" + Integer.toString(pivotIndex) + "]");
		if (!partitioned) {
			if (partitionHelpIndex < end) {
				counterData.get(0).incValue();
				OverviewChartController.setColor(data.get(partitionIndex)
						.getNode(), "swap");
				OverviewChartController.setColor(data.get(partitionHelpIndex)
						.getNode(), "swap");
				if (data.get(partitionHelpIndex).getYValue().intValue() <= pivot) {
					// if(partitionHelpIndex != partitionIndex) {
					counterData.get(1).incValue();
					swap(partitionHelpIndex, partitionIndex);
					// }
					partitionIndex++;
				}
				partitionHelpIndex++;
				return;
			}
			// if(partitionIndex!=end) {
			counterData.get(1).incValue();
			// }
			counterData.get(2).setValue(
					"t[" + Integer.toString(partitionIndex) + "]");
			OverviewChartController.setColor(
					data.get(partitionIndex).getNode(), "select");
			OverviewChartController.setColor(data.get(end).getNode(), "swap");
			pivotIndex = partitionIndex;
			partitioned = true;
		} else {
			if (begin < partitionIndex - 1)
				recursiveCall.add(new RecursiveParameter(begin,
						partitionIndex - 1));
			if (partitionIndex + 1 < end)
				recursiveCall.add(new RecursiveParameter(partitionIndex + 1,
						end));
			if (!recursiveCall.isEmpty()) {
				RecursiveParameter nextParameters = recursiveCall.remove();
				begin = (int) nextParameters.getFirstParameter();
				end = (int) nextParameters.getSecondParameter();
				partitioned = false;
				partitionIndex = begin;
				partitionHelpIndex = begin;
				pivotIndex = end;
				pivot = data.get(pivotIndex).getYValue().intValue();
				setRestColor(0, data.size());
			} else {
				for (int i = 0; i < data.size(); i++) {
					OverviewChartController.setColor(data.get(i).getNode(),
							"done");
				}
			}
		}
	}

	private void setRestColor(int begin, int end) {
		for (int i = begin; i < end; i++) {
			OverviewChartController.setColor(data.get(i).getNode(), "default");
		}
		for (int i = 0; i < begin; ++i) {
			OverviewChartController.setColor(data.get(i).getNode(), "fade");
		}
		for (int i = end + 1; i < data.size(); ++i) {
			OverviewChartController.setColor(data.get(i).getNode(), "fade");
		}
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
		quickSort(numbers, 0, numbers.length - 1);
		benchmarkData.add(new BenchmarkData("Gyorsrendezés", comparisonCounter,
				3 * swapCounter, swapCounter));
	};

	private static int partition(int[] numbers, int begin, int end) {
		int pivot = numbers[end];
		int partitionIndex = begin;
		for (int i = begin; i < end; ++i) {
			comparisonCounter++;
			if (numbers[i] <= pivot) {
				// if(i != partitionIndex) {
				swapCounter++;
				int temp = numbers[i];
				numbers[i] = numbers[partitionIndex];
				numbers[partitionIndex] = temp;
				// }
				partitionIndex++;
			}
		}
		// if(partitionIndex!=end) {
		swapCounter++;
		int temp = numbers[partitionIndex];
		numbers[partitionIndex] = numbers[end];
		numbers[end] = temp;
		// }
		return partitionIndex;
	}

	private static void quickSort(int[] numbers, int begin, int end) {
		if (begin < end) {
			int partitionIndex = partition(numbers, begin, end);
			quickSort(numbers, begin, partitionIndex - 1);
			quickSort(numbers, partitionIndex + 1, end);
		}
	}
}