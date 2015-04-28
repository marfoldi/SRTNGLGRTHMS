package srtnglgrthms.model.algorithm.raw.test;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;

import srtnglgrthms.model.algorithm.SortingAlgorithm;
import srtnglgrthms.model.algorithm.raw.QuickSortThread;

public class QuickSortThreadTest {

	@Test
	public void zeroElement() {
		SortingAlgorithm.setNumbers(new int[0]);
		QuickSortThread quickSortTest = new QuickSortThread();
		quickSortTest.doRun();

		assertArrayEquals(SortingAlgorithm.getNumbers(),quickSortTest.getNumbers());
		assertEquals(quickSortTest.getComparisonCounter(), 0);
		assertEquals(quickSortTest.getMoveCounter(), 0);
		assertEquals(quickSortTest.getSwapCounter(), 0);
	}

	@Test
	public void oneElement() {
		SortingAlgorithm.setNumbers(new int[] { 42 });
		QuickSortThread quickSortTest = new QuickSortThread();
		quickSortTest.doRun();

		assertArrayEquals(SortingAlgorithm.getNumbers(),quickSortTest.getNumbers());
		assertEquals(quickSortTest.getComparisonCounter(), 0);
		assertEquals(quickSortTest.getMoveCounter(), 0);
		assertEquals(quickSortTest.getSwapCounter(), 0);
	}

	@Test
	public void thousandRandomElement() {
		SortingAlgorithm.setNumbers(new int[100]);
		for (int i = 0; i < 100; i++)
	    {
			SortingAlgorithm.getNumbers()[i] = (int) (Math.random()*Integer.MAX_VALUE);
	    }
		QuickSortThread quickSortTest = new QuickSortThread();
		quickSortTest.doRun();
		Arrays.sort(SortingAlgorithm.getNumbers());
		assertArrayEquals(SortingAlgorithm.getNumbers(),quickSortTest.getNumbers());
	}


}
