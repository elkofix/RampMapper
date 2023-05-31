package com.example.demo1.controller;

import com.example.demo1.datastructures.Graph;
import com.example.demo1.model.Connection;
import com.example.demo1.model.Entrance;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class HelloController implements Initializable {
    @FXML
    public Pane root;

    private ArrayList<Connection> connections = new ArrayList<>();
    public Button reset;
    public Label start;
    public Label ends;
    Entrance init = null;
    Entrance end = null;

    @FXML
    public RadioButton rd1;

    private ArrayList<Entrance> entrances;

    @FXML
    public RadioButton rd2;

    @FXML
    public RadioButton rd3;

    @FXML
    public RadioButton rd4;

    @FXML
    public RadioButton rd5;

    private boolean firstSelected;

    @FXML
    private Canvas canvas;
    private GraphicsContext gc;
    private Graph<Entrance> graph;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            importPlaces();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        ToggleGroup toggleGroup = new ToggleGroup();
        gc = canvas.getGraphicsContext2D();
        rd1.setToggleGroup(toggleGroup);
        rd2.setToggleGroup(toggleGroup);
        rd3.setToggleGroup(toggleGroup);
        rd4.setToggleGroup(toggleGroup);
        rd5.setToggleGroup(toggleGroup);
        rd1.setSelected(true);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawFloor("1");
      /*   ArrayList<Point2D> path1 = new ArrayList<>();
        path1.add(new Point2D(100, 75));
        path1.add(new Point2D(150, 125));
        path1.add(new Point2D(200, 175));
        connectOvals(gc, entrances.get(1), entrances.get(10), path1);

        ArrayList<Point2D> path2 = new ArrayList<>();
       path2.add(new Point2D(200, 125));
        path2.add(new Point2D(250, 300));
        path2.add(new Point2D(300, 100));
        path2.add(new Point2D(425, 180));

        connectOvals(gc, entrances.get(2), entrances.get(20), path2);*/
        rd1.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                drawFloor(rd1.getText());
            }
        });
        rd2.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                drawFloor(rd2.getText());
            }
        });
        rd3.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                drawFloor(rd3.getText());
            }
        });
        rd4.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                drawFloor(rd4.getText());
            }
        });
        rd5.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                drawFloor(rd5.getText());
            }
        });

        firstSelected = false;
        canvas.setOnMouseClicked(event -> {
            double clickX = event.getX();
            double clickY = event.getY();
        System.out.println(clickX+" "+clickY);
            // Verifica si las coordenadas del clic están dentro de alguno de los óvalos
            for (Entrance oval : entrances) {
                if (clickX >= oval.getPosX() && clickX <= oval.getPosX() + 10 &&
                        clickY >= oval.getPosY() && clickY <= oval.getPosY() + 10) {
                    // Cambia el color del óvalo
                    gc.setFill(Color.GREEN);
                    gc.fillOval(oval.getPosX(), oval.getPosY(), 10, 10);
                    if(init == null && !firstSelected){
                        init = oval;
                        firstSelected=true;
                        start.setText(oval.getName());
                        continue;

                    }

                    if(!oval.equals(init) && firstSelected){
                        ends.setText(oval.getName());
                        if(end!=null)
                            if(!end.equals(oval)){
                                ends.setText(oval.getName());
                                gc.setFill(Color.RED);
                                gc.fillOval(end.getPosX(), end.getPosY(), 10, 10);
                                gc.setFill(Color.GREEN);
                                end = oval;
                                gc.fillOval(oval.getPosX(), oval.getPosY(), 10, 10);
                            }
                        end = oval;

                        continue;
                    }

                    // Llama al método deseado

                    break;
                }
            }
        });


    }

    public void importPlaces() throws IOException {
        File file = new File(System.getProperty("user.dir")+"/src/main/resources/data/places.txt");
        Scanner sc = new Scanner(file);
        entrances = new ArrayList<>();
        while (sc.hasNext()){
            String line = sc.nextLine();
            String[] data = line.split(" ");
            Entrance entrance = new Entrance(data[0],Double.parseDouble(data[1]), Double.parseDouble(data[2]), data[3]);
            entrances.add(entrance);
        }
     }

    public void drawFloor(String floor){

        Image image = new Image(System.getProperty("user.dir")+"/src/main/resources/img/floor"+floor+".png");
        gc.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight());
        drawPoints();
    }

    public void drawPoints(){
        gc.setFill(Color.RED);
        for (Entrance e: entrances) {
            gc.fillOval(e.getPosX(), e.getPosY(), 10, 10);
        }
    }


    public void onReset(ActionEvent actionEvent) {
        if(init !=null) {
            gc.setFill(Color.RED);
            gc.fillOval(init.getPosX(), init.getPosY(), 10, 10);
            init = null;
        }
        if(end!=null){
            gc.setFill(Color.RED);
            gc.fillOval(end.getPosX(), end.getPosY(), 10, 10);
            end = null;
        }

            ends.setText("Destino");
            start.setText("Inicio");


            firstSelected = false;

    }

    private void connectOvals(GraphicsContext gc, Entrance oval1, Entrance oval2, ArrayList<Point2D> path) {
        connections.add(new Connection(oval1, oval2, path));
        drawConnection(gc, connections.get(connections.size() - 1));
    }

    private void drawConnection(GraphicsContext gc, Connection connection) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.beginPath();
        gc.moveTo(connection.getPath().get(0).getX(), connection.getPath().get(0).getY());
        for (int i = 1; i < connection.getPath().size(); i++) {
            gc.lineTo(connection.getPath().get(i).getX(), connection.getPath().get(i).getY());
        }
        gc.stroke();
        gc.closePath();
    }
}