package srtnglgrthms.controller;

import srtnglgrthms.model.algorithm.BubbleSort;
import srtnglgrthms.model.algorithm.InsertionSort;
import srtnglgrthms.model.algorithm.ShellSort;
import javafx.fxml.FXML;

public class BenchmarkController {
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
	}
}