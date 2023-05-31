package com.example.demo1.datastructures;

import java.util.HashMap;
import java.util.Map;

class DisjointSet<T> {
    private Map<T, Node<T>> map;

    private static class Node<T> {
        T data;
        Node<T> parent;
        int rank;

        Node(T data) {
            this.data = data;
            this.parent = this;
            this.rank = 0;
        }
    }

    public DisjointSet() {
        this.map = new HashMap<>();
    }

    public void makeSet(T data) {
        if (!map.containsKey(data)) {
            Node<T> node = new Node<>(data);
            map.put(data, node);
        }
    }

    public Node<T> findSet(T data) {
        Node<T> node = map.get(data);
        if (node == null)
            return null;

        if (node.parent != node) {
            node.parent = findSet(node.parent.data);
        }
        return node.parent;
    }

    public void union(T data1, T data2) {
        Node<T> node1 = map.get(data1);
        Node<T> node2 = map.get(data2);

        if (node1 == null || node2 == null)
            return;

        Node<T> parent1 = findParent(node1);
        Node<T> parent2 = findParent(node2);

        if (parent1 == parent2)
            return;

        if (parent1.rank < parent2.rank) {
            parent1.parent = parent2;
        } else if (parent1.rank > parent2.rank) {
            parent2.parent = parent1;
        } else {
            parent2.parent = parent1;
            parent1.rank++;
        }
    }

    private Node<T> findParent(Node<T> node) {
        if (node.parent != node) {
            node.parent = findParent(node.parent);
        }
        return node.parent;
    }
}
