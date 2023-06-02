package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader =  loadWindow("start-view.fxml");
    }

    public static FXMLLoader loadWindow(String FXML) {
        FXMLLoader fxmlLoader = null;
        try {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(FXML));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
            Stage stage = new Stage();
            stage.setTitle("RampMapper");
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
            return fxmlLoader;
        } catch (IOException e) {
            e.getMessage();
            e.printStackTrace();
        }
        return fxmlLoader;
    }

    public static void main(String[] args) {
        launch();
    }
}