package com.ph1nix.tree;

import java.util.Arrays;

/**
 * HeapSort
 *
 * @author Huayu Zhang
 * create time: 2023-03-30 17:28:40
 */
public class HeapSort {
    public static void main(String[] args) {
        int arr[] = {4, 6, 8, 5, 9};
        heapSort(arr);

    }

    public static void heapSort (int[] arr) {
        int tmp;
        System.out.println("Heap Sort:");

        // make a disorder sequence to  a heap (big top for ascending, small top for descending)
        for (int i = arr.length / 2 - 1; i >= 0; i --) {
            adjustHeap(arr, i, arr.length);
        }

        // swap the element at heap top with the element at tail
        // then adjust the structure to fulfill the definition of heap
        for (int j = arr.length - 1; j > 0; j--) {
            tmp = arr[j];
            arr[j] = arr[0];
            arr[0] = tmp;
            adjustHeap(arr, 0, j);
        }

        System.out.println(Arrays.toString(arr));
    }

    /**
     * transform an array (binary tree) in to a big top heap
     *  from left to right, down to up
     *
     * @param arr
     * @param i the index of the node that is node leaf node
     * @param length indicate how many elements to be adjusted (the length is reducing)
     */
    public static void adjustHeap (int arr[], int i, int length) {
        int tmp = arr[i];
        /*
        1. k = i * 2 + 1 is the left child node of node i

         */
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) { // left child node is less than right child node
                k++;
            }
            if (arr[k] > tmp) { // child node is greater than parent node
                arr[i] = arr[k];
                i = k; // i point to k, keep comparing
            } else {
                break;
            }
        }
        // after the for loop, the most big value of the tree which has i as the root node,
        // already been placed at the top (of this tree) (partial tree of the whole binary tree)
        arr[i] = tmp;
    }
}
