package srtnglgrthms.model.algorithm.raw;

import srtnglgrthms.controller.BenchmarkController;
import srtnglgrthms.model.BenchmarkData;
import srtnglgrthms.model.algorithm.SortingAlgorithm;

/**
 *
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public class QuickThread extends SortingThread {
	@Override
	public void doRun() {
		numbers = new int[SortingAlgorithm.getNumbers().length];
		System.arraycopy(SortingAlgorithm.getNumbers(), 0, numbers, 0,
				SortingAlgorithm.getNumbers().length);
		quickSort(numbers, 0, numbers.length - 1);
		BenchmarkController.addBenchmarkData(new BenchmarkData("Gyorsrendezés", comparisonCounter,
				3 * swapCounter, swapCounter));
	}

	private int partition(int[] numbers, int begin, int end) {
		int pivot = numbers[end];
		int partitionIndex = begin;
		for (int i = begin; i < end; ++i) {
			comparisonCounter++;
			if (numbers[i] <= pivot) {
				if (i != partitionIndex) {
					swapCounter++;
					int temp = numbers[i];
					numbers[i] = numbers[partitionIndex];
					numbers[partitionIndex] = temp;
				}
				partitionIndex++;
			}
		}
		if (partitionIndex != end) {
			swapCounter++;
			int temp = numbers[partitionIndex];
			numbers[partitionIndex] = numbers[end];
			numbers[end] = temp;
		}
		return partitionIndex;
	}

	private void quickSort(int[] numbers, int begin, int end) {
		if (begin < end) {
			int partitionIndex = partition(numbers, begin, end);
			quickSort(numbers, begin, partitionIndex - 1);
			quickSort(numbers, partitionIndex + 1, end);
		}
	}
}
