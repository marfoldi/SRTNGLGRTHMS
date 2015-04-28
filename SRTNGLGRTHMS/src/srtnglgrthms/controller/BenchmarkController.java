package srtnglgrthms.controller;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import srtnglgrthms.model.BenchmarkData;
import srtnglgrthms.model.algorithm.raw.BubbleSortThread;
import srtnglgrthms.model.algorithm.raw.HeapSortThread;
import srtnglgrthms.model.algorithm.raw.InsertionSortThread;
import srtnglgrthms.model.algorithm.raw.QuickSortThread;
import srtnglgrthms.model.algorithm.raw.ShellSortThread;
import srtnglgrthms.model.algorithm.raw.SortingSortThread;
import srtnglgrthms.model.algorithm.raw.TournamentSortThread;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;

/**
 *
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public class BenchmarkController implements SortingThreadListener {
	private static BenchmarkTableController tableController;
	private static SortingSortThread[] sortingThreads;
	protected static List<BenchmarkData> benchmarkDataList = new ArrayList<BenchmarkData>();

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		sortingThreads = new SortingSortThread[] { new QuickSortThread(),
				new InsertionSortThread(), new ShellSortThread(), new BubbleSortThread(),
				new HeapSortThread(), new TournamentSortThread() };
		runSortingThreads();
	}

	private void runSortingThreads() {
		for (SortingSortThread thread : sortingThreads) {
			thread.setListener(this);
			thread.start();
		}

	}

	public static void setTableController(
			BenchmarkTableController tableController) {
		BenchmarkController.tableController = tableController;
	}

	public static void addBenchmarkData(BenchmarkData benchmarkData) {
		benchmarkDataList.add(benchmarkData);
	}

	@Override
	public void notifyOfThreadComplete(Thread thread) {
		try {
		tableController.getTableView().setItems(FXCollections.observableArrayList(benchmarkDataList));
		} catch (ConcurrentModificationException cme) {}
	}

	public static SortingSortThread[] getSortingThreads() {
		return sortingThreads;
	}

	public static List<BenchmarkData> getBenchmarkDataList() {
		return benchmarkDataList;
	}
}
