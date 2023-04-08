package com.ph1nix.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Graph
 *
 * @author Huayu Zhang
 * @create: 2023-04-08 03:37:08
 */
public class Graph {
    private ArrayList<String> vertexList;
    private int[][] edges;
    private int numOfEdges;
    private boolean[] isVisited;
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

//        System.out.println("Deep:");
//        graph.dfs();
//        System.out.println();

        System.out.println("Broad:");
        graph.bfs();
    }

    public Graph (int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    /**
     *
     * @param index
     * @return if existed, return corresponding index, if not, return -1
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    // deep first search
    public void dfs(boolean[] isVisited, int i) {
        System.out.printf(getValue(i) + " -> ");
        isVisited[i] = true;

        int w  = getFirstNeighbor(i);
        while(w != -1) {
            if(!isVisited[w]) {
                dfs(isVisited, w);
            }
            w = getNextNeighbor(i, w);
        }
    }

    // overdrive
    public void dfs () {
        for (int i = 0; i < getNumOfVertexes(); i++) {
            if(!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    /**
     * broad first search
     *
     * @param isVisited
     * @param i
     */
    public void bfs (boolean[] isVisited, int i) {
        int u; // index of head node of queue
        int w; // neighbor node
        LinkedList<Integer> queue = new LinkedList<>();
        System.out.printf(getValue(i) + " => ");
        isVisited[i] = true;
        queue.add(i);

        while (!queue.isEmpty()) {
            u = queue.removeFirst();
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.printf(getValue(w) + " => ");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                w = getNextNeighbor(u, w); // broad first
            }
        }
    }

    public void bfs() {
        for (int i = 0; i < getNumOfVertexes(); i++) {
            if(!isVisited[i]){
                bfs(isVisited, i);
            }
        }
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
