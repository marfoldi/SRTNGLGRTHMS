package srtnglgrthms.model.algorithm.raw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import srtnglgrthms.model.BenchmarkData;
import srtnglgrthms.model.algorithm.SortingAlgorithm;

/**
 * 
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public class ShellThread extends SortingThread {
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
		benchmarkData.add(new BenchmarkData("Shell rendezés",
				comparisonCounter, moveCounter, 0));
	}
	
	private static Integer[] generateGapArray() {
		int i, last2ind = 0, last3ind = 0; 
		List<Integer> pratt = new ArrayList<>();

		pratt.add(1);
		for (i=1; i < SortingAlgorithm.getNumbers().length; ++i) { 
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
		return pratt.toArray(new Integer[0]);
	}

}
