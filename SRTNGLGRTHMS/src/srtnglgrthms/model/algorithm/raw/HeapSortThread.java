package srtnglgrthms.model.algorithm.raw;

import srtnglgrthms.controller.BenchmarkController;
import srtnglgrthms.model.algorithm.SortingAlgorithm;

/**
 *
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public class HeapSortThread extends SortingSortThread {

	@Override
	public void doRun() {
		numbers = new int[SortingAlgorithm.getNumbers().length];
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
		BenchmarkController.addBenchmarkData(new BenchmarkData("Kupacrendezés", comparisonCounter,
				3 * swapCounter, swapCounter));
	}

	private void buildStarterHeap(int[] numbers) {
		int starterIndex = numbers.length / 2 - 1;
		while (starterIndex >= 0) {
			buildHeap(numbers, starterIndex, numbers.length - 1);
			starterIndex--;
		}
	}

	private void buildHeap(int[] numbers, int begin, int end) {
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
