package com.ph1nix.search;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * FibonacciSearch
 *
 * @author Huayu Zhang
 * create time: 2023-03-23 21:05:37
 */
public class FibonacciSearch {

    private static int maxSize = 20;
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println(fibSearch(arr, 1234));
    }

    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;

        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    public static int fibSearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int k = 0; // index of divide point
        int mid = 0;
        int f[] = fib();

        while(high > f[k] - 1) {
            k++;
        }

        int[] tmp = Arrays.copyOf(arr, f[k]);
        for (int i = high + 1; i < tmp.length; i++) {
            tmp[i] = arr[high];
        }

        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (target < tmp[mid]) {
                high = mid - 1;
                k--;
            } else if (target > tmp[mid]) {
                low = mid + 1;
                k -= 2;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }

        return -1;
    }


}
