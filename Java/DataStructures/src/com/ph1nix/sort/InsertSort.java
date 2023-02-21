package com.ph1nix.sort;

import java.util.Arrays;

/**
 * InsertSort
 *
 * @author Huayu Zhang
 * @since 2023-02-21
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        insertSort(arr);

    }

    public static void insertSort(int[] arr) {
        int tmp = 1;
        while (tmp < arr.length) {
            int insertValue = arr[tmp];
            int insertValueIndex = tmp - 1;
        /*
            1. insertIndex >= 0 for avoiding index not go out of boundary of array during finding position
            2. insertValue < arr[insertValueIndex for the number waiting for insert hasn't found the insertion location
            3. then move arr[insertValueIndex] backward
         */

            while (insertValueIndex >= 0 && insertValue < arr[insertValueIndex]) {
                arr[insertValueIndex + 1] = arr[insertValueIndex];
                insertValueIndex--;
            }
            // when exit the while loop, the insertion location has been found
            arr[insertValueIndex + 1] = insertValue;
            System.out.println(Arrays.toString(arr));
            tmp++;
        }


    }
}
