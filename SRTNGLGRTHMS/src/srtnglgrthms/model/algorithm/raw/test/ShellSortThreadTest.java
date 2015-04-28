package srtnglgrthms.model.algorithm.raw.test;

import org.junit.Test;

import srtnglgrthms.model.algorithm.SortingAlgorithm;
import srtnglgrthms.model.algorithm.raw.ShellSortThread;
import static org.junit.Assert.*;

import java.util.Arrays;

public class ShellSortThreadTest {

	@Test
	public void zeroElement() {
		SortingAlgorithm.setNumbers(new int[0]);
		ShellSortThread shellTest = new ShellSortThread();
		shellTest.doRun();

		assertArrayEquals(SortingAlgorithm.getNumbers(),shellTest.getNumbers());
		assertEquals(shellTest.getComparisonCounter(), 0);
		assertEquals(shellTest.getMoveCounter(), 0);
		assertEquals(shellTest.getSwapCounter(), 0);
	}

	@Test
	public void oneElement() {
		SortingAlgorithm.setNumbers(new int[] { 42 });
		ShellSortThread shellTest = new ShellSortThread();
		shellTest.doRun();

		assertArrayEquals(SortingAlgorithm.getNumbers(),shellTest.getNumbers());
		assertEquals(shellTest.getComparisonCounter(), 0);
		assertEquals(shellTest.getMoveCounter(), 0);
		assertEquals(shellTest.getSwapCounter(), 0);
	}

	@Test
	public void thousandRandomElement() {
		SortingAlgorithm.setNumbers(new int[100]);
		for (int i = 0; i < 100; i++)
	    {
			SortingAlgorithm.getNumbers()[i] = (int) (Math.random()*Integer.MAX_VALUE);
	    }
		ShellSortThread shellTest = new ShellSortThread();
		shellTest.doRun();
		Arrays.sort(SortingAlgorithm.getNumbers());
		assertArrayEquals(SortingAlgorithm.getNumbers(),shellTest.getNumbers());
	}


}
