package srtnglgrthms.controller;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import srtnglgrthms.model.BenchmarkData;
import srtnglgrthms.model.algorithm.raw.BubbleThread;
import srtnglgrthms.model.algorithm.raw.HeapThread;
import srtnglgrthms.model.algorithm.raw.InsertionThread;
import srtnglgrthms.model.algorithm.raw.QuickThread;
import srtnglgrthms.model.algorithm.raw.ShellThread;
import srtnglgrthms.model.algorithm.raw.SortingThread;
import srtnglgrthms.model.algorithm.raw.TournamentThread;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;

/**
 *
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public class BenchmarkController implements SortingThreadListener {
	private static BenchmarkTableController tableController;
	private static SortingThread[] sortingThreads;
	protected static List<BenchmarkData> benchmarkDataList = new ArrayList<BenchmarkData>();

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		sortingThreads = new SortingThread[] { new QuickThread(),
				new InsertionThread(), new ShellThread(), new BubbleThread(),
				new HeapThread(), new TournamentThread() };
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
		tableController.getTableView().setItems(FXCollections.observableArrayList(benchmarkDataList));
		} catch (ConcurrentModificationException cme) {}
	}

	public static SortingThread[] getSortingThreads() {
		return sortingThreads;
	}

	public static List<BenchmarkData> getBenchmarkDataList() {
		return benchmarkDataList;
	}
}
