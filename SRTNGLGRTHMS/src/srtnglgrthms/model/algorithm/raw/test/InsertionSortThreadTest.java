package srtnglgrthms.model.algorithm.raw.test;

import org.junit.Test;

import srtnglgrthms.controller.BenchmarkController;
import srtnglgrthms.model.algorithm.SortingAlgorithm;
import srtnglgrthms.model.algorithm.raw.BenchmarkData;
import srtnglgrthms.model.algorithm.raw.InsertionSortThread;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

public class InsertionSortThreadTest {

	@Test
	public void zeroElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
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
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[] { 42 });
		InsertionSortThread insertionSortTest = new InsertionSortThread();
		insertionSortTest.doRun();
		Arrays.sort(SortingAlgorithm.getNumbers());

		assertArrayEquals(SortingAlgorithm.getNumbers(),insertionSortTest.getNumbers());
		assertEquals(insertionSortTest.getComparisonCounter(), 0);
		assertEquals(insertionSortTest.getMoveCounter(), 0);
		assertEquals(insertionSortTest.getSwapCounter(), 0);
	}
	
	@Test
	public void fiveConcretElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[] { 42, 20, 7, 18, 100});
		InsertionSortThread insertionSortTest = new InsertionSortThread();
		insertionSortTest.doRun();
		Arrays.sort(SortingAlgorithm.getNumbers());

		assertArrayEquals(SortingAlgorithm.getNumbers(),insertionSortTest.getNumbers());
		assertEquals(insertionSortTest.getComparisonCounter(), 7);
		assertEquals(insertionSortTest.getMoveCounter(), 5);
		assertEquals(insertionSortTest.getSwapCounter(), 0);
	}

	@Test
	public void thousandRandomElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
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

	@Test
	public void millionRandomElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[1000000]);
		for (int i = 0; i < 1000000; i++)
	    {
			SortingAlgorithm.getNumbers()[i] = (int) (Math.random()*Integer.MAX_VALUE);
	    }
		InsertionSortThread insertionSortTest = new InsertionSortThread();
		insertionSortTest.doRun();
		Arrays.sort(SortingAlgorithm.getNumbers());
		assertArrayEquals(SortingAlgorithm.getNumbers(),insertionSortTest.getNumbers());
	}
}
