package com.example.demo1.datastructures;

public class Edge<K> {

    public String id;


    public Vertex<K> getVertex() {
        return vertex;
    }

    public void setVertex(Vertex<K> vertex) {
        this.vertex = vertex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    Vertex<K> vertex;
    int weight;

    public Edge(Vertex<K> vertex, int weight, String id) {
        this.vertex = vertex;
        this.id = id;
        this.weight = weight;
    }
}
