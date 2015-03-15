package srtnglgrthms.controller;

import java.io.IOException;

import srtnglgrthms.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MenuController {
	@FXML
	BorderPane menuPane;

	@FXML
	private void manualBtnHandler(ActionEvent event) {
		try {
			SplitPane inputLayout;
			FXMLLoader loader = new FXMLLoader();
			// Load input layout from fxml file.
			loader.setLocation(MainApplication.class
					.getResource("view/InputLayout.fxml"));
			inputLayout = (SplitPane) loader.load();
			// Show the scene containing the input layout.
			Scene scene = new Scene(inputLayout, ScreenSize.getWidth()/4.5, ScreenSize.getHeight()/7);
			Stage stage = (Stage) menuPane.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void randomBtnHandler(ActionEvent event) {
		try {
			AnchorPane randomLayout;
			FXMLLoader loader = new FXMLLoader();
			// Load input layout from fxml file.
			loader.setLocation(MainApplication.class
					.getResource("view/RandomLayout.fxml"));
			randomLayout = (AnchorPane) loader.load();
			// Show the scene containing the input layout.
			Scene scene = new Scene(randomLayout, ScreenSize.getWidth()/4.5, ScreenSize.getHeight()/7);
			Stage stage = (Stage) menuPane.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
