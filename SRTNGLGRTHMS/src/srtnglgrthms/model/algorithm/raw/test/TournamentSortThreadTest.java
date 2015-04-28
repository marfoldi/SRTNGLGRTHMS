package srtnglgrthms.model.algorithm.raw.test;

import org.junit.Test;
import static org.junit.Assert.*;

import srtnglgrthms.model.algorithm.SortingAlgorithm;
import srtnglgrthms.model.algorithm.raw.TournamentSortThread;

public class TournamentSortThreadTest {

	@Test
	public void zeroElement() {
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
		SortingAlgorithm.setNumbers(new int[] { 42 });
		TournamentSortThread tournamentSortTest = new TournamentSortThread();
		tournamentSortTest.doRun();

		assertArrayEquals(SortingAlgorithm.getNumbers(),tournamentSortTest.getNumbers());
		assertEquals(tournamentSortTest.getComparisonCounter(), 0);
		assertEquals(tournamentSortTest.getMoveCounter(), 0);
		assertEquals(tournamentSortTest.getSwapCounter(), 0);
	}
}
