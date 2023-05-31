package com.example.demo1.model;

import javafx.geometry.Point2D;

import java.util.ArrayList;

public class Connection {
    private Entrance oval1;
    private Entrance oval2;
    private double startX;
    private double startY;
    private double endX;
    private double endY;

    public ArrayList<Point2D> getPath() {
        return path;
    }

    public void setPath(ArrayList<Point2D> path) {
        this.path = path;
    }

    ArrayList<Point2D> path;

    public Connection(Entrance oval1, Entrance oval2, ArrayList<Point2D> path) {
        this.oval1 = oval1;
        this.oval2 = oval2;
        this.path = path;
    }

    public Entrance getOval1() {
        return oval1;
    }

    public Entrance getOval2() {
        return oval2;
    }

    public double getStartX() {
        return startX;
    }

    public double getStartY() {
        return startY;
    }

    public double getEndX() {
        return endX;
    }

    public double getEndY() {
        return endY;
    }
}
