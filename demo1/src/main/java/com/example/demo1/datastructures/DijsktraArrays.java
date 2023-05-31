package com.example.demo1.datastructures;

import java.util.ArrayList;
import java.util.HashMap;

public class DijsktraArrays<K> {
    public HashMap<Vertex<K>, Integer> getDist() {
        return dist;
    }

    public void setDist(HashMap<Vertex<K>, Integer> dist) {
        this.dist = dist;
    }

    public HashMap<Vertex<K>, Vertex<K>> getPrev() {
        return prev;
    }

    public void setPrev(HashMap<Vertex<K>, Vertex<K>> prev) {
        this.prev = prev;
    }

    HashMap<Vertex<K>, Integer> dist;

    HashMap<Vertex<K>, Vertex<K>> prev;


    public DijsktraArrays(HashMap<Vertex<K>, Integer> dist, HashMap<Vertex<K>, Vertex<K>> prev) {
        this.dist = dist;
        this.prev = prev;
    }
}
