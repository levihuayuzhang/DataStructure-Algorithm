package com.ph1nix.binarysearchtree;

import org.w3c.dom.NodeList;

/**
 * binarySearchTree
 *
 * @author Huayu Zhang
 * create time: 2023-04-03 16:04:09
 */
public class BinarySearchTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        for (int i = 0; i < arr.length; i++) {
            binarySearchTree.add(new Node(arr[i]));
        }

        System.out.println("Infix order binary search tree");
        binarySearchTree.infixOrder();

        binarySearchTree.delNode(7);
        System.out.println("After delete, Infix order binary search tree");
        binarySearchTree.infixOrder();
    }

}

class BinarySearchTree {
    private Node root;

    public Node search (int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /**
     * delete the node that have min value
     *
     * @param node the root node of binary search tree
     * @return the min value in the tree that have node as root node
     */
    public int delRightTreeMin (Node node) {
        Node target = node;
        while (target.left != null) {
            target = target.left;
        }
        delNode(target.value);
        return target.value;
    }

    public void delNode (int value) {
        if (root == null) { // empty
            return;
        } else {
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            // only have one root node
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }

            Node parent = searchParent(value);
            if (targetNode.left == null && targetNode.right == null) { // leaf node
                if(parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if(parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) { // delete the node that have two child tree
                int minValue = delRightTreeMin(targetNode.right);
                targetNode.value = minValue;
            } else { // delete the node that only have on child tree
                if (targetNode.left != null) { // target has left child node
                    if(parent.left.value == value) { // target is the left child node of parent
                        parent.left = targetNode.left;
                    } else { // target is the right child node of parent
                        parent.right = targetNode.left;
                    }
                } else { // target has right child node
                    if (parent.left.value == value) {
                        parent.left = targetNode.right;
                    } else {
                        parent.right = targetNode.right;
                    }
                }
            }
        }
    }
    public void add (Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void infixOrder () {
        if (root != null) {
            root.infixOrder();
        } else {
            System.err.println("Tree in empty...");
        }
    }
}


class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString () {
        return "Node [value=" + value + "]";
    }

    public Node search (int value) {
        if (value == this.value) { // find
            return this;
        } else if (value < this.value) { // search left
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * search for the parent node of target node
     *
     * @param value the value of target node
     * @return
     */
    public Node searchParent (int value) {
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {

            return this;
        } else {
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null; // parent node not find
            }
        }
    }

    public void add (Node node) {
        if (node == null) {
            return;
        }

        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    public void infixOrder () {
        if (this.left != null) {
            this.left.infixOrder();
        }

        System.out.println(this);

        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}
