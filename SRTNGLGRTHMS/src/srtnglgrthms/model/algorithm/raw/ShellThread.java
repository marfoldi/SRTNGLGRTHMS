package srtnglgrthms.model.algorithm.raw;

import srtnglgrthms.model.BenchmarkData;
import srtnglgrthms.model.algorithm.SortingAlgorithm;

/**
 * 
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public class ShellThread extends SortingThread{
	private static final int[] gapArray = { 1750, 701, 301, 132, 57, 23, 10, 4,
		1 };
	private int i, j, temp;
	
	@Override
	public void doRun() {
		numbers = new int[SortingAlgorithm.getNumbers().length];
		System.arraycopy(SortingAlgorithm.getNumbers(), 0, numbers, 0,
				SortingAlgorithm.getNumbers().length);
		for (int gap : gapArray) {
			i = gap;
			while (i < numbers.length) {
				temp = numbers[i];
				j = i - gap;
				while (j >= 0) {
					comparisonCounter++;
					if (numbers[j] > temp) {
						moveCounter++;
						numbers[j + gap] = numbers[j];
					} else
						break;
					j -= gap;
				}
				numbers[j + gap] = temp;
				i++;
			}
		}
		benchmarkData.add(new BenchmarkData("Shell rendezés",
				comparisonCounter, moveCounter, 0));
	}

}
