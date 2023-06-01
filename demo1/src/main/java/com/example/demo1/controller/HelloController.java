package com.example.demo1.controller;

import com.example.demo1.datastructures.Graph;
import com.example.demo1.model.*;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import com.google.gson.Gson;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class HelloController implements Initializable {
    @FXML
    public Pane root;
    public Button connBtn;
    public Button minBtn;

    String currentFloor;
    public Label state;
    String graphc = "";
    public boolean add;
    public TextField idLb;
    public TextField weighLb;

    private ArrayList<Connection> connections = new ArrayList<>();
    public Button reset;
    public Label start;
    public Label ends;
    Entrance init = null;
    Entrance end = null;

    ArrayList<Point> path= new ArrayList<>();
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

    Gson gson = new GsonBuilder()
            .registerTypeAdapter(Connection.class, new ConnectionTypeAdapter())
            .registerTypeAdapter(Entrance.class, new EntranceTypeAdapter())
            .create();
    @FXML
    private Canvas canvas;
    private GraphicsContext gc;
    private Graph<Entrance> graph;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        graph = new Graph<>(false);
        try {
            importPlaces();
            importGraph();
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
        currentFloor = "1";
        drawConnection();
        rd1.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                currentFloor = rd1.getText();
                drawFloor(rd1.getText());
                paintSelected();
            }
        });
        rd2.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                currentFloor = rd2.getText();
                drawFloor(rd2.getText());
                paintSelected();
            }
        });
        rd3.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                currentFloor = rd3.getText();
                drawFloor(rd3.getText());
                paintSelected();
            }
        });
        rd4.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                currentFloor = rd4.getText();
                drawFloor(rd4.getText());
                paintSelected();
            }
        });
        rd5.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                currentFloor = rd5.getText();
                drawFloor(rd5.getText());
                paintSelected();
            }
        });

        firstSelected = false;
        canvas.setOnMouseClicked(event -> {

            double clickX = event.getX();
            double clickY = event.getY();
            if(add==true){
                path.add(new Point(clickX, clickY));
            }
            // Verifica si las coordenadas del clic están dentro de alguno de los óvalos
            for (Entrance oval : entrances) {
                if (clickX >= oval.getPosX() && clickX <= oval.getPosX() + 10 &&
                        clickY >= oval.getPosY() && clickY <= oval.getPosY() + 10 && oval.getFloor()==Integer.parseInt(currentFloor)) {
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
                            if(!end.equals(oval) && currentFloor.equals(end.getFloor()+"")){
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

    public void paintSelected(){
        gc.setFill(Color.GREEN);
        if(init!=null && currentFloor.equals(init.getFloor()+"")){
            gc.fillOval(init.getPosX(), init.getPosY(), 10, 10);
        }
        if(end!=null && currentFloor.equals(end.getFloor()+"")){
            gc.fillOval(end.getPosX(), end.getPosY(), 10, 10);
        }
    }

    public Entrance searchEntrance(String id){
        Entrance result = null;
        for (Entrance e: entrances) {
            if(e.getName().equals(id)){
                result = e;
                break;
            }
        }
        return result;
    }

    public void importGraph() throws IOException {
        File file = new File(System.getProperty("user.dir")+"/src/main/resources/data/graph.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNext()){
            String line = sc.nextLine();
            graphc += line+"\n";
            String[] data = line.split(" ");
            graph.addEdge(searchEntrance(data[0]), searchEntrance(data[1]), Integer.parseInt(data[3]), data[2]);
        }
    }

    public void saveData(){

        File dataDirectory = new File(System.getProperty("user.dir")+"/src/main/resources/data");
        File result = new File(System.getProperty("user.dir")+"/src/main/resources/data/connections.json");

        if(!dataDirectory.exists()){
            dataDirectory.mkdirs();
        }

        String json =  gson.toJson(connections);
        try{
            FileOutputStream fos = new FileOutputStream(result);
            fos.write(json.getBytes(StandardCharsets.UTF_8));
            fos.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void saveData2(){

        File dataDirectory = new File(System.getProperty("user.dir")+"/src/main/resources/data");
        File result = new File(System.getProperty("user.dir")+"/src/main/resources/data/graph.txt");
        if(!dataDirectory.exists()){
            dataDirectory.mkdirs();
        }

        String json = graphc;
        try{
            FileOutputStream fos = new FileOutputStream(result);
            fos.write(json.getBytes(StandardCharsets.UTF_8));
            fos.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void importPlaces() throws IOException {
        File file = new File(System.getProperty("user.dir")+"/src/main/resources/data/places.txt");
        Scanner sc = new Scanner(file);
        entrances = new ArrayList<>();
        while (sc.hasNext()){
            String line = sc.nextLine();
            String[] data = line.split(" ");
            Entrance entrance = new Entrance(data[0],Double.parseDouble(data[1]), Double.parseDouble(data[2]), data[3]);
            graph.addVertex(entrance);
            entrances.add(entrance);
        }
        File result = new File(System.getProperty("user.dir")+"/src/main/resources/data/connections.json");
        try {
            FileInputStream fis = new FileInputStream(result);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            StringBuilder json = new StringBuilder();
            while ( (line = reader.readLine()) != null){
                json.append(line);
            }
            Connection[] connectionsArr = gson.fromJson(json.toString(), Connection[].class);
            if(connectionsArr!=null){
                Collections.addAll(connections, connectionsArr);
            }

        } catch (FileNotFoundException e){
            System.out.println("No hay datos para cargar");
        } catch (IOException e){
            e.printStackTrace();
        }
     }

    public void drawFloor(String floor){

        Image image = new Image(System.getProperty("user.dir")+"/src/main/resources/img/floor"+floor+".png");
        gc.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight());
        drawPoints(Integer.parseInt(floor));
    }

    public void drawPoints(int floor){
        gc.setFill(Color.RED);
        for (Entrance e: entrances) {
            if(e.getFloor()==floor){
                gc.fillOval(e.getPosX(), e.getPosY(), 10, 10);
            }

        }
    }


    public void onReset(ActionEvent actionEvent) {
        Connection con = new Connection(init, end,path.toArray(Point[]::new), idLb.getText());
        graphc += init.getName()+" "+end.getName()+" "+idLb.getText()+" "+weighLb.getText()+"\n";
        connections.add(con);
        path.clear();
        idLb.clear();
        weighLb.clear();
        saveData();
        saveData2();
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

    private void drawConnection() {
        if(!connections.isEmpty()) {
            Connection connection = connections.get(connections.size()-1);
            ArrayList<Point> con = connection.getPath();
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

    public void onAddConnection(ActionEvent actionEvent) {
        add = !add;
        if(add==true){
          state.setText("On");
          state.setStyle("-fx-text-fill: GREEN");
        }else {
            if(init!=null && end!=null){
                idLb.setText(init.getName()+"to"+end.getName());
            }
            state.setText("Off");
            state.setStyle("-fx-text-fill: RED");
        }
    }

    public void onSearchMinPath(ActionEvent actionEvent) {
    }
}