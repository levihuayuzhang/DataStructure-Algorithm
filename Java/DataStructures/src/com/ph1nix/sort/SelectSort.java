package com.ph1nix.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * SelectSort
 * 1. run for array.length() - 1 times
 * 2. every time of sort would confirm the most first n element
 * 3. time complexity: O(n^2)
 *
 * @author Huayu Zhang
 * @since 2023-02-21
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] array = {101, 34, 119, 1, -1, 90, 123};
        System.out.println("Result:\n" + Arrays.toString(selectSort(array)));

        // larger array test
        int[] arrayLarge = new int[80000];
        for (int i = 0; i < arrayLarge.length; i++) {
            arrayLarge[i] = (int) (Math.random() * arrayLarge.length);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("Before sort: " + date1Str);

        selectSort(arrayLarge);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("After sort: " + date2Str);
    }

    public static int[] selectSort(int[] arr) {
        int startIndex = 0;

        while (startIndex < arr.length - 1) {
            int minIndex = startIndex; // assumed min value in array is at the current startIndex
            int min = arr[minIndex];
            // find the actual min value, start from next element of current startIndex until last element
            for (int i = startIndex + 1; i < arr.length; i++) {
                if ( min > arr[i]) { // min is not actual min, reset min and minIndex
                    min = arr[i];
                    minIndex = i;
                }
            }
            // swap position of min with array[0]
            arr[minIndex] = arr[startIndex];
            arr[startIndex] = min;

//            System.out.printf("%d time(s) sort result: \n" + Arrays.toString(arr) + "\n\n", startIndex + 1);

            startIndex++;
        }
        return arr;
    }
}
