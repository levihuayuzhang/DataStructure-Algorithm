package com.ph1nix.avl;

/**
 * AVLTree
 *
 * @author Huayu Zhang
 * create time: 2023-04-06 12:06:05
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {4, 3, 6, 5, 7, 8};
//        int[] arr = {10, 12, 8, 9, 7, 6};

        int[] arr = {10, 11, 7, 6, 8, 9};

        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length;i++) {
            avlTree.add(new Node(arr[i]));
        }

        System.out.println("Infix order:");
        avlTree.infixOrder();

//        System.out.println("Before rotate:");
        System.out.println("The height of the tree is " + avlTree.getRoot().height());
        System.out.println("The height of the left child tree is " + avlTree.getRoot().leftHeight());
        System.out.println("The height of the right child tree is " + avlTree.getRoot().rightHeight());
        System.out.println("Current root node is: " + avlTree.getRoot());
    }
}

class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

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
                if (targetNode.left != null) {
                    if (targetNode != null) { // target has left child node
                        if(parent.left.value == value) { // target is the left child node of parent
                            parent.left = targetNode.left;
                        } else { // target is the right child node of parent
                            parent.right = targetNode.left;
                        }
                } else {
                        root = targetNode.left;
                    }
                } else { // target has right child node
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
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

    public int leftHeight() {
        if(left == null) {
            return 0;
        }else {
            return left.height();
        }
    }

    public int rightHeight() {
        if(right == null) {
            return 0;
        }else {
            return right.height();
        }
    }

    /**
     *
     * @return the height of current node, the height of the tree that use current node as root
     */
    public int height () {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    private void leftRotate() {
        // new node has the value of current root node
        Node newNode = new Node(value);
        // set left child tree of new node as the left child tree of current node
        newNode.left = left;
        // set right child tree of new node as the left child tree of right child tree of current node
        newNode.right = right.left;
        // set value of current node to the value right child node
        value = right.value;
        // set right child tree of current node to the right child tree of right child tree
        right = right.right;
        // set left child node to the new node
        left = newNode;
    }

    private void rightRotate() {
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
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

        // after adding a node, if (right.height - left.height) > 1, then perform left rotate
        if (rightHeight() - leftHeight() > 1) {
            if (right != null && right.leftHeight() > right.rightHeight()) {
                right.rightRotate();
                leftRotate();
            } else {
                leftRotate();
            }
            return;
        }

        if (leftHeight() - rightHeight() > 1) {
            if (left != null && left.rightHeight() > left.leftHeight()) {
                left.leftRotate();
                rightRotate();
            } else {
                rightRotate();
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