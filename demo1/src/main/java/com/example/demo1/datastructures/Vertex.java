package com.example.demo1.datastructures;

import src.model.Color;

import java.util.ArrayList;

public class Vertex<K> {
    K value;

    public int priority;

    public Vertex<K> getParent() {
        return parent;
    }

    public void setParent(Vertex<K> parent) {
        this.parent = parent;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    Vertex<K> parent;

    int distance;

    public K getValue() {
        return value;
    }

    public void setValue(K value) {
        this.value = value;
    }

    public void selfDeleteVertex(){
        for (Edge<K> a: adjacentList) {
            Vertex<K> vrtx = a.getVertex();
            //vrtx.getAdjacentList().remove();
        }
    }

    public boolean existVertex(Vertex<K> vertex){
        for (Edge<K> e: adjacentList) {
            if(e.getVertex().equals(vertex)){
                return true;
            }
        }
        return false;
    }

    public boolean addEdge(Edge<K> vertex){
        return adjacentList.add(vertex);
    }
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ArrayList<Edge<K>> getAdjacentList() {
        return adjacentList;
    }

    public int getWeight(Vertex<K> vertex){
        if(vertex.getValue().equals(value)){
            return 0;
        }
        for (Edge<K> edge: adjacentList) {
            if(edge.getVertex().equals(vertex)){
                return edge.weight;
            }
        }
        return Integer.MAX_VALUE;
    }

    public Vertex<K> getEdge(Vertex<K> vertex){
        if(vertex.getValue().equals(value)){
            return this;
        }
        for (Edge<K> edge: adjacentList) {
            if(edge.getVertex().equals(vertex)){
                return edge.getVertex();
            }
        }
        return null;
    }


    Color color;
    ArrayList<Edge<K>> adjacentList;

    public Vertex(K value){
        color = Color.WHITE;
        parent = null;
        distance = Integer.MAX_VALUE;
        this.value = value;
        adjacentList = new ArrayList<>();
    }
}
