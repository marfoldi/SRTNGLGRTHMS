package srtnglgrthms.model.algorithm.raw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import srtnglgrthms.controller.BenchmarkController;
import srtnglgrthms.model.algorithm.SortingAlgorithm;

/**
 *
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public class ShellSortThread extends SortingThread {

	@Override
	public void doRun() {
		numbers = new int[SortingAlgorithm.getNumbers().length];
		System.arraycopy(SortingAlgorithm.getNumbers(), 0, numbers, 0,
				SortingAlgorithm.getNumbers().length);
		Integer[] gapArray = generateGapArray();
		int i, j, temp;
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
		int last2ind = 0;
		int last3ind = 0;
		List<Integer> pratt = new ArrayList<>();

		pratt.add(1);
		for (int i=1; i < SortingAlgorithm.getNumbers().length; ++i) { 
		if (pratt.get(last2ind)*2 < pratt.get(last3ind)*3) { 
			pratt.add(pratt.get(last2ind)*2); 
			last2ind++; 
		} 
		else if (pratt.get(last2ind)*2 > pratt.get(last3ind)*3) { 
			pratt.add(pratt.get(last3ind)*3); 
			last3ind++; 
		} 
		else { 
			pratt.add(pratt.get(last2ind)*2); 
			last2ind++; 
			last3ind++; 
		} 
		if(pratt.get(i)>=SortingAlgorithm.getNumbers().length) break;
		}
		Collections.reverse(pratt);
		pratt.remove(0);
		return pratt.toArray(new Integer[0]);
	}

}
