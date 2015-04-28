package srtnglgrthms.model.algorithm.raw.test;

import org.junit.Test;

import srtnglgrthms.model.algorithm.SortingAlgorithm;
import srtnglgrthms.model.algorithm.raw.HeapSortThread;
import static org.junit.Assert.*;

import java.util.Arrays;

public class HeapSortThreadTest {

	@Test
	public void zeroElement() {
		SortingAlgorithm.setNumbers(new int[0]);
		HeapSortThread heapSortTest = new HeapSortThread();
		heapSortTest.doRun();

		assertArrayEquals(SortingAlgorithm.getNumbers(),heapSortTest.getNumbers());
		assertEquals(heapSortTest.getComparisonCounter(), 0);
		assertEquals(heapSortTest.getMoveCounter(), 0);
		assertEquals(heapSortTest.getSwapCounter(), 0);
	}

	@Test
	public void oneElement() {
		SortingAlgorithm.setNumbers(new int[] { 42 });
		HeapSortThread heapSortTest = new HeapSortThread();
		heapSortTest.doRun();

		assertArrayEquals(SortingAlgorithm.getNumbers(),heapSortTest.getNumbers());
		assertEquals(heapSortTest.getComparisonCounter(), 0);
		assertEquals(heapSortTest.getMoveCounter(), 0);
		assertEquals(heapSortTest.getSwapCounter(), 0);
	}

	@Test
	public void thousandRandomElement() {
		SortingAlgorithm.setNumbers(new int[100]);
		for (int i = 0; i < 100; i++)
	    {
			SortingAlgorithm.getNumbers()[i] = (int) (Math.random()*Integer.MAX_VALUE);
	    }
		HeapSortThread heapSortTest = new HeapSortThread();
		heapSortTest.doRun();
		Arrays.sort(SortingAlgorithm.getNumbers());
		assertArrayEquals(SortingAlgorithm.getNumbers(),heapSortTest.getNumbers());
	}


}
