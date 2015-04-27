package srtnglgrthms.controller;

import java.util.ConcurrentModificationException;

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

	@Override
	public void notifyOfThreadComplete(Thread thread) {
		try {
		tableController.getTableView().setItems(FXCollections.observableArrayList(SortingThread.getBenchmarkData()));
		} catch (ConcurrentModificationException cme) {}
	}

	public static SortingThread[] getSortingThreads() {
		return sortingThreads;
	}
}
