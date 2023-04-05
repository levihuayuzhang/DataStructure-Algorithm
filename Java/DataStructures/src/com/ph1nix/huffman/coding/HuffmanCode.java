package com.ph1nix.huffman.coding;

import javax.swing.text.StyledEditorKit;
import java.time.zone.ZoneOffsetTransitionRule;
import java.util.*;

/**
 * HuffmanCode
 * Encoding:
 * 1. create Node (ASCII and their frequencies)
 * 2. ASCII to bytes, and store in List of Nodes
 * 3. List of Nodes to Huffman tree
 *
 * Decoding:
 * 1. transform huffmanByteCode into string in binary format
 * 2. transform binary string by referencing huffman coding into original string
 *
 *
 * @author Huayu Zhang
 * create time: 2023-04-01 17:45:36
 */
public class HuffmanCode {

    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes(); // ASCII to bytes
        System.out.println(contentBytes.length); // 40

        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
        System.out.println(Arrays.toString(huffmanCodeBytes));
        System.out.println(huffmanCodeBytes.length);
        System.out.println("Compression ratio: " + (((contentBytes.length - huffmanCodeBytes.length + 0.00) / contentBytes.length) * 100) + "%");

//        System.out.println(byteToBitString((byte) -1));
        byte[] originalBytes = decode(huffmanCodes, huffmanCodeBytes);
        System.out.println(new String(originalBytes));
    }

    /**
     *
     * @param huffmanCodes the map
     * @param huffmanBytes the byte array
     * @return the corresponding array of original string
     */
    private static byte[] decode (Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            boolean flag = (i == huffmanBytes.length - 1);
            sb.append(byteToBitString(flag, b));
        }

        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry: huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < sb.length();) {
            int count = 1;
            boolean flag = true;
            Byte b = null;

            while (flag) {
                String key = sb.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                }else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * transform a byte into a string in binary format
     *
     * @param flag if true, then need top-up
     * @param b
     * @return
     */
    private static String byteToBitString (boolean flag, byte b) {
        int tmp = b;
        if (flag) {
            tmp |= 256; // cover position (bitwise and)
        }

        String str = Integer.toBinaryString(tmp);

        System.out.println(str);

        if (flag) {
            return str.substring(str.length() - 8); // right least 8 bits
        } else {
            return str;
        }
    }

    /**
     *
     * @param bytes bytes array of corresponding original string
     * @return the zipped array
     */
    private static byte[] huffmanZip (byte[] bytes) {


        List<Node> nodes = getNodes(bytes);
        System.out.println(nodes);

        Node root = createHuffmanTree(nodes); // root node of Huffman tree
//        root.preOrder();

        // test for corresponding Huffman Code
//        System.out.println(getCode(root));
        getCode(root);

//        System.out.println(Arrays.toString()); // length 17, length reduced
        return zip(bytes, huffmanCodes);
    }

    /**
     *
     * @param bytes the corresponding byte[] of original String
     * @param huffmanCodes code map
     * @return byte[]
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(huffmanCodes.get(b));
        }

        int len;
        if (sb.length() % 8 == 0) {
            len = sb.length() / 8;
        } else {
            len = sb.length() / 8 + 1;
        }

        byte[] huffmanCodesBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < sb.length(); i += 8) {
            String strByte;
            if (i + 8 > sb.length()) {
                strByte = sb.substring(i);
            } else {
                strByte = sb.substring(i, i + 8);
            }

            huffmanCodesBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }

        return huffmanCodesBytes;
    }

    private static Map<Byte, String> getCode (Node root) {
        if (root == null) {
            return null;
        }

        getCode(root.left, "0", sb);
        getCode(root.right, "1", sb);

        return huffmanCodes;
    }

    /**
     * 1. put the table of Huffman Codes into a map
     * 2. joint the path, use StringBuilder to store the path of the leaf nodes
     *
     * @param node root node
     * @param code 0 for left child node, 1 for right child node
     * @param sb
     */
    private static void getCode (Node node, String code, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder(sb);
        sb2.append(code);
        if (node != null) {
            if (node.data == null) { // not a leaf node
                getCode(node.left, "0", sb2);
                getCode(node.right, "1", sb2);
            } else {
                huffmanCodes.put(node.data, sb2.toString());
            }
        }
    }

    private static List<Node> getNodes(byte[] bytes) {
        ArrayList<Node> nodes = new ArrayList<>();

        // use map to record frequency (quantity of every char)
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }

        for (Map.Entry<Byte, Integer> entry: counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }

        return nodes;
    }

    private static Node createHuffmanTree (List<Node> nodes) {
        while (nodes.size() > 1) { // only one node left after exiting loop
            Collections.sort(nodes); // sort
            Node leftNode = nodes.get(0); // take out the least tree
            Node rightNode = nodes.get(1);

            // parent node has no data
            // the data all stored in leaf nodes
            Node parent = new Node (null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            nodes.remove(leftNode);
            nodes.remove(rightNode);

            nodes.add(parent);
        }

        return nodes.get(0); // the root node of Huffman tree
    }

}

class Node implements Comparable<Node>{
    Byte data;
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo (Node node) {
        return this.weight - node.weight;
    }

    @Override
    public String toString() {
        return "Node [data = " + data + " weight = " + weight + "]";
    }

    public void preOrder() {
        System.out.println(this);

        if (this.left != null) {
            this.left.preOrder();
        }

        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
