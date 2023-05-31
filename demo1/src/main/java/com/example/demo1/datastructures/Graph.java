package com.example.demo1.datastructures;

import src.model.Color;

import java.util.*;

public class Graph<K> {
    private boolean isDirected;
    int time;

    public Graph(boolean isDirected) {
        this.isDirected = isDirected;
        vertexList = new HashMap<>();
    }

    public HashMap<K, Vertex<K>> getVertexList() {
        return vertexList;
    }

    HashMap<K, Vertex<K>> vertexList;

    public ArrayList<Vertex<K>> getForSearch() {
        return forSearch;
    }

    public void setForSearch(ArrayList<Vertex<K>> forSearch) {
        this.forSearch = forSearch;
    }

    ArrayList<Vertex<K>> forSearch;

    //@Override
    public boolean addVertex(K vertex) {
        if (vertexList.get(vertex) == null) {
            vertexList.put(vertex, new Vertex<>(vertex));
            return true;
        }
        return false;
    }

    public boolean addVertex(Vertex<K> vertex) {
        if (vertexList.get(vertex.getValue()) == null) {
            vertexList.put(vertex.getValue(), vertex);
            return true;
        }
        return false;
    }

    //@Override
    public boolean addEdge(K init, K end, int weight, String id) {
        Vertex<K> first = vertexList.get(init);
        Vertex<K> last = vertexList.get(end);
        if (first != null && last != null) {
            if (isDirected) {
                return first.addEdge(new Edge<>(last, weight, id));
            }
            return first.addEdge(new Edge<>(last, weight, id)) && last.addEdge(new Edge<>(first, weight, id));
        }
        return false;
    }

    //@Override
    public boolean deleteVertex(K vertex) {
        Vertex<K> vertex1 = vertexList.get(vertex);
        if (vertex1 != null) {
            vertex1.selfDeleteVertex();
            vertexList.remove(vertex);
            return true;
        }
        return false;
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

    //@Override
    public boolean vertexExists(K vertex) {
        return vertexList.get(vertex) != null;
    }

    //@Override
    public boolean edgeExists(K init, K end) {
        Vertex<K> first = vertexList.get(init);
        Vertex<K> last = vertexList.get(end);
        if (first != null && last != null) {
            if (isDirected) {
                return first.existVertex(last);
            }
            return first.existVertex(last) || last.existVertex(first);
        }
        return false;
    }

    //@Override
    public void BFS(K ver) {
        Vertex<K> s = vertexList.get(ver);
        if (s == null) {
            return;
        }
        ArrayList<Vertex<K>> vertices = new ArrayList<>(vertexList.values());

        for (Vertex<K> u : vertices) {
            u.color = Color.WHITE;
            u.distance = Integer.MAX_VALUE;
            u.parent = null;
        }
        s.color = Color.GRAY;
        s.distance = 0;
        s.parent = null;
        Queue<Vertex<K>> Q = new LinkedList<>();
        Q.add(s);
        Vertex<K> u;
        while (!Q.isEmpty()) {
            u = Q.remove();
            for (Edge<K> a : u.adjacentList) {
                Vertex<K> v = a.getVertex();
                if (v.color == Color.WHITE) {
                    v.color = Color.GRAY;
                    v.distance = u.distance + 1;
                    v.parent = u;
                    Q.add(v);
                }
            }
            u.color = Color.BLACK;
        }
        forSearch = vertices;
    }

    //@Override
    public void DFS() {
        ArrayList<Vertex<K>> vertices = new ArrayList<>(vertexList.values());
        for (Vertex<K> u : vertices) {
            u.color = Color.WHITE;
            u.distance = Integer.MAX_VALUE;
            u.parent = null;
        }
        for (Vertex<K> u : vertices) {
            if (u.color == Color.WHITE) {
                DFSvisit(u);
            }
        }
        forSearch = vertices;
    }

    public void DFSvisit(Vertex<K> u) {
        u.color = Color.GRAY;
        for (Edge<K> a : u.adjacentList) {
            Vertex<K> v = a.getVertex();
            if (v.color == Color.WHITE) {
                v.parent = u;
                DFSvisit(v);
            }
        }
        u.color = Color.BLACK;
    }

    public Pair<HashMap<Vertex<K>, Integer>, HashMap<Vertex<K>, Vertex<K>>> dijsktra(K value) {
        Vertex<K> source = vertexList.get(value);
        HashMap<Vertex<K>, Integer> dist = new HashMap<>();
        HashMap<Vertex<K>, Vertex<K>> prev = new HashMap<>();
        dist.put(source, 0);
        source.priority = 0;
        PriorityQueue<Vertex<K>> Q = new PriorityQueue<>(Comparator.comparingInt(o -> o.priority));
        ArrayList<Vertex<K>> vertices = new ArrayList<>(vertexList.values());
        for (Vertex<K> v : vertices) {
            if (!v.equals(source)) {
                v.priority = Integer.MAX_VALUE;
                dist.put(v, Integer.MAX_VALUE);
                prev.put(v, null);
            }
            Q.add(v);
        }
        while (!Q.isEmpty()) {
            Vertex<K> u = Q.remove();
            for (Edge<K> v : u.adjacentList) {
                int alt = dist.get(u) + v.getWeight();
                if (alt < dist.get(v.getVertex())) {
                    dist.put(v.getVertex(), alt);
                    prev.put(v.getVertex(), u);
                    v.getVertex().priority = alt;
                    Vertex<K> temp = new Vertex<>(null);
                    temp.priority = Integer.MIN_VALUE;
                    Q.add(temp);
                    Q.remove(temp);
                }
            }
        }

        return new Pair<>(dist, prev);

    }

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
            for (Edge<K> p : u.getAdjacentList()) {
                Vertex<K> v = p.getVertex();
                int vIndex = vrtx.indexOf(v);
                distances[uIndex][vIndex] = p.getWeight();
                parents.setValor(vrtx.get(uIndex), vrtx.get(vIndex), u);
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

    public void Prim() {
        for (Vertex<K> u : vertexList.values()) {
            u.distance = Integer.MAX_VALUE;
            u.priority = Integer.MAX_VALUE;
            u.color = Color.WHITE;
            u.parent = null;
        }
        ArrayList<Vertex<K>> as = new ArrayList<>(vertexList.values());
        PriorityQueue<Edge<K>> QU = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        for (Vertex<K> a : as) {
            QU.addAll(a.getAdjacentList());
        }
        Edge<K> minummEdge = QU.remove();
        System.out.println(minummEdge.getWeight());
        Vertex<K> r = minummEdge.getVertex();
        r.distance = 0;
        r.priority = 0;
        PriorityQueue<Vertex<K>> Q = new PriorityQueue<Vertex<K>>(Comparator.comparingInt(o -> o.priority));
        Q.addAll(vertexList.values());
        while (!Q.isEmpty()) {
            Vertex<K> u = Q.remove();
            System.out.println(u.priority);
            for (Edge<K> v : u.adjacentList) {
                if (v.getVertex().color == Color.WHITE && v.getWeight() < v.getVertex().priority) {
                    Q.remove(v.getVertex());
                    v.getVertex().priority = v.getWeight();
                    Q.add(v.getVertex());
                    v.getVertex().parent = u;
                }
            }
            u.color = Color.BLACK;
        }
    }
}
/*
    public void Kruskal(){
        Graph<K> A = new Graph<>(false);
        DisjointSet<Vertex<K>> B = new DisjointSet<>();
        for (Vertex<K> u: vertexList.values()) {
            B.makeSet(u);
        }
        ArrayList<Vertex<K>> as = new ArrayList<>(vertexList.values());
        ArrayList<Edge<K>> bd = new ArrayList<>();
        for (Vertex<K> a: as) {
            bd.addAll(a.getAdjacentList());
        }
        bd.sort(new EdgeComparator<K>());
        for (Vertex<K> v:bd) {
            if(B.findSet(v) != B.findSet())<
        }
        
    }
*/
