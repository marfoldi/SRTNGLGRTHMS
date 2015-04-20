package srtnglgrthms.controller;

import srtnglgrthms.model.algorithm.BubbleSort;
import srtnglgrthms.model.algorithm.HeapSort;
import srtnglgrthms.model.algorithm.InsertionSort;
import srtnglgrthms.model.algorithm.QuickSort;
import srtnglgrthms.model.algorithm.ShellSort;
import srtnglgrthms.model.algorithm.SortingAlgorithm;
import srtnglgrthms.model.algorithm.TournamentSort;
import javafx.fxml.FXML;

public class BenchmarkController {
	private static BenchmarkTableController tableController;
	private static Thread[] sortingThreads;
	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		sortingThreads = new Thread[] {new Thread(BubbleSort.sort), new Thread(InsertionSort.sort), new Thread(ShellSort.sort),
				new Thread(QuickSort.sort), new Thread(HeapSort.sort), new Thread(TournamentSort.sort)};
		runSortingThreads();
	}

	private void runSortingThreads() {
		for(Thread thread : sortingThreads) {
			thread.start();
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		tableController.getTableView().setItems(SortingAlgorithm.getBenchmarkData());
		
		
		/*Thread bubbleThread = new Thread(BubbleSort.sort);
		Thread insertionThread = new Thread(InsertionSort.sort);
		Thread shellThread = new Thread(ShellSort.sort);
		Thread quickThread = new Thread(QuickSort.sort);
		Thread heapThread = new Thread(HeapSort.sort);
		Thread tournamentThread = new Thread(TournamentSort.sort);*/
		
		/*new Thread(InsertionSort.sort).start();
		new Thread(ShellSort.sort).start();
		new Thread(QuickSort.sort).start();
		new Thread(HeapSort.sort).start();
		new Thread(TournamentSort.sort).start();*/	
	}
	
	public static void setTableController(BenchmarkTableController tableController) {
		BenchmarkController.tableController = tableController;
	}
}