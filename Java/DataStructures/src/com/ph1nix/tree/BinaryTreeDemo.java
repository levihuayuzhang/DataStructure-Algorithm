package com.ph1nix.tree;

/**
 * BinaryTree
 *
 * @author Huayu Zhang
 * create time: 2023-03-27 15:00:47
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        Node root = new Node(1, "ass");
        Node node2 = new Node(2, "shit");
        Node node3 = new Node(3, "damn");
        Node node4 = new Node(4, "holy");
        Node node5 = new Node(5, "wow");

        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        System.out.println("Pre order:");
        binaryTree.preOrder();

        System.out.println("Infix order:");
        binaryTree.infixOrder();

        System.out.println("Post order:");
        binaryTree.postOrder();

        System.out.println("Pre-Order search:");
        Node resultNode = binaryTree.preOrderSearch(5);
        if (resultNode != null) {
            System.out.printf("Found! no = %d, name = %s\n", resultNode.getNo(), resultNode.getName());
        } else {
            System.err.println("Not found!");
        }

        System.out.println("Infix-Order search:");
        Node resultNode1 = binaryTree.infixOrderSearch(5);
        if (resultNode1 != null) {
            System.out.printf("Found! no = %d, name = %s\n", resultNode1.getNo(), resultNode1.getName());
        } else {
            System.err.println("Not found!");
        }

        System.out.println("Post-Order search:");
        Node resultNode2 = binaryTree.postOrderSearch(5);
        if (resultNode2 != null) {
            System.out.printf("Found! no = %d, name = %s\n", resultNode2.getNo(), resultNode2.getName());
        } else {
            System.err.println("Not found!");
        }

    }
}

class BinaryTree {
    private Node root;
    public void setRoot (Node root) {
        this.root = root;
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
}

class Node {
    private int no;
    private String name;
    private Node left;
    private Node right;

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
