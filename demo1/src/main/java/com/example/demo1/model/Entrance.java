package com.example.demo1.model;

public class Entrance {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;

    int floor;

    public Double getPosX() {
        return posX;
    }

    public void setPosX(Double posX) {
        this.posX = posX;
    }

    public Double getPosY() {
        return posY;
    }

    public void setPosY(Double posY) {
        this.posY = posY;
    }

    Double posX;
    Double posY;

    public Entrance(String name, Double posX, Double posY, String floor) {
        this.name = name;
        this.floor = Integer.parseInt(floor);
        this.posX = posX;
        this.posY = posY;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }
}
