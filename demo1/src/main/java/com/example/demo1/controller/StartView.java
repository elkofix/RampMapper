package com.example.demo1.controller;

import com.example.demo1.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class StartView implements Initializable {
    public RadioButton rd1;
    public RadioButton rd2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup toggleGroup = new ToggleGroup();
        rd1.setToggleGroup(toggleGroup);
        rd2.setToggleGroup(toggleGroup);
    }

    public void onEnter(ActionEvent actionEvent) {
        if(rd1.selectedProperty().get() || rd2.selectedProperty().get()){
            HelloController con = HelloApplication.loadWindow("hello-view.fxml").getController();
            if(rd1.selectedProperty().get()){
                con.init(1);
            }
            if(rd2.selectedProperty().get()){
                con.init(2);
            }
            Stage stage = (Stage) rd2.getScene().getWindow();
            stage.close();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de selección");
            alert.setHeaderText("Error");
            alert.setContentText("Debes seleccionar un tipo de grafo para continuar");
            alert.showAndWait();
        }
    }
}
