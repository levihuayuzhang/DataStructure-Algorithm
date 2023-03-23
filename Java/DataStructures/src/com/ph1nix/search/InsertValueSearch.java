package com.ph1nix.search;

/**
 * InsertValueSearch
 * Good for evenly distributed data set
 *
 * @author Huayu Zhang
 * create time: 2023-03-23 18:15:20
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }

        int index = insertValueSearch(arr, 0, arr.length - 1, 100);
        System.out.println(index);

    }

    /**
     *
     * @param arr
     * @param left
     * @param right
     * @param target
     * @return
     */
    public static int insertValueSearch (int[] arr, int left, int right, int target) {
        if (left > right || target < arr[0] || target > arr[arr.length - 1]) {
            return -1;
        }

        int mid = left + (right - left) * (target - arr[left]) / (arr[right] - arr[left]);
        int midValue = arr[mid];

        if (target > midValue) {
            return insertValueSearch(arr, mid + 1, right, target);
        } else if (target < midValue) {
            return insertValueSearch(arr, left , mid - 1, target);
        } else {
            return mid;
        }
    }
}
