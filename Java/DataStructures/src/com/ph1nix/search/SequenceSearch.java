package com.ph1nix.search;

/**
 * SequenceSearch
 *
 * @author Huayu Zhang
 * create time: 2023-03-22 17:31:05
 */
public class SequenceSearch {
    public static void main(String[] args) {
        int arr[] = {1, 9, 11, -1, 34, 89};
        int index = seqSearch(arr, 11);
        if (index == -1) {
            System.out.println("Not found...");
        } else {
            System.out.println("Found at " + index);
        }
    }

    /**
     * linear search for a specific value, and return it if exist
     *
     *
     * @param arr
     * @param target
     * @return
     */
    public static int seqSearch (int[] arr, int target) {
        for (int i =0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
