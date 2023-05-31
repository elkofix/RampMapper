package com.example.demo1.model;

import javafx.geometry.Point2D;
import java.util.ArrayList;
import java.util.Collections;

public class Connection {
    public void setOval1(Entrance oval1) {
        this.oval1 = oval1;
    }

    public void setOval2(Entrance oval2) {
        this.oval2 = oval2;
    }

    private Entrance oval1;
    private Entrance oval2;

    public ArrayList<Point> getPath() {
        return path;
    }

    public void setPath(ArrayList<Point> path) {
        this.path = path;
    }

    ArrayList<Point> path;

    public Connection(Entrance oval1, Entrance oval2, Point[] path) {
        this.oval1 = oval1;
        this.oval2 = oval2;
        this.path = new ArrayList<>();
        Collections.addAll(this.path, path);
    }
    public void addPoints(){
        this.path.add(new Point(oval2.getPosX(), oval2.getPosY()));
        Collections.reverse(this.path);
        this.path.add(new Point(oval1.getPosX(), oval1.getPosY()));
    }

    public Entrance getOval1() {
        return oval1;
    }

    public Entrance getOval2() {
        return oval2;
    }

}
