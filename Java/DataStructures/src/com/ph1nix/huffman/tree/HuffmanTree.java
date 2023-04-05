package com.ph1nix.huffman.tree;

import java.util.ArrayList;
import java.util.Collections;

/**
 * HuffmanTree
 *
 * @author Huayu Zhang
 * create time: 2023-03-31 18:51:57
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = {13, 7, 8, 3, 29, 6, 1};
        preOrder(createHuffmanTree(arr));
    }

    public static void preOrder (Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.err.println("Empty tree...");
        }
    }

    public static Node createHuffmanTree (int[] arr) {
        // make every elements in array to a Node
        ArrayList<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1) {
            Collections.sort(nodes);

            // take out the binary tree that has the least weight
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            // make a new binary tree
            Node parent = new Node (leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            // remove the nodes that has been processed from Array List
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

}

class Node implements Comparable<Node> {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString () {
        return "Node [value = " + this.value + "]";
    }

    @Override
    public int compareTo (Node node) {
        // from smaller to larger
        return this.value - node.value;
    }

    public void preOrder () {
        System.out.println(this);

        if (this.left != null) {
            this.left.preOrder();
        }

        if (this.right != null) {
            this.right.preOrder();
        }
    }


}
