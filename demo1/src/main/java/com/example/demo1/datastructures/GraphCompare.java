package com.example.demo1.datastructures;

import java.util.Comparator;

public class GraphCompare<K> implements Comparator<Vertex<K>> {

    @Override
    public int compare(Vertex<K> o1, Vertex<K> o2) {
        return o1.priority-o2.priority;
    }

}
