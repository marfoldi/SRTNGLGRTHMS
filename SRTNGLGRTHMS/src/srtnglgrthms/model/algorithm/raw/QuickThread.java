package srtnglgrthms.model.algorithm.raw;

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
		benchmarkData.add(new BenchmarkData("Gyorsrendezés", comparisonCounter,
				3 * swapCounter, swapCounter));
	}

    private void quickSort(int[] numbers, int low, int high) {
        int i = low;
        int j = high;

        // pivot is middle index
        int pivot = numbers[low + (high - low) / 2];

        // Divide into two arrays
        while (i <= j) {
            /**
             * As shown in above image, In each iteration, we will identify a
             * number from left side which is greater then the pivot value, and
             * a number from right side which is less then the pivot value. Once
             * search is complete, we can swap both numbers.
             */
        	comparisonCounter++;
            while (numbers[i] < pivot) {
                i++;
            }

            comparisonCounter++;
            while (numbers[j] > pivot) {
                j--;
            }

            if (i <= j) {
            	swapCounter++;
                int temp = numbers[i];
                numbers[i] = numbers[j];
                numbers[j] = temp;
                // move index to next position on both sides
                i++;
                j--;
            }
        }

        // calls quickSort() method recursively
        if (low < j) {
            quickSort(numbers, low, j);
        }

        if (i < high) {
            quickSort(numbers, i, high);
        }
    }
	/*private int partition(int[] numbers, int begin, int end) {
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
			if(begin+1<partitionIndex-1) quickSort(numbers, begin, partitionIndex - 1);
			if(partitionIndex+2<end) quickSort(numbers, partitionIndex + 1, end);
		}
	}*/
}
