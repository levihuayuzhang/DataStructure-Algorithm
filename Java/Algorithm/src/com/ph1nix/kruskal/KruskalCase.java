package com.ph1nix.kruskal;

import java.util.Arrays;
import java.util.WeakHashMap;

/**
 * KruskalCase
 *
 * @author Huayu Zhang
 * @Create: 2023-04-11 16:49:19
 */
public class KruskalCase {
    private int edgeNum;
    private char[] vertex;
    private int[][] matrix;
    private static final int INF = Integer.MAX_VALUE; // indicate that two nodes are not connected
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int matrix[][] = {
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0}
        };

        KruskalCase kruskalCase = new KruskalCase(vertex, matrix);

        kruskalCase.print();

        EData[] edges = kruskalCase.getEdges();

        System.out.println(Arrays.toString(edges));
//        kruskalCase.sortEdges(edges);
//        System.out.println(Arrays.toString(edges));
        kruskalCase.kruskal();
    }

    public KruskalCase(char[] vertex, int[][] matrix) {
        int vlen = vertex.length;

        // use copy to initialize
        this.vertex = new char[vlen];
        for (int i = 0; i < vertex.length; i++) {
            this.vertex[i] = vertex[i];
        }

        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        // count the quantity of edges
        for (int i = 0; i < vlen; i++) {
            for (int j = i + 1; j < vlen; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    public void kruskal() {
        int index = 0; // index for result array
        int[] ends = new int[edgeNum]; // for storing end nodes of each vertex in the existing minimum spanning tree
        EData[] results = new EData[edgeNum];
        EData[] edges = getEdges();

        sortEdges(edges);

        for(int i = 0; i < edgeNum; i++) {
            int p1 = getPosition(edges[i].start);
            int p2 = getPosition(edges[i].end);

            int m = getEnd(ends, p1);
            int n = getEnd(ends, p2);

            if (m != n) { // not looping
                ends[m] = n;
                results[index++] = edges[i];
            }
        }

        System.out.println(Arrays.toString(results));
    }

    public void print() {
        System.out.println("Adjacency Matrix:");
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.printf("%12d", this.matrix[i][j]);
            }
            System.out.println();
        }
    }

    // bubble sort
    private void sortEdges(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j+1].weight) { // swap
                    EData tmp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = tmp;
                }
            }
        }
    }

    /**
     *
     * @param ch value of vertex
     * @return the index of ch, if not found return -1
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vertex.length; i ++) {
            if (vertex[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * get edges from graph
     * @return
     */
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i + 1; j < vertex.length; j++) {
                if(matrix[i][j] != INF) {
                    edges[index++] = new EData(vertex[i], vertex[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * get the end node for the node that has i as index
     * for judging whether the later two nodes has same end node
     *
     * @param ends record the end nodes of each vertex
     * @param i the corresponding index of vertex
     * @return the index of end node of vertex that has i as index
     */
    private int getEnd(int[] ends, int i) {
        while(ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }
}

// Edge data
class EData {
    char start;
    char end;
    int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}
