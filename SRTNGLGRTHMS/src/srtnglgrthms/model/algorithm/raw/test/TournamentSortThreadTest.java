package srtnglgrthms.model.algorithm.raw.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import srtnglgrthms.controller.BenchmarkController;
import srtnglgrthms.model.algorithm.SortingAlgorithm;
import srtnglgrthms.model.algorithm.raw.BenchmarkData;
import srtnglgrthms.model.algorithm.raw.TournamentSortThread;
import static org.junit.Assert.*;

public class TournamentSortThreadTest {

	@Test
	public void zeroElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[0]);
		TournamentSortThread tournamentSortTest = new TournamentSortThread();
		tournamentSortTest.doRun();

		assertArrayEquals(SortingAlgorithm.getNumbers(),tournamentSortTest.getNumbers());
		assertEquals(tournamentSortTest.getComparisonCounter(), 0);
		assertEquals(tournamentSortTest.getMoveCounter(), 0);
		assertEquals(tournamentSortTest.getSwapCounter(), 0);
	}

	@Test
	public void oneElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[] { 42 });
		TournamentSortThread tournamentSortTest = new TournamentSortThread();
		tournamentSortTest.doRun();
		Arrays.sort(SortingAlgorithm.getNumbers());

		assertArrayEquals(SortingAlgorithm.getNumbers(),tournamentSortTest.getNumbers());
		assertEquals(tournamentSortTest.getComparisonCounter(), 0);
		assertEquals(tournamentSortTest.getMoveCounter(), 0);
		assertEquals(tournamentSortTest.getSwapCounter(), 0);
	}
	
	@Test
	public void fiveConcretElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[] { 42, 20, 7, 18, 100});
		TournamentSortThread tournamentSortTest = new TournamentSortThread();
		tournamentSortTest.doRun();

		List<Integer> numberList = new ArrayList<Integer>();
	    for (int i = 0; i < 5; ++i)
	    {
	    	numberList.add(tournamentSortTest.getFinalNumbers()[i]);
	    }
		Set<Integer> numberSet = new HashSet<>(numberList);
		assertEquals(numberSet.size(), 2);
		assertEquals(tournamentSortTest.getComparisonCounter(), 31);
		assertEquals(tournamentSortTest.getMoveCounter(), 19);
		assertEquals(tournamentSortTest.getSwapCounter(), 0);
	}
	
	@Test
	public void sixteenConcretElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[] {307, 42, 200, 307, 65, 2, 89, 120, 189, 420, 55, 12, 7, 99, 510, 120});
		TournamentSortThread tournamentSortTest = new TournamentSortThread();
		tournamentSortTest.doRun();

		List<Integer> numberList = new ArrayList<Integer>();
	    for (int i = 0; i < 16; ++i)
	    {
	    	numberList.add(tournamentSortTest.getFinalNumbers()[i]);
	    }
		Set<Integer> numberSet = new HashSet<>(numberList);
		assertEquals(numberSet.size(), 2);
		assertEquals(tournamentSortTest.getComparisonCounter(), 135);
		assertEquals(tournamentSortTest.getMoveCounter(), 75);
		assertEquals(tournamentSortTest.getSwapCounter(), 0);
	}
	
	@Test
	public void tenSortedElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[] {2, 42, 65, 89, 120, 189, 200, 291, 307, 420});
		TournamentSortThread tournamentSortTest = new TournamentSortThread();
		tournamentSortTest.doRun();

		List<Integer> numberList = new ArrayList<Integer>();
	    for (int i = 0; i < 10; ++i)
	    {
	    	numberList.add(tournamentSortTest.getFinalNumbers()[i]);
	    }
		Set<Integer> numberSet = new HashSet<>(numberList);
		assertEquals(numberSet.size(), 2);
		assertEquals(tournamentSortTest.getComparisonCounter(), 87);
		assertEquals(tournamentSortTest.getMoveCounter(), 51);
		assertEquals(tournamentSortTest.getSwapCounter(), 0);
	}
	
	@Test
	public void tenReverseSortedElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[] {420, 307, 291, 200, 189, 120, 89, 65, 42, 2});
		TournamentSortThread tournamentSortTest = new TournamentSortThread();
		tournamentSortTest.doRun();

		List<Integer> numberList = new ArrayList<Integer>();
	    for (int i = 0; i < 10; ++i)
	    {
	    	numberList.add(tournamentSortTest.getFinalNumbers()[i]);
	    }
		Set<Integer> numberSet = new HashSet<>(numberList);
		assertEquals(numberSet.size(), 2);
		assertEquals(tournamentSortTest.getComparisonCounter(), 87);
		assertEquals(tournamentSortTest.getMoveCounter(), 51);
		assertEquals(tournamentSortTest.getSwapCounter(), 0);
	}
	
	@Test
	public void tenAlmostSortedElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[] {1, 20, 56, 76, 55, 127, 160, 132, 191, 243});
		TournamentSortThread tournamentSortTest = new TournamentSortThread();
		tournamentSortTest.doRun();

		List<Integer> numberList = new ArrayList<Integer>();
	    for (int i = 0; i < 10; ++i)
	    {
	    	numberList.add(tournamentSortTest.getFinalNumbers()[i]);
	    }
		Set<Integer> numberSet = new HashSet<>(numberList);
		assertEquals(numberSet.size(), 2);
		assertEquals(tournamentSortTest.getComparisonCounter(), 87);
		assertEquals(tournamentSortTest.getMoveCounter(), 51);
		assertEquals(tournamentSortTest.getSwapCounter(), 0);
	}
	
	@Test
	public void thousandRandomElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[100]);
		for (int i = 0; i < 100; i++)
	    {
			SortingAlgorithm.getNumbers()[i] = (int) (Math.random()*Integer.MAX_VALUE);
	    }
		TournamentSortThread tournamentSortTest = new TournamentSortThread();
		tournamentSortTest.doRun();
	    List<Integer> numberList = new ArrayList<Integer>();
	    for (int i = 0; i < 100; ++i)
	    {
	    	numberList.add(tournamentSortTest.getFinalNumbers()[i]);
	    }
		Set<Integer> numberSet = new HashSet<>(numberList);
		assertEquals(numberSet.size(), 2);
		assertEquals(tournamentSortTest.getComparisonCounter(), 1513);
		assertEquals(tournamentSortTest.getMoveCounter(), 820);
		assertEquals(tournamentSortTest.getSwapCounter(), 0);
	}
	
	@Test
	public void millionRandomElement() {
		BenchmarkController.setBenchmarkDataList(new ArrayList<BenchmarkData>());
		SortingAlgorithm.setNumbers(new int[1000000]);
		for (int i = 0; i < 1000000; i++)
	    {
			SortingAlgorithm.getNumbers()[i] = (int) (Math.random()*Integer.MAX_VALUE);
	    }
		TournamentSortThread tournamentSortTest = new TournamentSortThread();
		tournamentSortTest.doRun();
	    List<Integer> numberList = new ArrayList<Integer>();
	    for (int i = 0; i < 1000000; ++i)
	    {
	    	numberList.add(tournamentSortTest.getFinalNumbers()[i]);
	    }
		Set<Integer> numberSet = new HashSet<>(numberList);
		assertEquals(numberSet.size(), 2);
		assertEquals(tournamentSortTest.getComparisonCounter(), 41048535);
		assertEquals(tournamentSortTest.getMoveCounter(), 21048555);
		assertEquals(tournamentSortTest.getSwapCounter(), 0);
	}
}
