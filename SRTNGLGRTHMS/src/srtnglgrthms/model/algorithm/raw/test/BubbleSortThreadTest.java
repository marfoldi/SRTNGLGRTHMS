package srtnglgrthms.model.algorithm.raw.test;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;

import srtnglgrthms.model.algorithm.SortingAlgorithm;
import srtnglgrthms.model.algorithm.raw.BubbleSortThread;

public class BubbleSortThreadTest {

	@Test
	public void zeroElement() {
		SortingAlgorithm.setNumbers(new int[0]);
		BubbleSortThread bubbleSortTest = new BubbleSortThread();
		bubbleSortTest.doRun();

		assertArrayEquals(SortingAlgorithm.getNumbers(),bubbleSortTest.getNumbers());
		assertEquals(bubbleSortTest.getComparisonCounter(), 0);
		assertEquals(bubbleSortTest.getMoveCounter(), 0);
		assertEquals(bubbleSortTest.getSwapCounter(), 0);
	}

	@Test
	public void oneElement() {
		SortingAlgorithm.setNumbers(new int[] { 42 });
		BubbleSortThread bubbleSortTest = new BubbleSortThread();
		bubbleSortTest.doRun();

		assertArrayEquals(SortingAlgorithm.getNumbers(),bubbleSortTest.getNumbers());
		assertEquals(bubbleSortTest.getComparisonCounter(), 0);
		assertEquals(bubbleSortTest.getMoveCounter(), 0);
		assertEquals(bubbleSortTest.getSwapCounter(), 0);
	}

	@Test
	public void thousandRandomElement() {
		SortingAlgorithm.setNumbers(new int[100]);
		for (int i = 0; i < 100; i++)
	    {
			SortingAlgorithm.getNumbers()[i] = (int) (Math.random()*Integer.MAX_VALUE);
	    }
		BubbleSortThread bubbleSortTest = new BubbleSortThread();
		bubbleSortTest.doRun();
		Arrays.sort(SortingAlgorithm.getNumbers());
		assertArrayEquals(SortingAlgorithm.getNumbers(),bubbleSortTest.getNumbers());
	}

}
