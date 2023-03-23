package com.ph1nix.search;

/**
 * BinarySearch
 * Pre-request: the array must be an ordered one
 *
 * @author Huayu Zhang
 * create time: 2023-03-23 16:59:21
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};

//        int resultIndex = binarySearch(arr, 0, arr.length - 1, 1);
        int resultIndex = binarySearch(arr, 0, arr.length - 1, 88); // not found
        System.out.println(resultIndex);
    }

    /**
     *
     * @param arr array
     * @param left left index
     * @param right right index
     * @param target target value
     * @return if found, return target index, if not, return -1
     */
    public static int binarySearch (int[] arr, int left, int right, int target) {

        if (left > right) { // finished recursion search and not found
            return -1;
        }

        int mid = (left + right) / 2;
        int midValue = arr[mid];

        if (target > midValue) { // recursion to right
            return binarySearch(arr, mid + 1, right, target);
        } else if (target < midValue){
            return binarySearch(arr, left, mid - 1, target );
        } else {
            return mid;
        }
    }
}
