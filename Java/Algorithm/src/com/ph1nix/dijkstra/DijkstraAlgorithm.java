package com.ph1nix.dijkstra;

import java.util.Arrays;

/**
 * DijkstraAlgorithm
 *
 * @author Huayu Zhang
 * @Create: 2023-04-12 05:10:31
 */
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535; // indicate not connected
        matrix[0]=new int[]{N, 5,7, N,N,N, 2};
        matrix[1]=new int[]{5,N,N, 9, N, N, 3};
        matrix[2]=new int[]{7,N,N,N, 8, N,N};
        matrix[3]=new int[]{N,9,N,N,N,4,N} ;
        matrix[4]=new int []{N,N, 8, N,N,5,4};
        matrix[5]=new int []{N,N,N,4,5, N,6};
        matrix[6]=new int []{2, 3, N,N, 4, 6, N};

        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();

        graph.dijkstra(6);
        graph.show();
    }
}

class Graph {
    private char[] vertex;
    private int[][] matrix;
    private VisitedVertex vv;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void showGraph() {
        for(int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    public void dijkstra (int index) {
        vv = new VisitedVertex(vertex.length, index);
        update(index);

        for (int j = 1; j < vertex.length; j++) {
            index = vv.updateArr();
            update(index);
        }
    }

    private void update(int index) {
        int len = 0;
        for (int j = 0; j < matrix[index].length; j++) {
            len = vv.getDis(index) + matrix[index][j];
            if(!vv.in(j) && len < vv.getDis(j)) {
                vv.updatePre(j, index);
                vv.updateDis(j, len);
            }
        }
    }

    public void show() {
        vv.show();
    }

}

class VisitedVertex {
    public int[] already_arr; // 1 for visited, 0 for not yet
    public int[] pre_visited; // index for previous vertex, will update dynamically
    public int[] dis; // record the distance that the vertex to other vertex, shortest will be put into distance

    /**
     *
     * @param length quantity of vertex
     * @param index the index of start vertex (G for 6)
     */
    public VisitedVertex(int length, int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];

        Arrays.fill(dis, 65535);
        this.already_arr[index] = 1;
        this.dis[index] = 0;
    }

    public boolean in(int index) {
        return already_arr[index] == 1;
    }

    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;
    }

    public int getDis(int index) {
        return dis[index];
    }

    public int updateArr() {
        int min = 65535, index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        already_arr[index] = 1;
        return index;
    }

    public void show() {
        System.out.println("==================");

        for (int i : already_arr) {
            System.out.printf(i + " ");
        }
        System.out.println();

        for (int i : pre_visited) {
            System.out.printf(i + " ");
        }
        System.out.println();

        for (int i : dis) {
            System.out.printf(i + " ");
        }
        System.out.println();
    }
}
