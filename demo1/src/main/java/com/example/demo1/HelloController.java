package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public Pane root;
    public RadioButton rd1;

    public RadioButton rd2;

    public RadioButton rd3;

    public RadioButton rd4;
    public RadioButton rd5;

    @FXML
    private Canvas canvas;

    Image image = new Image(System.getProperty("user.dir")+"/src/main/resources/img/map.png");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup toggleGroup = new ToggleGroup();
        rd1.setToggleGroup(toggleGroup);
        rd2.setToggleGroup(toggleGroup);
        rd3.setToggleGroup(toggleGroup);
        rd4.setToggleGroup(toggleGroup);
        rd5.setToggleGroup(toggleGroup);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight());


    }



}