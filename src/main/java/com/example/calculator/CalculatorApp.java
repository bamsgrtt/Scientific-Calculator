package com.example.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CalculatorApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/calculator/calculator.fxml"));
        Parent root = loader.load(); // muat isi dari FXML
        primaryStage.setTitle("Scientific Calculator");


        primaryStage.setScene(new Scene(root, 400, 450));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
