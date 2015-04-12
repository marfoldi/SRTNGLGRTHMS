package srtnglgrthms.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

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
			public void changed(ObservableValue ov, String oldValue,
					String newValue) {
				type = newValue;
				okBtn.setDisable(false);
			}
		});
	}

	private void generateNumbers() {
		int[] numbers = new int[Integer.parseInt(sizeField.getText())];
		switch (type) {
		case "Véletlen generált": {
			for(int i=0; i<numbers.length; ++i) {
				numbers[i] = (int)(Math.random()*100);
			}
			break;
		}
		case "Majdnem rendezett": {
			/*for(int i=0; i<numbers.length; ++i) {
				numbers[i] = (int)(Math.random()*100);
			}
			Integer[] numbersList = Arrays.stream(numbers).boxed().toArray(Integer[]::new);
			Arrays.sort(numbersList, Collections.reverseOrder());*/
			break;
		}
		case "Fordított": {
			break;
		}
		case "Néhány egyedi": {
			break;
		}
		}
		SortingAlgorithm.setNumbers(numbers);
	}

	@FXML
	public void okBtnHandler() {
		if (sizeField.getText().matches("\\d+")) {
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
	}
}
