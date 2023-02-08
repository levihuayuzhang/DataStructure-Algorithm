package com.ph1nix.sparsearray;

// can compress the data if the data contain a lot of 0 value

public class SparseArray {
    public static void main(String[] args) {
        // create an original 2D array of 11 * 11
        // 0 for no pieces, 1 for black pieces, 2 for blue pieces
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;

        // export the original 2D array
        System.out.println("\nThe original 2D array:\n");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        /* Original 2D array to sparse array:
         *  1. ergodic the 2D array get the value of number that not equal to 0
         */
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println("Sum = " + sum);

        // create sparse array
        int[][] sparseArr = new int [sum + 1][3];
        // assign value for sparse array (1st line) (row, column and quantity of valid numbers (not equal to 0))
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        // ergodic 2 dimensional array, and put non 0 data into sparse array
        int count = 0; // for recording the first few non 0 data
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j ++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        // print out sparse array
        System.out.println("\nThe Sparse Array is: \n");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }

        /* Recover the original array from sparse array:
            1. read the first row of sparse array to create the original array
            2. then read the data in remaining rows, then assign them to original 2d array
         */

        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];

        for (int i = 1; i < sparseArr.length; i ++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        // print out recovered array
        System.out.println("\nThe Recovered Array is: \n");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }


    }
}
