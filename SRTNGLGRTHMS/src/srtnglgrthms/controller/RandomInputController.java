package srtnglgrthms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import srtnglgrthms.MainApplication;
import srtnglgrthms.model.algorithm.SortingAlgorithm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * 
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public class RandomInputController {
	@FXML
	private TextField sizeField;
	@FXML
	private ComboBox<String> typeBox;
	@FXML
	private Button okBtn;
	private static String type;

	@FXML
	public void initialize() {
		okBtn.setDisable(true);
		initBox();
	}

	private void initBox() {
		typeBox.getItems().addAll("Véletlen generált", "Majdnem rendezett",
				"Fordított", "Néhány egyedi");
		typeBox.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> ov, String oldValue,
					String newValue) {
				type = newValue;
				okBtn.setDisable(false);
			}
		});
	}

	private void generateNumbers() {
		int[] numbers = new int[Integer.parseInt(sizeField.getText())];
		List<Integer> numberList = new ArrayList<>();
		switch (type) {
		case "Véletlen generált": {
			for(int i=0; i<numbers.length; ++i) {
				numbers[i] = (int)(Math.random()*100);
			}
			break;
		}
		case "Majdnem rendezett": {
			for(int i=0; i<numbers.length; ++i) {
				numberList.add((int)(Math.random()*100));
			}
			Collections.sort(numberList);
			for(int i=0; i<numbers.length*0.1; ++i) {
				int firstIndex = (int)(Math.random()*numbers.length);
				int secondIndex = (int)(Math.random()*numbers.length);
				int temp = numberList.get(firstIndex);
				numberList.set(firstIndex, numberList.get(secondIndex));
				numberList.set(secondIndex, temp);
			}
			List<Integer> isSorted = new ArrayList<Integer>(numberList.size());
			Collections.sort(isSorted);
			if(isSorted.equals(numberList)) {
				generateNumbers();
			}
			for(int i=0; i<numbers.length; ++i) {
				numbers[i] = numberList.get(i);
			}
			break;
		}
		case "Fordított": {
			for(int i=0; i<numbers.length; ++i) {
				numberList.add((int)(Math.random()*100));
			}
			Collections.sort(numberList, Collections.reverseOrder());
			for(int i=0; i<numbers.length; ++i) {
				numbers[i] = numberList.get(i);
			}
			break;
		}
		case "Néhány egyedi": {
			if((int) Math.sqrt(numbers.length)<=1) {
				for(int i=0; i<numbers.length; ++i) {
					numbers[i] = (int)(Math.random()*100);
				}
				break;
			}
			int[] uniqueKeys = new int[(int) Math.sqrt(numbers.length)];
			for(int i=0; i<uniqueKeys.length; ++i) {
				uniqueKeys[i] = (int)(Math.random()*100);
			}
			for(int i=0; i<numbers.length; ++i) {
				numbers[i] = uniqueKeys[(int)(Math.random()*uniqueKeys.length)];
			}
			break;
		}
		}
		SortingAlgorithm.setNumbers(numbers);
	}

	@FXML
	public void okBtnHandler() {
		try {
		if (sizeField.getText().matches("\\d+") && Integer.parseInt(sizeField.getText())<Integer.MAX_VALUE) {
			generateNumbers();
			try {
				BorderPane baseLayout;
				FXMLLoader loader = new FXMLLoader();
				// Load input layout from fxml file.
				loader.setLocation(MainApplication.class
						.getResource("view/BaseLayout.fxml"));
				baseLayout = (BorderPane) loader.load();
				// Show the scene containing the input layout.
				Scene scene = new Scene(baseLayout);
				Stage stage = (Stage) okBtn.getScene().getWindow();
				stage.setScene(scene);
				stage.show();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		} catch (NumberFormatException nfe) {}
	}
}
