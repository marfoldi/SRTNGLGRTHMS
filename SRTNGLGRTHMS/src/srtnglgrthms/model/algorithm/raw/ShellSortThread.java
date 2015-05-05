package srtnglgrthms.model.algorithm.raw;

import java.util.ArrayList;
import java.util.List;

import srtnglgrthms.controller.BenchmarkController;
import srtnglgrthms.model.algorithm.SortingAlgorithm;

/**
 *
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public class ShellSortThread extends SortingSortThread {
	private static Integer[] gapArray = generateGapArray();
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
		BenchmarkController.addBenchmarkData(new BenchmarkData("Shell rendezés",
				comparisonCounter, moveCounter, 0));
	}

	private static Integer[] generateGapArray() {
		int nextGap = SortingAlgorithm.getNumbers().length/2;
		List<Integer> gapList = new ArrayList<>();
		while(nextGap/2>=1) {
			gapList.add(nextGap);
			nextGap/=2;
		}
		gapList.add(1);
		return gapList.toArray(new Integer[0]);
	}

}
