package com.ph1nix.recursion;

/**
 * Maze
 * help a little ball find route out in a maze using recursion
 *
 * @author Huayu Zhang
 * @since 2023-02-20-8:59 AM
 */
public class Maze {
    public static void main(String[] args) {
        int[][] map = new int[8][7];

        // let most up and down row set to 1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        // set up the bazel
        map[3][1] = 1;
        map[3][2] = 1;

        System.out.println("Print out the map: ");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        setRoute(map, 1, 1);
        System.out.println("The route on the map: ");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * use recursion backtrack to find route for the little ball.
     * 1. if the ball can reach to [6][5], that means the route is passed.
     * 2. if map[i][j] is 0, means this point hasn't been reached to (1 for wall (bazel)), 2 is the path can be reached,
     *      3 for the point that already reached to but not work.
     * 3. strategic: try down -> right -> up -> down, if not work, then backtrack (this would affect the route)
     *
     * @param map for the map array
     * @param i start row
     * @param j start column
     * @return whether the ball can get out from the maze
     */
    public static boolean setRoute (int[][] map, int i, int j) {
        if(map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) { // the point hasn't been reached to
                map[i][j] = 2; // assume this point work
                if (setRoute(map, i+1, j)) { // try to go down
                    return true;
                } else if (setRoute(map, i, j + 1)) { // try to go right
                    return true;
                } else if (setRoute(map, i -1, j)) { // try to go up
                    return true;
                } else if (setRoute(map, i, j - 1)) { // try to go left
                    return true;
                } else {
                    map[i][j] = 3; // dead route
                    return false;
                }
            } else { // map[i][j] != 0 (could be 1, 2, 3)
                return false;
            }
        }
    }
}

