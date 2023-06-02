package com.example.demo1.test;

import static org.junit.Assert.*;

import com.example.demo1.datastructures.*;
import org.junit.Test;
import src.model.Color;

import java.util.ArrayList;
import java.util.HashMap;



public class GraphTest<K> {

    private Graph<Integer> graph;
    private GraphMatrix<Integer> graphM;


    public void setUp() {
        graph = new Graph<>(false);
    }

    public void setUp2() {
        graphM = new GraphMatrix<>(false);
    }

    @Test
    public void addVertexTest() {
        setUp();
        boolean result2 = graph.addVertex(1);
        boolean result = graph.addVertex(2);
        boolean result3 = graph.addVertex(3);
        assertTrue(result2);
        assertTrue(result);
        assertTrue(result3);
    }

    @Test
    public void addVertexTestMatrix() {
        setUp2();
        boolean result2 = graphM.addVertex(1);
        boolean result = graphM.addVertex(2);
        boolean result3 = graphM.addVertex(3);
        assertTrue(result2);
        assertTrue(result);
        assertTrue(result3);
    }

    @Test
    public void addVertexDirectTest() {
        setUp();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        int result1 = graph.getVertexList().get(1).getValue();
        int result2 = graph.getVertexList().get(2).getValue();
        int result3 = graph.getVertexList().get(3).getValue();
        assertEquals(1, result1);
        assertEquals(2,result2);
        assertEquals(3, result3);
    }

    @Test
    public void addVertexDirectMatrixTest() {
        setUp2();
        graphM.addVertex(1);
        graphM.addVertex(2);
        graphM.addVertex(3);
        int result1 = graphM.adjacentMatrix.getValor(1, 1);
        int result2 = graphM.adjacentMatrix.getValor(2, 2);
        int result3 = graphM.adjacentMatrix.getValor(3, 3);
        assertEquals(0, result1);
        assertEquals(0,result2);
        assertEquals(0, result3);
    }

    @Test
    public void addAlreadyExistingVertex() {
        setUp();
        boolean result2 = graph.addVertex(1);
        boolean result = graph.addVertex(1);
        assertTrue(result2);
        assertFalse(result);
    }

    @Test
    public void addAlreadyExistingVertexMatrix() {
        setUp2();
        boolean result2 = graphM.addVertex(1);
        boolean result = graphM.addVertex(1);
        assertTrue(result2);
        assertFalse(result);
    }
    @Test
    public void addEdgeTest() {
        setUp();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2, 1, "");
        boolean exist = graph.edgeExists(1, 2);
        assertTrue(exist);
    }

    @Test
    public void addEdgeTestMatrix() {
        setUp2();
        graphM.addVertex(1);
        graphM.addVertex(2);
        graphM.addEdge(1, 2, 1, "");
        boolean exist = graphM.edgeExists(1, 2);
        assertTrue(exist);
    }

    @Test
    public void addEdgeTestNoExistingVertex() {
        setUp();
        graph.addVertex(1);
        assertFalse(graph.addEdge(1, 2, 1, ""));
    }

    @Test
    public void addEdgeTestNoExistingVertexMatrix() {
        setUp2();
        graphM.addVertex(1);
        assertFalse(graphM.addEdge(1, 2, 1, ""));
    }

    @Test
    public void addAlreadyExistingEdgeTest() {
        setUp();
        graph.addVertex(1);
        graph.addVertex(2);
        boolean result1 = graph.addEdge(1, 2, 1, "");
        boolean result2 = graph.addEdge(1, 2, 1, "");
        //Como el grafo permite tener mas de un camino entre dos vertices deberia ser true
        assertTrue(result1);
        assertTrue(result2);
    }

    @Test
    public void addAlreadyExistingEdgeMatrixTest() {
        setUp2();
        graphM.addVertex(1);
        graphM.addVertex(2);
        boolean result1 = graphM.addEdge(1, 2, 1, "");
        boolean result2 = graphM.addEdge(1, 2, 1, "");
        //Como el grafo permite tener mas de un camino entre dos vertices deberia ser true
        assertTrue(result1);
        assertTrue(result2);
    }
    @Test
    public void deleteEdgeTest() {
        setUp();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2, 5,"1to2");
        graph.deleteEdge(1, 2, "1to2");
        boolean exist = graph.edgeExists(1, 2);
        assertFalse(exist);
    }

    @Test
    public void deleteEdgeMatrixTest() {
        setUp2();
        graphM.addVertex(1);
        graphM.addVertex(2);
        graphM.addEdge(1, 2, 5,"1to2");
        graphM.deleteEdge(1, 2, "1to2");
        boolean exist = graphM.edgeExists(1, 2);
        assertFalse(exist);
    }

    @Test
    public void deleteNotExistingEdgeTest() {
        setUp();
        graph.addVertex(1);
        graph.addVertex(2);
        boolean result = graph.deleteEdge(1, 2, "1to2");
        assertFalse(result);
    }

    @Test
    public void deleteNotExistingEdgeMatrixTest() {
        setUp2();
        graphM.addVertex(1);
        graphM.addVertex(2);
        boolean result = graphM.deleteEdge(1, 2, "1to2");
        assertFalse(result);
    }
    @Test
    public void deleteEdgeNotExistingVertexTest() {
        setUp();
        graph.addVertex(1);
        boolean result = graph.deleteEdge(1, 2, "1to2");
        assertFalse(result);
    }

    @Test
    public void deleteEdgeNotExistingVertexMatrixTest() {
        setUp2();
        graphM.addVertex(1);
        boolean result = graphM.deleteEdge(1, 2, "1to2");
        assertFalse(result);
    }

    @Test
    public void deleteVertexTest() {
        setUp();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addEdge(1, 2, 2,"1to2");
        graph.addEdge(1, 3, 2,"1to3");
        graph.addEdge(3, 2, 1,"3to2");
        graph.addEdge(3, 4, 3,"3to4");
        graph.deleteVertex(1);
        boolean exist = graph.edgeExists(1, 2);
        boolean exist2 = graph.edgeExists(1, 3);
        assertFalse(exist);
        assertFalse(exist2);
    }

    @Test
    public void deleteVertexMatrixTest() {
        setUp2();
        graphM.addVertex(1);
        graphM.addVertex(2);
        graphM.addVertex(3);
        graphM.addVertex(4);
        graphM.addEdge(1, 2, 2,"1to2");
        graphM.addEdge(1, 3, 2,"1to3");
        graphM.addEdge(3, 2, 1,"3to2");
        graphM.addEdge(3, 4, 3,"3to4");
        graphM.deleteVertex(1);
        boolean exist = graphM.edgeExists(1, 2);
        boolean exist2 = graphM.edgeExists(1, 3);
        assertFalse(exist);
        assertFalse(exist2);
    }

    @Test
    public void deleteVertexDirectTest() {
        setUp();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addEdge(1,  2, 2, "1to2");
        Vertex<Integer> ver = graph.getVertexList().get(2);
        boolean result1 = ver.getAdjacentList().isEmpty();
        graph.deleteVertex(1);
        Vertex<Integer> result = graph.getVertexList().get(1);
        ver = graph.getVertexList().get(2);
        boolean result2 = ver.getAdjacentList().isEmpty();
        assertFalse(result1);
        assertTrue(result2);
        assertNull(result);
    }

    @Test
    public void deleteVertexDirectMatrixTest() {
        setUp2();
        graphM.addVertex(1);
        graphM.addVertex(2);
        graphM.addVertex(3);
        graphM.addVertex(4);
        graphM.addEdge(1,  2, 2, "1to2");
        Vertex<Integer> ver = graphM.getVertexList().get(2);
        boolean result1 = ver.getAdjacentList().isEmpty();
        graphM.deleteVertex(1);
        Vertex<Integer> result = graphM.getVertexList().get(1);
        Integer result2 = graphM.adjacentMatrix.getValor(1, 2);
        assertFalse(result1);
        assertNull(result2);
        assertNull(result);
    }

    @Test
    public void deleteNotExistingVertexTest() {
        setUp();
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        boolean result = graph.deleteVertex(1);
        assertFalse(result);
    }

    @Test
    public void deleteNotExistingVertexMatrixTest() {
        setUp2();
        graphM.addVertex(2);
        graphM.addVertex(3);
        graphM.addVertex(4);
        boolean result = graphM.deleteVertex(1);
        assertFalse(result);
    }
    @Test
    public void BFSTestParents() {
        setUp();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addEdge(1, 2, 1, "");
        graph.addEdge(3, 2, 1, "");
        graph.addEdge(3, 4, 1, "");
        graph.addEdge(3, 5,1, "");
        graph.BFS(1);
        Color col = graph.getForSearch().get(0).getColor();
        int dis=graph.getForSearch().get(1).getDistance();
        Vertex<Integer> a = graph.getForSearch().get(0);//raiz 1
        Vertex<Integer> par = graph.getForSearch().get(1).getParent(); //vertice 2 debe tener a padre 1
        Vertex<Integer> result = graph.getForSearch().get(1); //vertice 2
        Vertex<Integer> expected = graph.getForSearch().get(2).getParent(); // vertice 3 debe tener a padre 2
        Vertex<Integer> result1 = graph.getForSearch().get(2); //vertice 3
        Vertex<Integer> expected1 = graph.getForSearch().get(3).getParent(); // vertice 4 debe tener a padre 3
        Vertex<Integer> expected2 = graph.getForSearch().get(4).getParent(); // vertice 5 debe tener a padre 3
        assertEquals(a, par);
        assertEquals(expected, result);
        assertEquals(expected1, result1);
        assertEquals(expected2, result1);
    }

    @Test
    public void BFSTestParentsMatrix() {
        setUp2();
        graphM.addVertex(1);
        graphM.addVertex(2);
        graphM.addVertex(3);
        graphM.addVertex(4);
        graphM.addVertex(5);
        graphM.addEdge(1, 2, 1, "");
        graphM.addEdge(3, 2, 1, "");
        graphM.addEdge(3, 4, 1, "");
        graphM.addEdge(3, 5,1, "");
        graphM.BFS(1);
        Color col = graphM.getForSearch().get(0).getColor();
        int dis=graphM.getForSearch().get(1).getDistance();
        Vertex<Integer> a = graphM.getForSearch().get(0);//raiz 1
        Vertex<Integer> par = graphM.getForSearch().get(1).getParent(); //vertice 2 debe tener a padre 1
        Vertex<Integer> result = graphM.getForSearch().get(1); //vertice 2
        Vertex<Integer> expected = graphM.getForSearch().get(2).getParent(); // vertice 3 debe tener a padre 2
        Vertex<Integer> result1 = graphM.getForSearch().get(2); //vertice 3
        Vertex<Integer> expected1 = graphM.getForSearch().get(3).getParent(); // vertice 4 debe tener a padre 3
        Vertex<Integer> expected2 = graphM.getForSearch().get(4).getParent(); // vertice 5 debe tener a padre 3
        assertEquals(a, par);
        assertEquals(expected, result);
        assertEquals(expected1, result1);
        assertEquals(expected2, result1);
    }

    @Test
    public void BFSTestDistance() {
        setUp();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addEdge(1, 2, 1, "");
        graph.addEdge(3, 2, 1, "");
        graph.addEdge(3, 4, 1, "");
        graph.addEdge(3, 5, 1, "");
        graph.BFS(1);
        int result = graph.getForSearch().get(0).getDistance();//raiz 1 distancia 0
        int result1 = graph.getForSearch().get(1).getDistance(); //vertice 2 distancia 1
        int result2 = graph.getForSearch().get(2).getDistance(); //vertice 3 distancia 2
        int result3 = graph.getForSearch().get(3).getDistance(); //vertice 4 distancia 3
        int result4 = graph.getForSearch().get(4).getDistance(); //vertice 5 distancia 3
        assertEquals(0, result);
        assertEquals(1, result1);
        assertEquals(2, result2);
        assertEquals(3, result3);
        assertEquals(3, result4);
    }


    @Test
    public void BFSTestDistanceMatrix() {
        setUp2();
        graphM.addVertex(1);
        graphM.addVertex(2);
        graphM.addVertex(3);
        graphM.addVertex(4);
        graphM.addVertex(5);
        graphM.addEdge(1, 2, 1, "");
        graphM.addEdge(3, 2, 1, "");
        graphM.addEdge(3, 4, 1, "");
        graphM.addEdge(3, 5, 1, "");
        graphM.BFS(1);
        int result = graphM.getForSearch().get(0).getDistance();//raiz 1 distancia 0
        int result1 = graphM.getForSearch().get(1).getDistance(); //vertice 2 distancia 1
        int result2 = graphM.getForSearch().get(2).getDistance(); //vertice 3 distancia 2
        int result3 = graphM.getForSearch().get(3).getDistance(); //vertice 4 distancia 3
        int result4 = graphM.getForSearch().get(4).getDistance(); //vertice 5 distancia 3
        assertEquals(0, result);
        assertEquals(1, result1);
        assertEquals(2, result2);
        assertEquals(3, result3);
        assertEquals(3, result4);
    }

    @Test
    public void BFSTestColor() {
        setUp();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addEdge(1, 2, 1, "");
        graph.addEdge(3, 2, 1, "");
        graph.addEdge(3, 4, 1, "");
        graph.addEdge(3, 5,1, "");
        graph.BFS(1);
        Color result = graph.getForSearch().get(0).getColor();//raiz 1
        Color result1 = graph.getForSearch().get(1).getColor(); //vertice 2
        Color result2 = graph.getForSearch().get(2).getColor(); //vertice 3
        Color result3 = graph.getForSearch().get(3).getColor(); //vertice 4
        Color result4 = graph.getForSearch().get(4).getColor(); //vertice 5
        //Todos deben haber quedado negros
        assertEquals(Color.BLACK, result);
        assertEquals(Color.BLACK, result1);
        assertEquals(Color.BLACK, result2);
        assertEquals(Color.BLACK, result3);
        assertEquals(Color.BLACK, result4);
    }

    @Test
    public void BFSTestColorMatrix() {
        setUp2();
        graphM.addVertex(1);
        graphM.addVertex(2);
        graphM.addVertex(3);
        graphM.addVertex(4);
        graphM.addVertex(5);
        graphM.addEdge(1, 2, 1, "");
        graphM.addEdge(3, 2, 1, "");
        graphM.addEdge(3, 4, 1, "");
        graphM.addEdge(3, 5,1, "");
        graphM.BFS(1);
        Color result = graphM.getForSearch().get(0).getColor();//raiz 1
        Color result1 = graphM.getForSearch().get(1).getColor(); //vertice 2
        Color result2 = graphM.getForSearch().get(2).getColor(); //vertice 3
        Color result3 = graphM.getForSearch().get(3).getColor(); //vertice 4
        Color result4 = graphM.getForSearch().get(4).getColor(); //vertice 5
        //Todos deben haber quedado negros
        assertEquals(Color.BLACK, result);
        assertEquals(Color.BLACK, result1);
        assertEquals(Color.BLACK, result2);
        assertEquals(Color.BLACK, result3);
        assertEquals(Color.BLACK, result4);
    }

    @Test
    public void DFSTestParents() {
        setUp();
        graph = new Graph<>(true);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);
        graph.addEdge(1, 2, 1, "");
        graph.addEdge(1, 3, 1, "");
        graph.addEdge(3, 2, 1, "");
        graph.addEdge(2, 4,1, "");
        graph.addEdge(4, 3, 1, "");
        graph.addEdge(5, 4, 1, "");
        graph.addEdge(5, 6, 1, "");
        graph.addEdge(6, 6,1, "");
        graph.DFS();
        Vertex<Integer> expected = graph.getForSearch().get(0);
        Vertex<Integer> result = graph.getForSearch().get(0).getParent();
        Vertex<Integer> result1 = graph.getForSearch().get(1).getParent();
        Vertex<Integer> expected1 = graph.getForSearch().get(1);
        Vertex<Integer> result2 = graph.getForSearch().get(2).getParent();
        Vertex<Integer> expected2 = graph.getForSearch().get(3);
        Vertex<Integer> result3 = graph.getForSearch().get(3).getParent();
        Vertex<Integer> result4 = graph.getForSearch().get(4).getParent();
        assertNull(result);
        assertEquals(expected, result1);
        assertEquals(expected1, result3);
        assertEquals(expected2, result2);
        assertNull(result4);
    }

    @Test
    public void DFSTestParentsMatrix() {
        setUp2();
        graphM = new GraphMatrix<>(true);
        graphM.addVertex(1);
        graphM.addVertex(2);
        graphM.addVertex(3);
        graphM.addVertex(4);
        graphM.addVertex(5);
        graphM.addVertex(6);
        graphM.addEdge(1, 2, 1, "");
        graphM.addEdge(1, 3, 1, "");
        graphM.addEdge(3, 2, 1, "");
        graphM.addEdge(2, 4,1, "");
        graphM.addEdge(4, 3, 1, "");
        graphM.addEdge(5, 4, 1, "");
        graphM.addEdge(5, 6, 1, "");
        graphM.addEdge(6, 6,1, "");
        graphM.DFS();
        Vertex<Integer> expected = graphM.getForSearch().get(0);
        Vertex<Integer> result = graphM.getForSearch().get(0).getParent();
        Vertex<Integer> result1 = graphM.getForSearch().get(1).getParent();
        Vertex<Integer> expected1 = graphM.getForSearch().get(1);
        Vertex<Integer> result2 = graphM.getForSearch().get(2).getParent();
        Vertex<Integer> expected2 = graphM.getForSearch().get(3);
        Vertex<Integer> result3 = graphM.getForSearch().get(3).getParent();
        Vertex<Integer> result4 = graphM.   getForSearch().get(4).getParent();
        assertNull(result);
        assertEquals(expected, result1);
        assertEquals(expected1, result3);
        assertEquals(expected2, result2);
        assertNull(result4);
    }

    @Test
    public void DFSTestColor() {
        setUp();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addEdge(1, 2, 1, "");
        graph.addEdge(3, 2,1, "");
        graph.addEdge(3, 4, 1, "");
        graph.addEdge(3, 5,1, "");
        graph.DFS();
        Color result = graph.getForSearch().get(0).getColor();//raiz 1
        Color result1 = graph.getForSearch().get(1).getColor(); //vertice 2
        Color result2 = graph.getForSearch().get(2).getColor(); //vertice 3
        Color result3 = graph.getForSearch().get(3).getColor(); //vertice 4
        Color result4 = graph.getForSearch().get(4).getColor(); //vertice 5
        //Todos deben haber quedado negros
        assertEquals(Color.BLACK, result);
        assertEquals(Color.BLACK, result1);
        assertEquals(Color.BLACK, result2);
        assertEquals(Color.BLACK, result3);
        assertEquals(Color.BLACK, result4);
    }

    @Test
    public void DFSTestColorMatrix() {
        setUp2();
        graphM.addVertex(1);
        graphM.addVertex(2);
        graphM.addVertex(3);
        graphM.addVertex(4);
        graphM.addVertex(5);
        graphM.addEdge(1, 2, 1, "");
        graphM.addEdge(3, 2,1, "");
        graphM.addEdge(3, 4, 1, "");
        graphM.addEdge(3, 5,1, "");
        graphM.DFS();
        Color result = graphM.getForSearch().get(0).getColor();//raiz 1
        Color result1 = graphM.getForSearch().get(1).getColor(); //vertice 2
        Color result2 = graphM.getForSearch().get(2).getColor(); //vertice 3
        Color result3 = graphM.getForSearch().get(3).getColor(); //vertice 4
        Color result4 = graphM.getForSearch().get(4).getColor(); //vertice 5
        //Todos deben haber quedado negros
        assertEquals(Color.BLACK, result);
        assertEquals(Color.BLACK, result1);
        assertEquals(Color.BLACK, result2);
        assertEquals(Color.BLACK, result3);
        assertEquals(Color.BLACK, result4);
    }

    @Test
    public void DijsktraTest() {
        setUp();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);
        graph.addEdge(1, 2, 1, "");
        graph.addEdge(1, 5, 1, "");
        graph.addEdge(3, 2, 5, "");
        graph.addEdge(2, 5, 3, "");
        graph.addEdge(3, 4, 2, "");
        graph.addEdge(3, 5,2, "");
        graph.addEdge(4, 5, 1, "");
        graph.addEdge(1, 6, 26, "");
        Pair<HashMap<Vertex<Integer>, Integer>, HashMap<Vertex<Integer>, Vertex<Integer>>> result = graph.dijsktra(1);
        HashMap<Vertex<Integer>, Vertex<Integer>> predecesor = result.getValue2();
        HashMap<Integer, Vertex<Integer>> list  = graph.getVertexList();
        Vertex<Integer>  last = predecesor.get(list.get(1));
        Vertex<Integer>  last2 = predecesor.get(list.get(2));
        Vertex<Integer>  last3 = predecesor.get(list.get(3));
        Vertex<Integer>  last4 = predecesor.get(list.get(4));
        Vertex<Integer>  last5 = predecesor.get(list.get(5));
        Vertex<Integer>  last6 = predecesor.get(list.get(6));
        assertNull(last);
        assertEquals(list.get(1),last2);
        assertEquals(list.get(5),last3);
        assertEquals(list.get(5),last4);
        assertEquals(list.get(1),last5);
        assertEquals(list.get(1),last6);
    }

    @Test
    public void DijsktraTestMatrix() {
        setUp2();
        graphM.addVertex(1);
        graphM.addVertex(2);
        graphM.addVertex(3);
        graphM.addVertex(4);
        graphM.addVertex(5);
        graphM.addVertex(6);
        graphM.addEdge(1, 2, 1, "");
        graphM.addEdge(1, 5, 1, "");
        graphM.addEdge(3, 2, 5, "");
        graphM.addEdge(2, 5, 3, "");
        graphM.addEdge(3, 4, 2, "");
        graphM.addEdge(3, 5,2, "");
        graphM.addEdge(4, 5, 1, "");
        graphM.addEdge(1, 6, 26, "");
        Pair<HashMap<Vertex<Integer>, Integer>, HashMap<Vertex<Integer>, Vertex<Integer>>> result = graphM.dijsktra(1);
        HashMap<Vertex<Integer>, Vertex<Integer>> predecesor = result.getValue2();
        HashMap<Integer, Vertex<Integer>> list  = graphM.getVertexList();
        Vertex<Integer>  last = predecesor.get(list.get(1));
        Vertex<Integer>  last2 = predecesor.get(list.get(2));
        Vertex<Integer>  last3 = predecesor.get(list.get(3));
        Vertex<Integer>  last4 = predecesor.get(list.get(4));
        Vertex<Integer>  last5 = predecesor.get(list.get(5));
        Vertex<Integer>  last6 = predecesor.get(list.get(6));
        assertNull(last);
        assertEquals(list.get(1),last2);
        assertEquals(list.get(5),last3);
        assertEquals(list.get(5),last4);
        assertEquals(list.get(1),last5);
        assertEquals(list.get(1),last6);
    }

    @Test
    public void floydWarshallTest() {
        setUp();
        ArrayList<Vertex<Integer>> vrtx = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Vertex<Integer> vr = new Vertex<>(i);
            vrtx.add(vr);
            graph.addVertex(vr);
        }
        graph.addEdge(1, 10 ,2, "");
        graph.addEdge(2, 5 ,3, "");
        graph.addEdge(4, 2 ,1, "");
        graph.addEdge(3, 10 ,5, "");
        graph.addEdge(6, 4 ,2, "");
        graph.addEdge(1, 7 ,1, "");
        graph.addEdge(10, 7 ,1, "");
        graph.addEdge(8, 10 ,6, "");
        graph.addEdge(3, 9 ,1, "");
        graph.addEdge(3, 2 ,5, "");
        graph.addEdge(7, 5 ,7, "");
        Pair<int[][], MatrizGenerica<Vertex<Integer>, Vertex<Integer>>> res = graph.floyWarshall();
        MatrizGenerica<Vertex<Integer>, Vertex<Integer>> source = res.getValue2();

        assertEquals(vrtx.get(6).getValue(), source.getValor(vrtx.get(6), vrtx.get(0)).getValue());
        assertEquals(vrtx.get(4).getValue(), source.getValor(vrtx.get(6), vrtx.get(1)).getValue());
        assertEquals(vrtx.get(9).getValue(), source.getValor(vrtx.get(6), vrtx.get(2)).getValue());
        assertEquals(vrtx.get(1).getValue(), source.getValor(vrtx.get(6), vrtx.get(3)).getValue());
        assertEquals(vrtx.get(6).getValue(), source.getValor(vrtx.get(6), vrtx.get(4)).getValue());
        assertEquals(vrtx.get(3).getValue(), source.getValor(vrtx.get(6), vrtx.get(5)).getValue());
        assertEquals(vrtx.get(6).getValue(), source.getValor(vrtx.get(6), vrtx.get(6)).getValue());
        assertEquals(vrtx.get(9).getValue(), source.getValor(vrtx.get(6), vrtx.get(7)).getValue());
        assertEquals(vrtx.get(2).getValue(), source.getValor(vrtx.get(6), vrtx.get(8)).getValue());
        assertEquals(vrtx.get(6).getValue(), source.getValor(vrtx.get(6), vrtx.get(9)).getValue());
        assertEquals(vrtx.get(6).getValue(), source.getValor(vrtx.get(1), vrtx.get(0)).getValue());
        assertEquals(vrtx.get(1).getValue(), source.getValor(vrtx.get(1), vrtx.get(1)).getValue());
        assertEquals(vrtx.get(1).getValue(), source.getValor(vrtx.get(1), vrtx.get(2)).getValue());
        assertEquals(vrtx.get(1).getValue(), source.getValor(vrtx.get(1), vrtx.get(3)).getValue());
        assertEquals(vrtx.get(1).getValue(), source.getValor(vrtx.get(1), vrtx.get(4)).getValue());
        assertEquals(vrtx.get(3).getValue(), source.getValor(vrtx.get(1), vrtx.get(5)).getValue());
        assertEquals(vrtx.get(4).getValue(), source.getValor(vrtx.get(1), vrtx.get(6)).getValue());
        assertEquals(vrtx.get(9).getValue(), source.getValor(vrtx.get(1), vrtx.get(7)).getValue());
        assertEquals(vrtx.get(2).getValue(), source.getValor(vrtx.get(1), vrtx.get(8)).getValue());
        assertEquals(vrtx.get(2).getValue(), source.getValor(vrtx.get(1), vrtx.get(9)).getValue());
    }

    @Test
    public void floydWarshallMatrixTest() {
        setUp2();
        for (int i = 1; i <= 10; i++) {
            Vertex<Integer> vr = new Vertex<>(i);
            graphM.addVertex(i);
        }
        ArrayList<Vertex<Integer>> vrtx = new ArrayList<>(graphM.getVertexList().values());
        graphM.addEdge(1, 10,2, "");
        graphM.addEdge(2, 5 ,3, "");
        graphM.addEdge(4, 2 ,1, "");
        graphM.addEdge(3, 10 ,5, "");
        graphM.addEdge(6, 4 ,2, "");
        graphM.addEdge(1, 7 ,1, "");
        graphM.addEdge(10, 7 ,1, "");
        graphM.addEdge(8, 10 ,6, "");
        graphM.addEdge(3, 9 ,1, "");
        graphM.addEdge(3, 2 ,5, "");
        graphM.addEdge(7, 5 ,7, "");
        Pair<int[][], MatrizGenerica<Vertex<Integer>, Vertex<Integer>>> res = graphM.floyWarshall();
        MatrizGenerica<Vertex<Integer>, Vertex<Integer>> source = res.getValue2();
        assertEquals(vrtx.get(6).getValue(), source.getValor(vrtx.get(6), vrtx.get(0)).getValue());
        assertEquals(vrtx.get(4).getValue(), source.getValor(vrtx.get(6), vrtx.get(1)).getValue());
        assertEquals(vrtx.get(9).getValue(), source.getValor(vrtx.get(6), vrtx.get(2)).getValue());
        assertEquals(vrtx.get(1).getValue(), source.getValor(vrtx.get(6), vrtx.get(3)).getValue());
        assertEquals(vrtx.get(6).getValue(), source.getValor(vrtx.get(6), vrtx.get(4)).getValue());
        assertEquals(vrtx.get(3).getValue(), source.getValor(vrtx.get(6), vrtx.get(5)).getValue());
        assertEquals(vrtx.get(6).getValue(), source.getValor(vrtx.get(6), vrtx.get(6)).getValue());
        assertEquals(vrtx.get(9).getValue(), source.getValor(vrtx.get(6), vrtx.get(7)).getValue());
        assertEquals(vrtx.get(2).getValue(), source.getValor(vrtx.get(6), vrtx.get(8)).getValue());
        assertEquals(vrtx.get(6).getValue(), source.getValor(vrtx.get(6), vrtx.get(9)).getValue());
        assertEquals(vrtx.get(6).getValue(), source.getValor(vrtx.get(1), vrtx.get(0)).getValue());
        assertEquals(vrtx.get(1).getValue(), source.getValor(vrtx.get(1), vrtx.get(1)).getValue());
        assertEquals(vrtx.get(1).getValue(), source.getValor(vrtx.get(1), vrtx.get(2)).getValue());
        assertEquals(vrtx.get(1).getValue(), source.getValor(vrtx.get(1), vrtx.get(3)).getValue());
        assertEquals(vrtx.get(1).getValue(), source.getValor(vrtx.get(1), vrtx.get(4)).getValue());
        assertEquals(vrtx.get(3).getValue(), source.getValor(vrtx.get(1), vrtx.get(5)).getValue());
        assertEquals(vrtx.get(4).getValue(), source.getValor(vrtx.get(1), vrtx.get(6)).getValue());
        assertEquals(vrtx.get(9).getValue(), source.getValor(vrtx.get(1), vrtx.get(7)).getValue());
        assertEquals(vrtx.get(2).getValue(), source.getValor(vrtx.get(1), vrtx.get(8)).getValue());
        assertEquals(vrtx.get(2).getValue(), source.getValor(vrtx.get(1), vrtx.get(9)).getValue());

    }
    @Test
    public void floydWarshallTest2() {
        setUp();
        Graph<String> graphS = new Graph<>(false);

        graphS.addVertex("A");
        graphS.addVertex("B");
        graphS.addVertex("C");
        graphS.addVertex("D");
        graphS.addEdge("A", "B",1, "");
        graphS.addEdge("A", "C" ,5, "");
        graphS.addEdge("C", "B" ,2, "");
        graphS.addEdge("C", "D" ,1, "");
        graphS.addEdge("B", "D" ,2, "");
        ArrayList<Vertex<String>> vrtx = new ArrayList<>(graphS.getVertexList().values());
        Pair<int[][], MatrizGenerica<Vertex<String>, Vertex<String>>> res = graphS.floyWarshall();
        MatrizGenerica<Vertex<String>, Vertex<String>> source = res.getValue2();
        assertEquals(vrtx.get(0).getValue(), source.getValor(vrtx.get(0), vrtx.get(0)).getValue());
        assertEquals(vrtx.get(0).getValue(), source.getValor(vrtx.get(0), vrtx.get(1)).getValue());
        assertEquals(vrtx.get(1).getValue(), source.getValor(vrtx.get(0), vrtx.get(2)).getValue());
        assertEquals(vrtx.get(1).getValue(), source.getValor(vrtx.get(0), vrtx.get(3)).getValue());
        assertEquals(vrtx.get(1).getValue(), source.getValor(vrtx.get(1), vrtx.get(0)).getValue());
        assertEquals(vrtx.get(1).getValue(), source.getValor(vrtx.get(1), vrtx.get(1)).getValue());
        assertEquals(vrtx.get(1).getValue(), source.getValor(vrtx.get(1), vrtx.get(2)).getValue());
        assertEquals(vrtx.get(1).getValue(), source.getValor(vrtx.get(1), vrtx.get(3)).getValue());
        assertEquals(vrtx.get(1).getValue(), source.getValor(vrtx.get(2), vrtx.get(0)).getValue());
        assertEquals(vrtx.get(2).getValue(), source.getValor(vrtx.get(2), vrtx.get(1)).getValue());
        assertEquals(vrtx.get(2).getValue(), source.getValor(vrtx.get(2), vrtx.get(2)).getValue());
        assertEquals(vrtx.get(2).getValue(), source.getValor(vrtx.get(2), vrtx.get(3)).getValue());
        assertEquals(vrtx.get(1).getValue(), source.getValor(vrtx.get(3), vrtx.get(0)).getValue());
        assertEquals(vrtx.get(3).getValue(), source.getValor(vrtx.get(3), vrtx.get(1)).getValue());
        assertEquals(vrtx.get(3).getValue(), source.getValor(vrtx.get(3), vrtx.get(2)).getValue());
        assertEquals(vrtx.get(3).getValue(), source.getValor(vrtx.get(3), vrtx.get(3)).getValue());
    }

    @Test
    public void floydWarshallMatrixTest2() {
        setUp2();
        GraphMatrix<String> graphS = new GraphMatrix<>(false);
        graphS.addVertex("A");
        graphS.addVertex("B");
        graphS.addVertex("C");
        graphS.addVertex("D");
        graphS.addEdge("A", "B",1, "");
        graphS.addEdge("A", "C" ,5, "");
        graphS.addEdge("C", "B" ,2, "");
        graphS.addEdge("C", "D" ,1, "");
        graphS.addEdge("B", "D" ,2, "");
        ArrayList<Vertex<String>> vrtx = new ArrayList<>(graphS.getVertexList().values());
        Pair<int[][], MatrizGenerica<Vertex<String>, Vertex<String>>> res = graphS.floyWarshall();
        MatrizGenerica<Vertex<String>, Vertex<String>> source = res.getValue2();
        assertEquals(vrtx.get(0).getValue(), source.getValor(vrtx.get(0), vrtx.get(0)).getValue());
        assertEquals(vrtx.get(0).getValue(), source.getValor(vrtx.get(0), vrtx.get(1)).getValue());
        assertEquals(vrtx.get(1).getValue(), source.getValor(vrtx.get(0), vrtx.get(2)).getValue());
        assertEquals(vrtx.get(1).getValue(), source.getValor(vrtx.get(0), vrtx.get(3)).getValue());
        assertEquals(vrtx.get(1).getValue(), source.getValor(vrtx.get(1), vrtx.get(0)).getValue());
        assertEquals(vrtx.get(1).getValue(), source.getValor(vrtx.get(1), vrtx.get(1)).getValue());
        assertEquals(vrtx.get(1).getValue(), source.getValor(vrtx.get(1), vrtx.get(2)).getValue());
        assertEquals(vrtx.get(1).getValue(), source.getValor(vrtx.get(1), vrtx.get(3)).getValue());
        assertEquals(vrtx.get(1).getValue(), source.getValor(vrtx.get(2), vrtx.get(0)).getValue());
        assertEquals(vrtx.get(2).getValue(), source.getValor(vrtx.get(2), vrtx.get(1)).getValue());
        assertEquals(vrtx.get(2).getValue(), source.getValor(vrtx.get(2), vrtx.get(2)).getValue());
        assertEquals(vrtx.get(2).getValue(), source.getValor(vrtx.get(2), vrtx.get(3)).getValue());
        assertEquals(vrtx.get(1).getValue(), source.getValor(vrtx.get(3), vrtx.get(0)).getValue());
        assertEquals(vrtx.get(3).getValue(), source.getValor(vrtx.get(3), vrtx.get(1)).getValue());
        assertEquals(vrtx.get(3).getValue(), source.getValor(vrtx.get(3), vrtx.get(2)).getValue());
        assertEquals(vrtx.get(3).getValue(), source.getValor(vrtx.get(3), vrtx.get(3)).getValue());
    }

    @Test
    public void primTest(){
        setUp();
        Graph<String> graphS = new Graph<>(false);
        ArrayList<Vertex<String>> vrtx = new ArrayList<>();
        Vertex<String > vr = new Vertex<>("SF");
        vrtx.add(vr);
        graphS.addVertex(vr);
        vr = new Vertex<>("DE");
        vrtx.add(vr);
        graphS.addVertex(vr);
        vr = new Vertex<>("CH");
        vrtx.add(vr);
        graphS.addVertex(vr);
        vr = new Vertex<>("NY");
        vrtx.add(vr);
        graphS.addVertex(vr);
        vr = new Vertex<>("AT");
        vrtx.add(vr);
        graphS.addVertex(vr);
        graphS.addEdge("SF", "CH",1200, "");
        graphS.addEdge("DE", "CH" ,1300, "");
        graphS.addEdge("DE", "AT" ,1400, "");
        graphS.addEdge("DE", "NY" ,1600, "");
        graphS.addEdge("SF", "DE" ,900, "");
        graphS.addEdge("SF", "AT" ,2200, "");
        graphS.addEdge("SF", "NY" ,2000, "");
        graphS.addEdge("CH", "AT" ,700, "");
        graphS.addEdge("CH", "NY" ,1000, "");
        graphS.addEdge("NY", "AT" ,800, "");
        graphS.Prim();
        HashMap<String, Vertex<String>> vertexList = graphS.getVertexList();
        for (Vertex<String> vertex : vertexList.values()) {
            Vertex<String> parent = vertex.getParent();
            if (parent != null) {
                System.out.println(parent.getValue() + " - " + vertex.getValue()+": "+vertex.getWeight(parent));
            }
        }
    }

    @Test
    public void primMatrixTest(){
        setUp2();
        GraphMatrix<String> graphS = new GraphMatrix<>(false);
        ArrayList<Vertex<String>> vrtx = new ArrayList<>();
        graphS.addVertex("SF");
        graphS.addVertex("DE");
        graphS.addVertex("CH");
        graphS.addVertex("NY");
        graphS.addVertex("AT");
        graphS.addEdge("SF", "CH",1200, "");
        graphS.addEdge("DE", "CH" ,1300, "");
        graphS.addEdge("DE", "AT" ,1400, "");
        graphS.addEdge("DE", "NY" ,1600, "");
        graphS.addEdge("SF", "DE" ,900, "");
        graphS.addEdge("SF", "AT" ,2200, "");
        graphS.addEdge("SF", "NY" ,2000, "");
        graphS.addEdge("CH", "AT" ,700, "");
        graphS.addEdge("CH", "NY" ,1000, "");
        graphS.addEdge("NY", "AT" ,800, "");
        graphS.Prim();
        HashMap<String, Vertex<String>> vertexList = graphS.getVertexList();
        for (Vertex<String> vertex : vertexList.values()) {
            Vertex<String> parent = vertex.getParent();
            if (parent != null) {
                System.out.println(parent.getValue() + " - " + vertex.getValue()+": "+vertex.getWeight(parent));
            }else{
                System.out.println("null");
            }
        }
    }

    @Test
    public void KruskalTest(){
        setUp();
        Graph<String> graphS = new Graph<>(false);
        ArrayList<Vertex<String>> vrtx = new ArrayList<>();
        Vertex<String > vr = new Vertex<>("SF");
        vrtx.add(vr);
        graphS.addVertex(vr);
        vr = new Vertex<>("DE");
        vrtx.add(vr);
        graphS.addVertex(vr);
        vr = new Vertex<>("CH");
        vrtx.add(vr);
        graphS.addVertex(vr);
        vr = new Vertex<>("NY");
        vrtx.add(vr);
        graphS.addVertex(vr);
        vr = new Vertex<>("AT");
        vrtx.add(vr);
        graphS.addVertex(vr);
        graphS.addEdge("SF", "CH",1200, "");
        graphS.addEdge("DE", "CH" ,1300, "");
        graphS.addEdge("DE", "AT" ,1400, "");
        graphS.addEdge("DE", "NY" ,1600, "");
        graphS.addEdge("SF", "DE" ,900, "");
        graphS.addEdge("SF", "AT" ,2200, "");
        graphS.addEdge("SF", "NY" ,2000, "");
        graphS.addEdge("CH", "AT" ,700, "");
        graphS.addEdge("CH", "NY" ,1000, "");
        graphS.addEdge("NY", "AT" ,800, "");
        graphS.Prim();
        ArrayList<Pair<Vertex<String>, Edge<String>>> vertexList = graphS.Kruskal();
        for (Pair<Vertex<String>, Edge<String>> vertex : vertexList) {
            System.out.println(vertex.getValue1().getValue() + " -> "+vertex.getValue2().getVertex().getValue()+" "+vertex.getValue2().getWeight());
        }
    }

    @Test
    public void KruskalMatrixTest(){
        setUp();
        Graph<String> graphS = new Graph<>(false);
        ArrayList<Vertex<String>> vrtx = new ArrayList<>();
        Vertex<String > vr = new Vertex<>("SF");
        vrtx.add(vr);
        graphS.addVertex(vr);
        vr = new Vertex<>("DE");
        vrtx.add(vr);
        graphS.addVertex(vr);
        vr = new Vertex<>("CH");
        vrtx.add(vr);
        graphS.addVertex(vr);
        vr = new Vertex<>("NY");
        vrtx.add(vr);
        graphS.addVertex(vr);
        vr = new Vertex<>("AT");
        vrtx.add(vr);
        graphS.addVertex(vr);
        graphS.addEdge("SF", "CH",1200, "");
        graphS.addEdge("DE", "CH" ,1300, "");
        graphS.addEdge("DE", "AT" ,1400, "");
        graphS.addEdge("DE", "NY" ,1600, "");
        graphS.addEdge("SF", "DE" ,900, "");
        graphS.addEdge("SF", "AT" ,2200, "");
        graphS.addEdge("SF", "NY" ,2000, "");
        graphS.addEdge("CH", "AT" ,700, "");
        graphS.addEdge("CH", "NY" ,1000, "");
        graphS.addEdge("NY", "AT" ,800, "");
        graphS.Prim();
        ArrayList<Pair<Vertex<String>, Edge<String>>> vertexList = graphS.Kruskal();
        for (Pair<Vertex<String>, Edge<String>> vertex : vertexList) {
            System.out.println(vertex.getValue1().getValue() + " -> "+vertex.getValue2().getVertex().getValue()+" "+vertex.getValue2().getWeight());
        }
    }

}
