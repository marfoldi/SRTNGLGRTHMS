package srtnglgrthms.model.algorithm.raw.test;

import org.junit.Test;

import srtnglgrthms.model.algorithm.SortingAlgorithm;
import srtnglgrthms.model.algorithm.raw.BenchmarkData;
import srtnglgrthms.model.algorithm.raw.QuickSortThread;
import srtnglgrthms.controller.BenchmarkController;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

public class QuickSortThreadTest {

	@Test
	public void zeroElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
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
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[] { 42 });
		QuickSortThread quickSortTest = new QuickSortThread();
		quickSortTest.doRun();
		Arrays.sort(SortingAlgorithm.getNumbers());

		assertArrayEquals(SortingAlgorithm.getNumbers(),quickSortTest.getNumbers());
		assertEquals(quickSortTest.getComparisonCounter(), 0);
		assertEquals(quickSortTest.getMoveCounter(), 0);
		assertEquals(quickSortTest.getSwapCounter(), 0);
	}
	
	@Test
	public void fiveConcretElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[] { 42, 20, 7, 18, 100});
		QuickSortThread quickSortTest = new QuickSortThread();
		quickSortTest.doRun();
		Arrays.sort(SortingAlgorithm.getNumbers());

		assertArrayEquals(SortingAlgorithm.getNumbers(),quickSortTest.getNumbers());
		assertEquals(quickSortTest.getComparisonCounter(), 8);
		assertEquals(quickSortTest.getMoveCounter(), 0);
		assertEquals(quickSortTest.getSwapCounter(), 3);
	}

	@Test
	public void thousandRandomElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
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

	@Test
	public void millionRandomElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[1000000]);
		for (int i = 0; i < 1000000; i++)
	    {
			SortingAlgorithm.getNumbers()[i] = (int) (Math.random()*Integer.MAX_VALUE);
	    }
		QuickSortThread quickSortTest = new QuickSortThread();
		quickSortTest.doRun();
		Arrays.sort(SortingAlgorithm.getNumbers());
		assertArrayEquals(SortingAlgorithm.getNumbers(),quickSortTest.getNumbers());
	}

}
