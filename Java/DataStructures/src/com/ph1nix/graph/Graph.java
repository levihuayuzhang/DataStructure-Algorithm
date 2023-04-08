package com.ph1nix.graph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Graph
 *
 * @author Huayu Zhang
 * create time: 2023-04-08 03:37:08
 */
public class Graph {
    private ArrayList<String> vertexList;
    private int[][] edges;
    private int numOfEdges;
    public static void main(String[] args) {
        int n = 5;
        String[] vertexValue = {"A", "B", "C" , "D", "E"};
        Graph graph = new Graph(n);

        for (String value : vertexValue) {
            graph.insertVertex(value);
        }
        graph.insertEdges(0, 1, 1);
        graph.insertEdges(0, 2, 1);
        graph.insertEdges(1, 2, 1);
        graph.insertEdges(1, 3, 1);
        graph.insertEdges(1, 4, 1);
        graph.showGraph();
    }

    public Graph (int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
    }

    public void showGraph () {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    private int getNumOfVertexes() {
        return vertexList.size();
    }

    private int getNumOfEdges() {
        return this.numOfEdges;
    }

    private String getValue (int i) {
        return vertexList.get(i);
    }

    private int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    private void insertVertex (String vertex) {
        vertexList.add(vertex);
    }

    /**
     *
     * @param v1 index of vertex 1
     * @param v2 index of vertex 2
     * @param weight whether there has direct connection between two vertexes
     */
    private void insertEdges (int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
