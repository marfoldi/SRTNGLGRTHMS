package srtnglgrthms.model.algorithm.raw.test;

import org.junit.Test;

import srtnglgrthms.controller.BenchmarkController;
import srtnglgrthms.model.algorithm.SortingAlgorithm;
import srtnglgrthms.model.algorithm.raw.BenchmarkData;
import srtnglgrthms.model.algorithm.raw.ShellSortThread;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

public class ShellSortThreadTest {

	@Test
	public void zeroElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[0]);
		ShellSortThread shellSortTest = new ShellSortThread();
		shellSortTest.doRun();

		assertArrayEquals(SortingAlgorithm.getNumbers(),shellSortTest.getNumbers());
		assertEquals(shellSortTest.getComparisonCounter(), 0);
		assertEquals(shellSortTest.getMoveCounter(), 0);
		assertEquals(shellSortTest.getSwapCounter(), 0);
	}

	@Test
	public void oneElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[] { 42 });
		ShellSortThread shellSortTest = new ShellSortThread();
		shellSortTest.doRun();
		Arrays.sort(SortingAlgorithm.getNumbers());

		assertArrayEquals(SortingAlgorithm.getNumbers(),shellSortTest.getNumbers());
		assertEquals(shellSortTest.getComparisonCounter(), 0);
		assertEquals(shellSortTest.getMoveCounter(), 0);
		assertEquals(shellSortTest.getSwapCounter(), 0);
	}
	
	@Test
	public void fiveConcretElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[] { 42, 20, 7, 18, 100});
		ShellSortThread shellSortTest = new ShellSortThread();
		shellSortTest.doRun();
		Arrays.sort(SortingAlgorithm.getNumbers());

		assertArrayEquals(SortingAlgorithm.getNumbers(),shellSortTest.getNumbers());
		assertEquals(shellSortTest.getComparisonCounter(), 11);
		assertEquals(shellSortTest.getMoveCounter(), 3);
		assertEquals(shellSortTest.getSwapCounter(), 0);
	}
	
	@Test
	public void sixteenConcretElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[] {307, 42, 200, 307, 65, 2, 89, 120, 189, 420, 55, 12, 7, 99, 510, 120});
		ShellSortThread shellSortTest = new ShellSortThread();
		shellSortTest.doRun();
		Arrays.sort(SortingAlgorithm.getNumbers());

		assertArrayEquals(SortingAlgorithm.getNumbers(),shellSortTest.getNumbers());
		assertEquals(shellSortTest.getComparisonCounter(), 93);
		assertEquals(shellSortTest.getMoveCounter(), 17);
		assertEquals(shellSortTest.getSwapCounter(), 0);
	}
	
	@Test
	public void tenSortedElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[] {2, 42, 65, 89, 120, 189, 200, 291, 307, 420});
		ShellSortThread shellSortTest = new ShellSortThread();
		shellSortTest.doRun();
		Arrays.sort(SortingAlgorithm.getNumbers());

		assertArrayEquals(SortingAlgorithm.getNumbers(),shellSortTest.getNumbers());
		assertEquals(shellSortTest.getComparisonCounter(), 37);
		assertEquals(shellSortTest.getMoveCounter(), 0);
		assertEquals(shellSortTest.getSwapCounter(), 0);
	}
	
	@Test
	public void tenReverseSortedElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[] {420, 307, 291, 200, 189, 120, 89, 65, 42, 2});
		ShellSortThread shellSortTest = new ShellSortThread();
		shellSortTest.doRun();
		Arrays.sort(SortingAlgorithm.getNumbers());

		assertArrayEquals(SortingAlgorithm.getNumbers(),shellSortTest.getNumbers());
		assertEquals(shellSortTest.getComparisonCounter(), 41);
		assertEquals(shellSortTest.getMoveCounter(), 7);
		assertEquals(shellSortTest.getSwapCounter(), 0);
	}
	
	@Test
	public void tenAlmostSortedElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[] {1, 20, 56, 76, 55, 127, 160, 132, 191, 243});
		ShellSortThread shellSortTest = new ShellSortThread();
		shellSortTest.doRun();
		Arrays.sort(SortingAlgorithm.getNumbers());

		assertArrayEquals(SortingAlgorithm.getNumbers(),shellSortTest.getNumbers());
		assertEquals(shellSortTest.getComparisonCounter(), 40);
		assertEquals(shellSortTest.getMoveCounter(), 3);
		assertEquals(shellSortTest.getSwapCounter(), 0);
	}

	@Test
	public void thousandRandomElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[100]);
		for (int i = 0; i < 100; i++)
	    {
			SortingAlgorithm.getNumbers()[i] = (int) (Math.random()*Integer.MAX_VALUE);
	    }
		ShellSortThread shellSortTest = new ShellSortThread();
		shellSortTest.doRun();
		Arrays.sort(SortingAlgorithm.getNumbers());
		assertArrayEquals(SortingAlgorithm.getNumbers(),shellSortTest.getNumbers());
	}

	@Test
	public void millionRandomElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[1000000]);
		for (int i = 0; i < 1000000; i++)
	    {
			SortingAlgorithm.getNumbers()[i] = (int) (Math.random()*Integer.MAX_VALUE);
	    }
		ShellSortThread shellSortTest = new ShellSortThread();
		shellSortTest.doRun();
		Arrays.sort(SortingAlgorithm.getNumbers());
		assertArrayEquals(SortingAlgorithm.getNumbers(),shellSortTest.getNumbers());
	}
}
