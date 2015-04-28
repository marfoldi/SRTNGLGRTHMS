package srtnglgrthms.model.algorithm.raw;

import srtnglgrthms.controller.BenchmarkController;
import srtnglgrthms.model.BenchmarkData;
import srtnglgrthms.model.algorithm.SortingAlgorithm;

/**
 *
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public class TournamentThread extends SortingThread {
	int maxIndex = 0;

	@Override
	public void doRun() {
		int length = 1;
		int power = (int) Math
				.ceil(Math.log(SortingAlgorithm.getNumbers().length)
						/ Math.log(2));
		for (int i = 0; i < power; ++i)
			length *= 2;
		numbers = new int[length];
		for (int i = SortingAlgorithm.getNumbers().length; i < length; ++i) {
			numbers[i] = -1;
		}
		if (length > SortingAlgorithm.getNumbers().length)
			System.arraycopy(SortingAlgorithm.getNumbers(), 0, numbers, 0,
					length - (length - SortingAlgorithm.getNumbers().length));
		else
			System.arraycopy(SortingAlgorithm.getNumbers(), 0, numbers, 0,
					length);
		int[] finalNumbers = new int[length * 2 - 1];
		for (int i = 0; i < length; ++i) {
			finalNumbers[i] = -2;
		}
		int j = 0;
		for (int i = length - 1; i < finalNumbers.length; ++i) {
			finalNumbers[i] = numbers[j];
			j++;
		}
		for (int i = finalNumbers.length / 2; i >= 1; --i) {
			comparisonCounter++;
			moveCounter++;
			finalNumbers[i - 1] = finalNumbers[2 * i] > finalNumbers[2 * i - 1] ? finalNumbers[2 * i]
					: finalNumbers[2 * i - 1];
		}
		int recursiveCounter = SortingAlgorithm.getNumbers().length - 1;
		while (recursiveCounter >= 1) {
			maxIndex = 0;
			while (maxIndex < finalNumbers.length / 2) {
				comparisonCounter++;
				maxIndex = finalNumbers[maxIndex] == finalNumbers[2 * maxIndex + 1] ? 2 * maxIndex + 1
						: 2 * maxIndex + 2;
			}
			finalNumbers[maxIndex] = -1;
			if (maxIndex % 2 == 0)
				maxIndex = maxIndex / 2 - 1;
			else
				maxIndex = maxIndex / 2;
			while (maxIndex >= 0) {
				comparisonCounter++;
				moveCounter++;
				finalNumbers[maxIndex] = finalNumbers[2 * maxIndex + 1] > finalNumbers[2 * maxIndex + 2] ? finalNumbers[2 * maxIndex + 1]
						: finalNumbers[2 * maxIndex + 2];
				if (maxIndex % 2 == 0)
					maxIndex = maxIndex / 2 - 1;
				else
					maxIndex = maxIndex / 2;
			}
			recursiveCounter--;
		}
		BenchmarkController.addBenchmarkData(new BenchmarkData("Versenyrendezés",
				comparisonCounter, moveCounter, 0));
	}

}
