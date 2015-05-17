package srtnglgrthms.model.algorithm.raw.test;

import org.junit.Test;

import srtnglgrthms.controller.BenchmarkController;
import srtnglgrthms.model.algorithm.SortingAlgorithm;
import srtnglgrthms.model.algorithm.raw.BenchmarkData;
import srtnglgrthms.model.algorithm.raw.BubbleSortThread;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

public class BubbleSortThreadTest {

	@Test
	public void zeroElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
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
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[] { 42 });
		BubbleSortThread bubbleSortTest = new BubbleSortThread();
		bubbleSortTest.doRun();
		Arrays.sort(SortingAlgorithm.getNumbers());
		
		assertArrayEquals(SortingAlgorithm.getNumbers(),bubbleSortTest.getNumbers());
		assertEquals(bubbleSortTest.getComparisonCounter(), 0);
		assertEquals(bubbleSortTest.getMoveCounter(), 0);
		assertEquals(bubbleSortTest.getSwapCounter(), 0);
	}
	
	@Test
	public void fiveConcreteElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[] { 42, 20, 7, 18, 100});
		BubbleSortThread bubbleSortTest = new BubbleSortThread();
		bubbleSortTest.doRun();
		Arrays.sort(SortingAlgorithm.getNumbers());

		assertArrayEquals(SortingAlgorithm.getNumbers(),bubbleSortTest.getNumbers());
		assertEquals(bubbleSortTest.getComparisonCounter(), 10);
		assertEquals(bubbleSortTest.getMoveCounter(), 0);
		assertEquals(bubbleSortTest.getSwapCounter(), 5);
	}

	@Test
	public void thousandRandomElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
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
	
	@Test
	public void millionRandomElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[1000000]);
		for (int i = 0; i < 1000000; i++)
	    {
			SortingAlgorithm.getNumbers()[i] = (int) (Math.random()*Integer.MAX_VALUE);
	    }
		BubbleSortThread bubbleSortTest = new BubbleSortThread();
		bubbleSortTest.doRun();
		Arrays.sort(SortingAlgorithm.getNumbers());
		assertArrayEquals(SortingAlgorithm.getNumbers(),bubbleSortTest.getNumbers());
	}
}
