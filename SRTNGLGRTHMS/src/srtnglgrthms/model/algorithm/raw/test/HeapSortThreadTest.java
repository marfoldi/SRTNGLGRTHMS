package srtnglgrthms.model.algorithm.raw.test;

import org.junit.Test;

import srtnglgrthms.controller.BenchmarkController;
import srtnglgrthms.model.algorithm.SortingAlgorithm;
import srtnglgrthms.model.algorithm.raw.BenchmarkData;
import srtnglgrthms.model.algorithm.raw.HeapSortThread;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

public class HeapSortThreadTest {

	@Test
	public void zeroElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
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
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[] { 42 });
		HeapSortThread heapSortTest = new HeapSortThread();
		heapSortTest.doRun();
		Arrays.sort(SortingAlgorithm.getNumbers());

		assertArrayEquals(SortingAlgorithm.getNumbers(),heapSortTest.getNumbers());
		assertEquals(heapSortTest.getComparisonCounter(), 0);
		assertEquals(heapSortTest.getMoveCounter(), 0);
		assertEquals(heapSortTest.getSwapCounter(), 0);
	}
	
	@Test
	public void fiveConcretElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[] { 42, 20, 7, 18, 100});
		HeapSortThread heapSortTest = new HeapSortThread();
		heapSortTest.doRun();
		Arrays.sort(SortingAlgorithm.getNumbers());
		
		assertArrayEquals(SortingAlgorithm.getNumbers(),heapSortTest.getNumbers());
		assertEquals(heapSortTest.getComparisonCounter(), 12);
		assertEquals(heapSortTest.getMoveCounter(), 27);
		assertEquals(heapSortTest.getSwapCounter(), 9);
	}

	@Test
	public void thousandRandomElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
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

	@Test
	public void millionRandomElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[1000000]);
		for (int i = 0; i < 1000000; i++)
	    {
			SortingAlgorithm.getNumbers()[i] = (int) (Math.random()*Integer.MAX_VALUE);
	    }
		HeapSortThread heapSortTest = new HeapSortThread();
		heapSortTest.doRun();
		Arrays.sort(SortingAlgorithm.getNumbers());
		assertArrayEquals(SortingAlgorithm.getNumbers(),heapSortTest.getNumbers());
	}

}
