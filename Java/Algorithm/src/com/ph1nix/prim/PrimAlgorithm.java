package com.ph1nix.prim;

import javax.swing.text.html.MinimalHTMLWriter;
import java.util.Arrays;

/**
 * PrimAlgorithm
 *
 * @author Huayu Zhang
 * @Create: 2023-04-11 15:27:25
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertex = data.length;
        int [][]weight=new int[][]{ // 10000 for not connected
            // A, B,  C, D, E, F, G
            {10000, 5, 7, 10000, 10000, 10000, 2}, // A
            {5,10000, 10000, 9, 10000, 10000, 3}, // B
            {7,10000, 10000, 10000, 8, 10000, 10000}, // C
            {10000, 9, 10000, 10000, 10000, 4, 10000}, // D
            {10000, 10000, 8, 10000, 10000, 5, 4}, // E
            {10000, 10000, 10000, 4, 5, 10000,6}, // F
            {2, 3, 10000, 10000, 4,6,10000}}; // G

        MGraph mGraph = new MGraph(vertex);
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph, vertex, data, weight);
        minTree.showGraph(mGraph);

        minTree.prim(mGraph, 0);
    }
}

// minimum spanning tree
class MinTree {
    public void createGraph (MGraph graph, int vertex, char[] data, int[][] weight) {
        int i, j;
        for (i = 0; i < vertex; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < vertex; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     *  prim algorithm for generating minimum spanning tree
     *
     * @param graph
     * @param v generate from the first v vertex
     */
    public void prim (MGraph graph, int v) {
        int[] visited = new int[graph.vertex];
        for (int i = 0; i < graph.vertex; i++) {
            visited[i] = 0; // initialize
        }

        visited[v] = 1;
        // h1 and h2 for recording the index of vertex
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000; // initialize to a large number
        for (int k = 1; k < graph.vertex; k++) {
            // the node that has the shortest distance with each child graph generated
            for (int i = 0; i < graph.vertex; i++) { // i node for visited node
                for (int j = 0; j < graph.vertex; j++) { // j node for the node hasn't visited yet
                    // 1 for visited, 0 for not visited yet
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        minWeight = graph.weight[i][j]; // the minimum weight that between nodes that has visited and not visited
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            System.out.println("Edge < " + graph.data[h1] + ", " + graph.data[h2] + " > weight: " + minWeight);
            visited[h2] = 1; // set to visited
            minWeight = 10000; // reset to large number
        }
    }

}

class MGraph {
    int vertex; // quantity of vertex in graph
    char[] data; // data in node
    int[][] weight; // adjacency matrix (edges)

    public MGraph(int vertex) {
        this.vertex = vertex;
        data = new char[vertex];
        weight = new int[vertex][vertex];
    }


}
