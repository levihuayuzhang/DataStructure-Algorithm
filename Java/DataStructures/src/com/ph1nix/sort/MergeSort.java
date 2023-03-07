package com.ph1nix.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * MergeSort
 *
 * @author Huayu Zhang
 * Create Time: 2023-03-07 16:19:16
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int tmp[] = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, tmp);

        System.out.println(Arrays.toString(arr));


        // larger array test
        int[] arrayLarge = new int[8000000];
        for (int i = 0; i < arrayLarge.length; i++) {
            arrayLarge[i] = (int) (Math.random() * arrayLarge.length);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("Before sort: " + date1Str);

        mergeSort(arrayLarge, 0,  arr.length - 1, tmp);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("After sort: " + date2Str);
    }

    public static void mergeSort (int[] arr, int left, int right, int[] tmp) {
        if (left < right) {
            int mid = (left + right) / 2;
            // left side recursion
            mergeSort(arr, left, mid, tmp);
            // right side recursion
            mergeSort(arr, mid + 1, right, tmp);
            // merge
            merge(arr, left, mid, right, tmp);
        }
    }


    /**
     *
     *
     * @param arr original array
     * @param left index for ordered sequence at left side
     * @param mid index for middle
     * @param right index for ordered sequence at right side
     * @param tmp temporary array
     */
    public static void merge (int[] arr, int left, int mid, int right, int[] tmp) {
        int i = left;
        int j = mid + 1;
        int t = 0; // point to current index of tmp array

        // fill the data with both left side and right side into tmp array
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                tmp[t] = arr[i];
                t += 1;
                i += 1;
            } else {
                tmp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }

        // fill the rest data into tmp by order
        while (i <= mid) { // left side still has remaining ordered sequence
            tmp[t] = arr[i];
            t += 1;
            i += 1;
        }

        while (j <= right) { // right side still has remaining ordered sequence
            tmp[t] = arr[j];
            t += 1;
            j += 1;
        }

        // copy tmp back to arr
        t = 0;
        int tmpLeft = left;
        while (tmpLeft <= right) {
            arr[tmpLeft] = tmp[t];
            t += 1;
            tmpLeft += 1;
        }
    }
}
