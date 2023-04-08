package com.ph1nix.dac;

/**
 * TowerOfHanoi
 *
 * @author Huayu Zhang
 * @create: 2023-04-08 21:26:23
 */
public class TowerOfHanoi {
    public static void main(String[] args) {
        TowerOfHanoi(5, 'A', 'B', 'C');
    }

    public static void TowerOfHanoi (int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("1st" + a + " -> " +c);
        } else {
            TowerOfHanoi(num -1, a, c, b);
            System.out.println(num + a + " -> " +c);
            TowerOfHanoi(num -1, b, a, c);
        }
    }

}
