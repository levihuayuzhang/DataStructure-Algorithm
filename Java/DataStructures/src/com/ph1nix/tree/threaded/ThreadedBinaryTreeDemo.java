package com.ph1nix.tree.threaded;

/**
 * ThreadedBinaryTreeDemo
 *
 * @author Huayu Zhang
 * create time: 2023-03-29 08:54:29
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        Node root = new Node(1, "tom");
        Node node2 = new Node(3, "jack");
        Node node3 = new Node(6, "smith");
        Node node4 = new Node(8, "mary");
        Node node5 = new Node(10, "king");
        Node node6 = new Node(14, "dim");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes(root);

        Node leftNode = node5.getLeft();
        Node rightNode = node5.getRight();

        System.out.println(leftNode);
        System.out.println(rightNode);

        threadedBinaryTree.threadedList();

    }


}

class ThreadedBinaryTree {
    private Node root;
    private Node pre = null; // point to precursor node
    public void setRoot (Node root) {
        this.root = root;
    }

    public void threadNodes() {
        this.threadedNodes(root);
    }

    public void threadedNodes (Node node) {
        if (node == null) {
            return;
        }

        threadedNodes(node.getLeft());

        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }

        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }

        pre = node;


        threadedNodes(node.getRight());
    }

    public void threadedList () {
        Node node = root;
        while (node != null) {
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.println(node);

            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            node  = node.getRight();
        }
    }

    public void preOrder () {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.err.println("BST is empty...");
        }
    }

    public void infixOrder () {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.err.println("BST is empty...");
        }
    }
    public void postOrder () {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.err.println("BST is empty...");
        }
    }

    public Node preOrderSearch (int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    public Node infixOrderSearch (int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    public Node postOrderSearch (int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    public void delNode (int no) {
        if (root != null) {
            if (root.getNo() == no) {
                root = null;
            } else {
                root.delNode(no);
            }
        } else {
            System.err.println("Empty tree...");
        }
    }
}

class Node {
    private int no;
    private String name;

    private Node left;
    private Node right;

    private int leftType; // if leftType == 0, point to left child tree, if 1, point to precursor node
    private int rightType; // if rightType == 0, point to right child tree, if 1, point to successor node

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public void delNode (int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }

        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }

        if (this.left != null) {
            this.left.delNode(no);
        }

        if (this.right != null) {
            this.right.delNode(no);
        }

    }

    public void preOrder () {
        System.out.println(this); // parent node
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public void infixOrder () {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this); // parent node
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    public void postOrder () {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this); // parent node
    }

    public Node preOrderSearch (int no) {
        // if current node matches
        if (this.no == no) {
            return this;
        }

        Node resultNode = null;

        if (this.left != null) {
            resultNode = this.left.preOrderSearch(no);
        }
        if (resultNode != null) {
            return resultNode;
        }

        if (this.right != null) {
            resultNode = this.right.preOrderSearch(no);
        }

        return resultNode;
    }

    public Node infixOrderSearch (int no) {

        Node resultNode = null;

        if (this.left != null) {
            resultNode = this.left.infixOrderSearch(no);
        }
        if (resultNode != null) {
            return resultNode;
        }

        if (this.no == no) {
            return this;
        }

        if (this.right != null) {
            resultNode = this.right.infixOrderSearch(no);
        }

        return resultNode;
    }

    public Node postOrderSearch (int no) {

        Node resultNode = null;

        if (this.left != null) {
            resultNode = this.left.infixOrderSearch(no);
        }
        if (resultNode != null) {
            return resultNode;
        }

        if (this.right != null) {
            resultNode = this.right.infixOrderSearch(no);
        }
        if (resultNode != null) {
            return resultNode;
        }

        if (this.no == no) {
            return this;
        }

        return resultNode;
    }
}
