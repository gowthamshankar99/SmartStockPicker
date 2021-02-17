package com.smartpickers.stocks;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainJavaFx extends Application {
	
	Button button;

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Start method is the first method that gets called
		
		primaryStage.setTitle("Welcome to Smart Stock Pickers");
		
		button = new Button();
		button.setText("OK");
	}

}
