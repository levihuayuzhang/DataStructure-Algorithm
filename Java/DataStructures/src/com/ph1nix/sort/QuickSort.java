package com.ph1nix.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * QuickSort
 *
 * @author Huayu Zhang
 * Create Time: 2023-03-06 23:14:42
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70, -1, 900, 4561};

        quickSort(arr, 0, arr.length - 1);
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

        quickSort(arrayLarge, 0, arrayLarge.length - 1);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("After sort: " + date2Str);
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2 ];
        int tmp; // for swapping
        // if element is less than pivot, put to left; if larger, put to right
        while ( l < r) {
            // keep finding at left of pivot
            while (arr[l] < pivot) {
                l += 1;
            }

            while (arr[r] > pivot) {
                r -= 1;
            }

            // left is all less or equal to pivot
            // right is all larger or equal to pivot
            if (l >= r) {
                break;
            }

            // swap
            tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;

            // after swapping, if arr[l] is equal to pivot, then move to previous location
            if (arr[l] == pivot) {
                r -= 1;
            }

            // after swapping, if arr[lr is equal to pivot, then move to next location
            if (arr[r] == pivot) {
                l += 1;
            }
        }

        // during recursion do following, otherwise, stack overflow
        if (l == r) {
            l += 1;
            r -= 1;
        }

        // recursion for left side of pivot
        if (left < r) {
            quickSort(arr, left, r);
        }

        // recursion for right side of pivot
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}


