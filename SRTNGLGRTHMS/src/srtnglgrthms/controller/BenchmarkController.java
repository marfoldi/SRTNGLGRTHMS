package srtnglgrthms.controller;

import srtnglgrthms.model.algorithm.BubbleSort;
import srtnglgrthms.model.algorithm.HeapSort;
import srtnglgrthms.model.algorithm.InsertionSort;
import srtnglgrthms.model.algorithm.QuickSort;
import srtnglgrthms.model.algorithm.ShellSort;
import srtnglgrthms.model.algorithm.SortingAlgorithm;
import srtnglgrthms.model.algorithm.TournamentSort;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BenchmarkController {
    private static BenchmarkTableController tableController;
    private static Thread[] sortingThreads;
    private static Alert alert;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        sortingThreads = new Thread[] { new Thread(BubbleSort.sort), new Thread(InsertionSort.sort),
                new Thread(ShellSort.sort), new Thread(QuickSort.sort), new Thread(HeapSort.sort),
                new Thread(TournamentSort.sort) };
        setAlertBox();
        runSortingThreads();
    }

    private static void setAlertBox() {
        alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Betöltés...");
    }

    private static void runSortingThreads() {
    	alert.show();
        for (Thread thread : sortingThreads) {
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        alert.hide();
        tableController.getTableView().setItems(SortingAlgorithm.getBenchmarkData());
    }

    public static void setTableController(BenchmarkTableController tableController) {
        BenchmarkController.tableController = tableController;
    }

    public static boolean isThreadsDone() {
        for (Thread thread : sortingThreads) {
            if (thread.isAlive())
                return false;
        }
        return true;
    }
}
