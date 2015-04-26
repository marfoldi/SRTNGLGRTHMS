package srtnglgrthms.model.algorithm.raw;

import srtnglgrthms.model.BenchmarkData;
import srtnglgrthms.model.algorithm.SortingAlgorithm;

/**
 * 
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public class InsertionThread extends SortingThread {
	@Override
	public void doRun() {
		numbers = new int[SortingAlgorithm.getNumbers().length];
		System.arraycopy(SortingAlgorithm.getNumbers(), 0, numbers, 0,
				SortingAlgorithm.getNumbers().length);
		for (int i = 0; i < numbers.length - 1; i++) {
			int temp = numbers[i + 1];
			int j;
			for (j = i; j >= 0; --j) {
				comparisonCounter++;
				if (temp < numbers[j]) {
					moveCounter++;
					numbers[j + 1] = numbers[j];
				} else {
					break;
				}
			}
			numbers[j + 1] = temp;
		}
		benchmarkData.add(new BenchmarkData("Beszúró rendezés",
				comparisonCounter, moveCounter, 0));
	}

}
