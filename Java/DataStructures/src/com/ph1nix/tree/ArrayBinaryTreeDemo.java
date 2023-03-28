package com.ph1nix.tree;

/**
 * ArrayBinaryTreeDemo
 * (Sequence Store of Binary Tree)
 *
 * @author Huayu Zhang
 * create time: 2023-03-29 00:05:12
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder();
    }
}

class ArrayBinaryTree {
    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder() {
        this.preOrder(0);
    }

    public void preOrder (int index) {
        if (arr.length == 0 || arr == null) {
            System.err.println("Array is empty...");
        }
        // to left
        System.out.println(arr[index]);
        if (index * 2 + 1 < arr.length) {
            preOrder(2 * index + 1);
        }
        // to right
        if ((index * 2 + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }
}
