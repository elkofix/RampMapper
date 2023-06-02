package com.example.demo1.datastructures;

import java.util.*;

public class GraphMatrix<K> implements IGraph<K>{
    private boolean isDirected;
    int time;

    public GraphMatrix(boolean isDirected) {
        this.isDirected = isDirected;
        adjacentMatrix = new MatrizGenerica<>();
        vertexList = new HashMap<>();
    }
    public MatrizGenerica<K, Integer>  adjacentMatrix;

    @Override
    public HashMap<K, Vertex<K>> getVertexList() {
        return vertexList;
    }

    public void setVertexList(HashMap<K, Vertex<K>> vertexList) {
        this.vertexList = vertexList;
    }

    HashMap<K, Vertex<K>> vertexList;

    ArrayList<Vertex<K>> forSearch;
    public ArrayList<Vertex<K>> getForSearch() {
        return forSearch;
    }

    public void setForSearch(ArrayList<Vertex<K>> forSearch) {
        this.forSearch = forSearch;
    }

    @Override
    public boolean addVertex(K vertex) {
        if (adjacentMatrix.getValor(vertex, vertex) == null) {
            Vertex<K> v = new Vertex<>(vertex);
            adjacentMatrix.setValor(vertex, vertex, 0);
            vertexList.put(vertex, v);
            return true;
        }
        return false;

    }


    @Override
    public boolean addEdge(K init, K end, int weight, String id) {
        Vertex<K> first = vertexList.get(init);
        Vertex<K> last = vertexList.get(end);
        if (first != null && last != null) {
                if(adjacentMatrix.getValor(init, end)!=null) {
                    if (weight > adjacentMatrix.getValor(init, end)) {
                        return true;
                    }
                }
                if (isDirected) {
                    adjacentMatrix.setValor(init, end, weight);
                    return first.addEdge(new Edge<>(last, weight, id));
                }
                adjacentMatrix.setValor(init, end, weight);
                adjacentMatrix.setValor(end, init, weight);
                return first.addEdge(new Edge<>(last, weight, id)) && last.addEdge(new Edge<>(first, weight, id));

        }
        return false;
    }

    @Override
    public boolean deleteVertex(K vertex) {
       return adjacentMatrix.deleteValor(vertex, vertex);
    }

  /*  @Override
    public boolean deleteEdge(K init, K end) {
        Vertex<K> first = vertexList.get(init);
        Vertex<K> last = vertexList.get(end);
        if(first!=null && last!=null){

            boolean result1 = vertexList.get(init).getAdjacentList().remove(last);
            if(isDirected){
                return result1;
            }
            boolean result2 = vertexList.get(end).getAdjacentList().remove(first);
            return result2 && result1;
        }
        return false;
    }*/

    @Override
    public boolean vertexExists(K vertex) {
        return vertexList.get(vertex) != null;
    }

    @Override
    public boolean edgeExists(K init, K end) {
        return adjacentMatrix.getValor(init, init)!=null;
    }

    @Override
    public void BFS(K ver) {
        Vertex<K> s = vertexList.get(ver);
        if (s == null) {
            return;
        }
        ArrayList<Vertex<K>> vertices = new ArrayList<>(vertexList.values());
        for (Vertex<K> u : vertices) {
            u.color = src.model.Color.WHITE;
            u.distance = Integer.MAX_VALUE;
            u.parent = null;
        }
        s.color = src.model.Color.GRAY;
        s.distance = 0;
        s.parent = null;
        Queue<Vertex<K>> Q = new LinkedList<>();
        Q.add(s);
        Vertex<K> u;
        while (!Q.isEmpty()) {
            u = Q.remove();
            for (int i = 0; i < vertexList.size(); i++) {
                if (adjacentMatrix.getValor(u.getValue(), vertices.get(i).getValue()) != null && adjacentMatrix.getValor(u.getValue(), vertices.get(i).getValue()) != 0){
                        Vertex<K> v = vertices.get(i);
                    if (v.color == src.model.Color.WHITE) {
                        v.color = src.model.Color.GRAY;
                        v.distance = u.distance + 1;
                        v.parent = u;
                        Q.add(v);
                    }
                }
            }
            u.color = src.model.Color.BLACK;
        }
        forSearch = vertices;
    }

    @Override
    public void DFS() {
        ArrayList<Vertex<K>> vertices = new ArrayList<>(vertexList.values());
        for (Vertex<K> u : vertices) {
            u.color = src.model.Color.WHITE;
            u.distance = Integer.MAX_VALUE;
            u.parent = null;
        }
        for (Vertex<K> u : vertices) {
            if (u.color == src.model.Color.WHITE) {
                DFSvisit(u);
            }
        }
        forSearch = vertices;
    }

    public void DFSvisit(Vertex<K> u) {
        ArrayList<Vertex<K>> vertices = new ArrayList<>(vertexList.values());
        u.color = src.model.Color.GRAY;
        for (int i = 0; i < vertices.size(); i++) {
            if (adjacentMatrix.getValor(u.getValue(), vertices.get(i).getValue()) != null && adjacentMatrix.getValor(u.getValue(), vertices.get(i).getValue()) != 0){
                Vertex<K> v = vertices.get(i);
                if (v.color == src.model.Color.WHITE) {
                    v.parent = u;
                    DFSvisit(v);
                }
            }
        }
        u.color = src.model.Color.BLACK;
    }

    @Override
    public Pair<HashMap<Vertex<K>, Integer>, HashMap<Vertex<K>, Vertex<K>>> dijsktra(K value) {
        Vertex<K> source = vertexList.get(value);
        HashMap<Vertex<K>, Integer> dist = new HashMap<>();
        HashMap<Vertex<K>, Vertex<K>> prev = new HashMap<>();
        dist.put(source, 0);
        source.priority = 0;
        PriorityQueue<Vertex<K>> Q = new PriorityQueue<>(Comparator.comparingInt(o -> o.priority));
        for (Vertex<K> v : vertexList.values()) {
            if (!v.equals(source)) {
                v.priority = Integer.MAX_VALUE;
                dist.put(v, Integer.MAX_VALUE);
                prev.put(v, null);
                v.parent=null;
            }
            Q.add(v);
        }
        ArrayList<Vertex<K>> vertices = new ArrayList<>(vertexList.values());
        while (!Q.isEmpty()) {
            Vertex<K> u = Q.remove();
            for (int i = 0; i < vertexList.values().size(); i++) {
                if (adjacentMatrix.getValor(u.getValue(), vertices.get(i).getValue()) != null && adjacentMatrix.getValor(u.getValue(), vertices.get(i).getValue()) != 0){
                    Vertex<K> v = vertices.get(i);
                    int weight = adjacentMatrix.getValor(u.getValue(), vertices.get(i).getValue());
                    int alt = u.priority + weight;
                    if (alt < v.priority) {
                        dist.put(v, alt);
                        prev.put(v, u);
                        v.parent = u;
                        Q.remove(v);
                        v.priority = alt;
                        Q.add(v);
                    }
                }
            }
        }
        return new Pair<>(dist, prev);
    }


    @Override
    public Pair<int[][], MatrizGenerica<Vertex<K>, Vertex<K>>> floyWarshall() {
        ArrayList<Vertex<K>> vrtx = new ArrayList<>(vertexList.values());
        int[][] distances = new int[vertexList.size()][vertexList.size()];
        MatrizGenerica<Vertex<K>, Vertex<K>> parents = new MatrizGenerica<>();
        for (int i = 0; i < vrtx.size(); i++) {
            for (int j = 0; j < vrtx.size(); j++) {
                distances[i][j] = Integer.MAX_VALUE;
                parents.setValor(vrtx.get(i), vrtx.get(j), null);
            }
            distances[i][i] = 0;
            parents.setValor(vrtx.get(i), vrtx.get(i), vrtx.get(i));
        }
        for (Vertex<K> u : vrtx) {
            int uIndex = vrtx.indexOf(u);
            for (int i = 0; i < vertexList.values().size(); i++) {
                if (adjacentMatrix.getValor(u.getValue(), vrtx.get(i).getValue()) != null && adjacentMatrix.getValor(u.getValue(), vrtx.get(i).getValue()) != 0){
                    Vertex<K> v = vrtx.get(i);
                    int p = adjacentMatrix.getValor(u.getValue(), vrtx.get(i).getValue());
                    int vIndex = vrtx.indexOf(v);
                    distances[uIndex][vIndex] = p;
                    parents.setValor(vrtx.get(uIndex), vrtx.get(vIndex), u);
                }
            }
        }

        for (int k = 0; k < vrtx.size(); k++) {
            for (int i = 0; i < vrtx.size(); i++) {
                for (int j = 0; j < vrtx.size(); j++) {
                    int distance = (distances[i][k] == Integer.MAX_VALUE || distances[k][j] == Integer.MAX_VALUE) ? Integer.MAX_VALUE : distances[i][k] + distances[k][j];
                    if (distances[i][j] > distance) {
                        distances[i][j] = distance;
                        parents.setValor(vrtx.get(i), vrtx.get(j), parents.getValor(vrtx.get(k), vrtx.get(j)));
                    }
                }
            }
        }
        return new Pair<>(distances, parents);
    }
    @Override
    public void Prim() {
        for (Vertex<K> u : vertexList.values()) {
            u.distance = Integer.MAX_VALUE;
            u.priority = Integer.MAX_VALUE;
            u.color = src.model.Color.WHITE;
            u.parent = null;
        }
        ArrayList<Vertex<K>> as = new ArrayList<>(vertexList.values());
        PriorityQueue<Integer> QU = new PriorityQueue<>();
        Integer minummEdge = Integer.MAX_VALUE;
        Vertex<K> r = new Vertex<>(null);
        for (int i = 0; i <as.size() ; i++) {
            for (Vertex<K> a : as) {
                if (adjacentMatrix.getValor(as.get(i).getValue(), a.getValue()) != null) {
                    if(adjacentMatrix.getValor(as.get(i).getValue(), a.getValue()) < minummEdge){
                        minummEdge = adjacentMatrix.getValor(as.get(i).getValue(), a.getValue());
                        r = as.get(i);
                    }
                }
            }
        }
        r.distance = 0;
        r.priority = 0;
        PriorityQueue<Vertex<K>> Q = new PriorityQueue<Vertex<K>>(Comparator.comparingInt(o -> o.priority));
        Q.addAll(vertexList.values());
        while (!Q.isEmpty()) {
            Vertex<K> u = Q.remove();
            for (int i = 0; i < vertexList.values().size(); i++) {
                if (adjacentMatrix.getValor(u.getValue(), as.get(i).getValue()) != null && adjacentMatrix.getValor(u.getValue(), as.get(i).getValue()) != 0){
                    Vertex<K> v = as.get(i);
                    int weight = adjacentMatrix.getValor(u.getValue(), as.get(i).getValue());
                    if (v.color == src.model.Color.WHITE && weight < v.priority) {
                        Q.remove(v);
                        v.priority = weight;
                        Q.add(v);
                        v.parent = u;
                    }
                }
            }
            u.color = src.model.Color.BLACK;
        }
    }
}
