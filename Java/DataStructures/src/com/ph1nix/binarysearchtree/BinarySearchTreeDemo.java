package com.ph1nix.binarysearchtree;

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

        binarySearchTree.delete(2);
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

    public void delete (int value) {
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
