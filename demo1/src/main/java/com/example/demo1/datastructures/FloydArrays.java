package com.example.demo1.datastructures;

public class FloydArrays<K, V> {

    public int[][] getDist() {
        return dist;
    }

    public MatrizGenerica<K, K> getPrev() {
        return prev;
    }

    int[][] dist;
    MatrizGenerica<K, K> prev;


    public FloydArrays(int[][] dist, MatrizGenerica<K, K> prev) {
        this.dist = dist;
        this.prev = prev;
    }


}
