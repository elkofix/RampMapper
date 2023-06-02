package com.example.demo1.datastructures;

import java.util.HashMap;

public interface IGraph<K> {
    public void Prim();
    public Pair<int[][], MatrizGenerica<Vertex<K>, Vertex<K>>> floyWarshall();
    public Pair<HashMap<Vertex<K>, Integer>, HashMap<Vertex<K>, Vertex<K>>> dijsktra(K value);
    public void DFS();
    public void BFS(K ver);
    public boolean edgeExists(K init, K end);
    public boolean vertexExists(K vertex);

    public boolean deleteVertex(K vertex);

    public boolean addEdge(K init, K end, int weight, String id);

    public boolean addVertex(K vertex);

    public HashMap<K, Vertex<K>> getVertexList();

}
