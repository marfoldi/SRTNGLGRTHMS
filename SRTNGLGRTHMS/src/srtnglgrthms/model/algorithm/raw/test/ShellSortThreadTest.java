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
