package com.ph1nix.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * InsertSort
 *
 * @author Huayu Zhang
 * @since 2023-02-21
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};

        // larger array test
        int[] arrayLarge = new int[80000];
        for (int i = 0; i < arrayLarge.length; i++) {
            arrayLarge[i] = (int) (Math.random() * arrayLarge.length);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("Before sort: " + date1Str);

        System.out.println(Arrays.toString(insertSort(arr)));

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("After sort: " + date2Str);

    }

    public static int[] insertSort(int[] arr) {
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
            if (insertValueIndex + 1!= tmp) {
                arr[insertValueIndex + 1] = insertValue;
            }
//            System.out.println(Arrays.toString(arr));
            tmp++;
        }

        return arr;

    }
}
