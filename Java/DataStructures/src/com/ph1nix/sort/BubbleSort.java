package com.ph1nix.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * BubbleSort
 * 1. sort from the lowest index
 * 2. originally would sort for array.length() - 1 times (every time would set the right position of the most last n element in array)
 * 3. optimization: stop the sorting in advance while any sort that doesn't occur any swap
 * 4. time complexity is O(n^2)
 *
 * @author Huayu Zhang
 * @since 2023-02-21-6:52 AM
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, -2};

        System.out.println("Result is: " + Arrays.toString(bubbleSort(arr)));

        // larger array test
        int[] arrayLarge = new int[80000];
        for (int i = 0; i < arrayLarge.length; i++) {
            arrayLarge[i] = (int) (Math.random() * arrayLarge.length);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("Before sort: " + date1Str);

        bubbleSort(arrayLarge);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("After sort: " + date2Str);
    }

    private static int[] bubbleSort(int[] arr) {
        int tmp;
        int n = 1;
        boolean flag = false; // indicate whether current sort has swap action
        while (n < arr.length - 1) {
            for (int i = 0; i < arr.length - n; i++) {
                if (arr[i] > arr[i + 1]) { // swap
                    flag = true;
                    tmp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = tmp;
                }
            }
//            System.out.println(Arrays.toString(arr));

            if (!flag) { // false (has swap), stop in advance
                break;
            } else { // true
                flag = false; // reset flag
            }

            n++;
        }

        return arr;
    }
}


