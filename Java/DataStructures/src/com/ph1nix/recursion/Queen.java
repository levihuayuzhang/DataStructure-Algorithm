package com.ph1nix.recursion;

/**
 * Queen (Eight Queens)
 *
 * @author Huayu Zhang
 * @since 2023-02-20-10:50 AM
 */
public class Queen {
    // array size (indicate how many queens lay in the checkerboard)
    private final int max  = 8;
    // array for holding the position of queens in the checkerboard (index is the queen and also the row index in 2d array (the checkerboard))
    private int[] array = new int[max];
    static int count = 0;
    public static void main(String[] args) {
        Queen queen8 = new Queen();
        queen8.check(0);

        System.out.printf("\n%d kinds of solutions!", count);
    }

    /**
     * every check would have the for loop, thus there would be backtracking
     *
     * @param n the n th queen
     */

    private void check (int n) {
        if (n == max) {
            print();
            return;
        }

        for (int i = 0; i < max; i++) {
            // first, put the n th queen at 1 st column of current row
            array[n] = i;
            if (judge(n)) {
                check(n + 1);
            }
            //  if conflict, then array[n] = i (n th queen put at the last position of current row)
        }

    }


    /**
     * when putting `n`th queen, judge whether conflict with the previous queen
     * 
     * @param n the n th queen
     * @return whether conflict
     */
    private boolean judge (int n) {
        for (int i = 0; i < n; i++) {
            /*1. array[i] == array[n] for if in same column
            * 2. Math.abs(n - i) == Math.abs(array[n] - array[i]) for if in same slash
            *   e.g. n = 1 (2nd column) and array[1] = 1
            *   Math.abs(n - i) -> Math.abs(1 - 0)
            *   Math.abs(array[n] - array[i]) -> Math.abs(array[1] - array[0]) -> Math.abs(1 - 0)
            * 3. whether in same row (not necessary, because every queen is designed to lay in different row by index)
            * */

            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }
    

    private void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "  ");
        }
        System.out.println();
        count++;
    }
}
