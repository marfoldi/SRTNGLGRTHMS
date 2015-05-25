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
		assertEquals(heapSortTest.getMoveCounter(), 0);
		assertEquals(heapSortTest.getSwapCounter(), 9);
	}
	
	@Test
	public void sixteenConcretElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[] {307, 42, 200, 307, 65, 2, 89, 120, 189, 420, 55, 12, 7, 99, 510, 120});
		HeapSortThread heapSortTest = new HeapSortThread();
		heapSortTest.doRun();
		Arrays.sort(SortingAlgorithm.getNumbers());
		
		assertArrayEquals(SortingAlgorithm.getNumbers(),heapSortTest.getNumbers());
		assertEquals(heapSortTest.getComparisonCounter(), 81);
		assertEquals(heapSortTest.getMoveCounter(), 0);
		assertEquals(heapSortTest.getSwapCounter(), 51);
	}
	
	@Test
	public void tenSortedElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[] {2, 42, 65, 89, 120, 189, 200, 291, 307, 420});
		HeapSortThread heapSortTest = new HeapSortThread();
		heapSortTest.doRun();
		Arrays.sort(SortingAlgorithm.getNumbers());
		
		assertArrayEquals(SortingAlgorithm.getNumbers(),heapSortTest.getNumbers());
		assertEquals(heapSortTest.getComparisonCounter(), 41);
		assertEquals(heapSortTest.getMoveCounter(), 0);
		assertEquals(heapSortTest.getSwapCounter(), 30);
	}
	
	@Test
	public void tenReverseSortedElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[] {420, 307, 291, 200, 189, 120, 89, 65, 42, 2});
		HeapSortThread heapSortTest = new HeapSortThread();
		heapSortTest.doRun();
		Arrays.sort(SortingAlgorithm.getNumbers());
		
		assertArrayEquals(SortingAlgorithm.getNumbers(),heapSortTest.getNumbers());
		assertEquals(heapSortTest.getComparisonCounter(), 35);
		assertEquals(heapSortTest.getMoveCounter(), 0);
		assertEquals(heapSortTest.getSwapCounter(), 21);
	}
	
	@Test
	public void tenAlmostSortedElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[] {1, 20, 56, 76, 55, 127, 160, 132, 191, 243});
		HeapSortThread heapSortTest = new HeapSortThread();
		heapSortTest.doRun();
		Arrays.sort(SortingAlgorithm.getNumbers());
		
		assertArrayEquals(SortingAlgorithm.getNumbers(),heapSortTest.getNumbers());
		assertEquals(heapSortTest.getComparisonCounter(), 40);
		assertEquals(heapSortTest.getMoveCounter(), 0);
		assertEquals(heapSortTest.getSwapCounter(), 29);
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
		System.out.println(heapSortTest.getComparisonCounter());
		System.out.println(heapSortTest.getSwapCounter());
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
