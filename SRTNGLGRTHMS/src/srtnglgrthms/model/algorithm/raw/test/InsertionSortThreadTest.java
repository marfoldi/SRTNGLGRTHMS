package srtnglgrthms.model.algorithm.raw.test;

import org.junit.Test;

import srtnglgrthms.model.algorithm.SortingAlgorithm;
import srtnglgrthms.model.algorithm.raw.InsertionSortThread;
import static org.junit.Assert.*;

import java.util.Arrays;

public class InsertionSortThreadTest {

	@Test
	public void zeroElement() {
		SortingAlgorithm.setNumbers(new int[0]);
		InsertionSortThread insertionSortTest = new InsertionSortThread();
		insertionSortTest.doRun();

		assertArrayEquals(SortingAlgorithm.getNumbers(),insertionSortTest.getNumbers());
		assertEquals(insertionSortTest.getComparisonCounter(), 0);
		assertEquals(insertionSortTest.getMoveCounter(), 0);
		assertEquals(insertionSortTest.getSwapCounter(), 0);
	}

	@Test
	public void oneElement() {
		SortingAlgorithm.setNumbers(new int[] { 42 });
		InsertionSortThread insertionSortTest = new InsertionSortThread();
		insertionSortTest.doRun();

		assertArrayEquals(SortingAlgorithm.getNumbers(),insertionSortTest.getNumbers());
		assertEquals(insertionSortTest.getComparisonCounter(), 0);
		assertEquals(insertionSortTest.getMoveCounter(), 0);
		assertEquals(insertionSortTest.getSwapCounter(), 0);
	}

	@Test
	public void thousandRandomElement() {
		SortingAlgorithm.setNumbers(new int[100]);
		for (int i = 0; i < 100; i++)
	    {
			SortingAlgorithm.getNumbers()[i] = (int) (Math.random()*Integer.MAX_VALUE);
	    }
		InsertionSortThread insertionSortTest = new InsertionSortThread();
		insertionSortTest.doRun();
		Arrays.sort(SortingAlgorithm.getNumbers());
		assertArrayEquals(SortingAlgorithm.getNumbers(),insertionSortTest.getNumbers());
	}


}
