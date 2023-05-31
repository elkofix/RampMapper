package com.example.demo1.datastructures;

import java.util.Comparator;

public class EdgeComparator<K> implements Comparator<Edge<K>> {

    @Override
    public int compare(Edge<K> o1, Edge<K> o2) {
        return o1.weight-o2.weight;
    }

}