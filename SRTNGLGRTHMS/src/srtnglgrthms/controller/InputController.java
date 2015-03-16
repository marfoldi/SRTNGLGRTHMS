package srtnglgrthms.controller;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import srtnglgrthms.MainApplication;
import srtnglgrthms.model.Algorithm;

public class InputController {
	@FXML
	private TextField sizeField;
	@FXML
	private Button randomBtn;
	@FXML
	private Button okBtn;
	@FXML
	private GridPane numbersPane;
	
	int size;
	
	@FXML
	public void createFields() {
		 size = Integer.parseInt(sizeField.getText());
		 for (int i = 0; i < size; ++i) {
	            TextField tf = new TextField();
	            tf.setMaxWidth(50.0);
	            numbersPane.add(tf, i, 0);
	     }
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
				Scene scene = new Scene(baseLayout, ScreenSize.getWidth()/2, ScreenSize.getHeight()/2);
				Stage stage = (Stage) numbersPane.getScene().getWindow();
				stage.setScene(scene);
				stage.show();
				//Line line = new Line(10, 20, 30, 40);
				//baseLayout.getChildren().add(line);
				
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
        Algorithm.setNumbers(numbers);
	}
}
