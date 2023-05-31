package com.example.demo1.datastructures;

import java.util.HashMap;

public class PrimReturn<K> {

    HashMap<K, Integer> key;
    HashMap<K, K> pred;


    public PrimReturn(HashMap<K, Integer> key, HashMap<K, K> pred) {
        this.key = key;
        this.pred = pred;
    }

    public HashMap<K, Integer> getKey() {
        return key;
    }

    public void setKey(HashMap<K, Integer> key) {
        this.key = key;
    }

    public HashMap<K, K> getPred() {
        return pred;
    }

    public void setPred(HashMap<K, K> pred) {
        this.pred = pred;
    }
}
