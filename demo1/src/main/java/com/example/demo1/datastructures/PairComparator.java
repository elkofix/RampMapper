package com.example.demo1.datastructures;

import java.util.Comparator;

public class PairComparator<K>  implements Comparator<Pair<Vertex<K>, Edge<K>>> {

    @Override
    public int compare(Pair<Vertex<K>, Edge<K>> o1, Pair<Vertex<K>, Edge<K>> o2) {
        return o1.getValue2().getWeight()-o2.getValue2().getWeight();
    }
}
