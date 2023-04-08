package com.ph1nix.binarysearchwithoutrecursion;

/**
 * BinarySearchWithoutRecursion
 *
 * @author: Huayu Zhang
 * @create: 2023-04-08 21:04:01
 */
public class BinarySearchWithoutRecursion {
    public static void main(String[] args) {
        int arr[] = {1, 3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr, 1);
        System.out.println("index=" + index);
    }

    /**
     *
     * @param arr ascending array
     * @param target target number
     * @return corresponding index, -1 indicate not found
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1; // left
            } else {
                left  = mid + 1; //right
            }
        }
        return -1;
    }
}
