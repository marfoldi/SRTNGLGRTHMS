package srtnglgrthms.model.algorithm.raw;

import srtnglgrthms.controller.BenchmarkController;
import srtnglgrthms.model.algorithm.SortingAlgorithm;

/**
 *
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public class BubbleSortThread extends SortingThread {
	@Override
	public void doRun() {
		numbers = new int[SortingAlgorithm.getNumbers().length];
		System.arraycopy(SortingAlgorithm.getNumbers(), 0, numbers, 0,
				SortingAlgorithm.getNumbers().length);
		for (int i = 1; i < numbers.length; i++) {
			for (int j = 0; j < numbers.length - i; j++) {
				comparisonCounter++;
				if ((numbers[j]) > (numbers[j + 1])) {
					swapCounter++;
					int temp = numbers[j];
					numbers[j] = numbers[j + 1];
					numbers[j + 1] = temp;
				}
			}
		}
		BenchmarkController.addBenchmarkData(new BenchmarkData("Buborékrendezés",
				comparisonCounter, 3 * swapCounter, swapCounter));
	}

}
