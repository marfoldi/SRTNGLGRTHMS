package srtnglgrthms.controller;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import srtnglgrthms.model.algorithm.raw.BenchmarkData;
import srtnglgrthms.model.algorithm.raw.BubbleSortThread;
import srtnglgrthms.model.algorithm.raw.HeapSortThread;
import srtnglgrthms.model.algorithm.raw.InsertionSortThread;
import srtnglgrthms.model.algorithm.raw.QuickSortThread;
import srtnglgrthms.model.algorithm.raw.ShellSortThread;
import srtnglgrthms.model.algorithm.raw.SortingThread;
import srtnglgrthms.model.algorithm.raw.TournamentSortThread;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;

/**
 *
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public class BenchmarkController implements SortingThreadListener {
	private static BenchmarkTableController tableController;
	private static SortingThread[] sortingThreads;
	protected static List<BenchmarkData> benchmarkDataList;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		benchmarkDataList = new ArrayList<BenchmarkData>();
		sortingThreads = new SortingThread[] { new QuickSortThread(),
				new InsertionSortThread(), new ShellSortThread(), new BubbleSortThread(),
				new HeapSortThread(), new TournamentSortThread() };
		runSortingThreads();
	}

	private void runSortingThreads() {
		for (SortingThread thread : sortingThreads) {
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
		Thread.sleep(500);
		tableController.getTableView().setItems(FXCollections.observableArrayList(benchmarkDataList));
		} catch (ConcurrentModificationException | InterruptedException | IllegalStateException cmiie) {}
	}

	public static SortingThread[] getSortingThreads() {
		return sortingThreads;
	}

	public static List<BenchmarkData> getBenchmarkDataList() {
		return benchmarkDataList;
	}

	public static void setBenchmarkDataList(List<BenchmarkData> benchmarkDataList) {
		BenchmarkController.benchmarkDataList = benchmarkDataList;
	}
}
