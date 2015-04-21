package srtnglgrthms.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import srtnglgrthms.MainApplication;
import srtnglgrthms.model.algorithm.SortingAlgorithm;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * 
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public class FileInputController {
	private static Stage stage;
	private static File file;
	private static FileChooser fileChooser;
	private static Scanner scanner;

	public static void openChooser() {
		fileChooser = new FileChooser();
        // Set extension filter
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Szöveges fájl (*.txt)", "*.txt"));
        // Show open file dialog
        file = fileChooser.showOpenDialog(stage);
        if(file != null) {
        	try {
				scanner = new Scanner(file);
	        	loadFile();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}

	private static int getLineNumber() {
		int lineNumber = 0;
		try {
			LineNumberReader lnr = new LineNumberReader(new FileReader(file));
			lnr.skip(Long.MAX_VALUE);
			lineNumber = lnr.getLineNumber()+1; //Add 1 because line index starts at 0
			// Finally, the LineNumberReader object should be closed to prevent resource leak
			lnr.close();
		} catch (IOException ioe) {}
		return lineNumber;
	}

	private static void loadFile() {
		try {
		int[] numbers = new int[getLineNumber()];
		for (int i=0; i<numbers.length; ++i) numbers[i] = scanner.nextInt();
		SortingAlgorithm.setNumbers(numbers);
		showBaseLayout();
		} catch(InputMismatchException ime) {
			Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Hiba");
    		alert.setHeaderText("Hiba történt");
    		alert.setContentText("A megadott fájl nem megfelelõ!\n(Nem csak egész számokat tartalmaz soronként)");

    		alert.showAndWait();
		}
	}

	private static void showBaseLayout() {
		try {
			BorderPane baseLayout;
			FXMLLoader loader = new FXMLLoader();
			// Load input layout from fxml file.
			loader.setLocation(MainApplication.class.getResource("view/BaseLayout.fxml"));
			baseLayout = (BorderPane) loader.load();
			// Show the scene containing the input layout.
			Scene scene = new Scene(baseLayout);
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setStage(Stage stage) {
		FileInputController.stage = stage;
	}
}
