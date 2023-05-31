package com.example.demo1.datastructures;

public class Pair<K, T>{
    public K getValue1() {
        return value1;
    }

    public void setValue1(K value1) {
        this.value1 = value1;
    }

    public T getValue2() {
        return value2;
    }

    public void setValue2(T value2) {
        this.value2 = value2;
    }

    K value1;
    T value2;


    public Pair(K value1, T value2) {
        this.value1 = value1;
        this.value2 = value2;
    }
}
