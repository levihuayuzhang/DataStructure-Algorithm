package com.ph1nix.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * ShellSort
 *
 * @author Huayu Zhang
 * Create Time: 2023-02-26 21:27:26
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        // arr sort result
//        System.out.println(Arrays.toString(ShellSortSwap(arr)));
        System.out.println(Arrays.toString(ShellSortShift(arr)));

        // large array test
        int[] arrayLarge = new int[80000];
        for (int i = 0; i < arrayLarge.length; i++) {
            arrayLarge[i] = (int) (Math.random() * arrayLarge.length);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("Before sort: " + date1Str);

//        ShellSortSwap(arrayLarge);
        ShellSortShift(arrayLarge);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("After sort: " + date2Str);
    }

    // using swap
    public static int[] ShellSortSwap(int[] arr) {

        int tmp;

        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) { // length of step is gap
                    if (arr[j] > arr[j + gap]) { // need to be swapped
                        tmp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = tmp;
                    }
                }
            }
        }
        return arr;
    }

    // translocation
    public static int[] ShellSortShift(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j =i;
                int tmp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j -gap >= 0 && tmp < arr[j -gap]) {
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    arr[j] = tmp;
                }
            }
        }
        return arr;
    }
}
