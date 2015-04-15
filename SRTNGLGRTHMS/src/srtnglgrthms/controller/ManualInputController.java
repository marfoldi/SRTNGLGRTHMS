package srtnglgrthms.controller;

import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import srtnglgrthms.MainApplication;
import srtnglgrthms.model.algorithm.SortingAlgorithm;

public class ManualInputController {
	@FXML
	private TextField sizeField;
	@FXML
	private Button randomBtn;
	@FXML
	private Button okBtn;
	@FXML
	private ScrollPane scrollPane;
	@FXML
	private GridPane numbersPane;
	
	int size;
	
	@FXML
	public void initialize() {
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		setSizeLimit();
		okBtn.setDisable(true);
		randomBtn.setDisable(true);
	}
	
	@FXML
	public void createFields() {
		try {
			numbersPane.getChildren().clear();
			size = Integer.parseInt(sizeField.getText());
			for (int i = 0; i < size; ++i) {
				TextField tf = new TextField();
				tf.setMaxWidth(50.0);
				numbersPane.add(tf, i, 0);
			}
			okBtn.setDisable(false);
			randomBtn.setDisable(false);
		} catch (NumberFormatException nfe) {
			okBtn.setDisable(true);
			randomBtn.setDisable(true);
		}
	}
	
	private void setSizeLimit() {
		sizeField.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            try {
	        	if (Integer.parseInt(sizeField.getText()) > 100) {
	            	sizeField.setText("100");
	            }
	        	if(Integer.parseInt(sizeField.getText()) == 0 || numbersPane.getChildren().isEmpty()) {
	        		okBtn.setDisable(true);
	    			randomBtn.setDisable(true);
	    			return;
	        	}
	        	else {
	        		okBtn.setDisable(false);
	    			randomBtn.setDisable(false);
	        	}
	            } catch (NumberFormatException nfe) {}
	        }
	    });
	}
	
	@FXML
	public void randomBtnHandler() {
        ObservableList<Node> nodes = numbersPane.getChildren();
        for(Node node : nodes) {
        	((TextField)node).setText(Integer.toString((int)(Math.random()*100)));
        }
	}
	
	@FXML
	public void okBtnHandler() {
			saveNumbers();
			try {
				BorderPane baseLayout;
				FXMLLoader loader = new FXMLLoader();
				// Load input layout from fxml file.
				loader.setLocation(MainApplication.class.getResource("view/BaseLayout.fxml"));
				baseLayout = (BorderPane) loader.load();
				// Show the scene containing the input layout.
				Scene scene = new Scene(baseLayout);
				Stage stage = (Stage) numbersPane.getScene().getWindow();
				stage.setScene(scene);
				stage.show();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void saveNumbers() {
		int[] numbers = new int[size];
        ObservableList<Node> nodes = numbersPane.getChildren();
        for(int i=0; i<size; ++i) {
        	numbers[i] = Integer.parseInt(((TextField)nodes.get(i)).getText());
        }
        SortingAlgorithm.setNumbers(numbers);
	}
}
