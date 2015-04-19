package srtnglgrthms.controller;

import srtnglgrthms.model.algorithm.BubbleSort;
import srtnglgrthms.model.algorithm.HeapSort;
import srtnglgrthms.model.algorithm.InsertionSort;
import srtnglgrthms.model.algorithm.QuickSort;
import srtnglgrthms.model.algorithm.ShellSort;
import javafx.fxml.FXML;

public class BenchmarkController {
	private static int doneCounter = 0;
	
	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		runSortingThreads();
	}
	
	private void runSortingThreads() {
		new Thread(BubbleSort.sort).start();
		new Thread(InsertionSort.sort).start();
		new Thread(ShellSort.sort).start();
		new Thread(QuickSort.sort).start();
		new Thread(HeapSort.sort).start();
	}
	
	public static int getDoneCounter() {
		return doneCounter;
	}
	
	public static void incDoneCounter() {
		doneCounter++;
	}
}