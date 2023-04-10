package com.ph1nix.kmp;

import java.util.Arrays;

/**
 * KMPAlgorithm
 *
 * @author Huayu Zhang
 * @Create: 2023-04-10 11:11:47
 */
public class KMPAlgorithm {
    public static void main(String[] args) {

        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
//        String str2 = "BBC";

        int[] next = kmpNext(str2);
        System.out.println(Arrays.toString(next));

        int index = kmpSearch(str1, str2, next);
        System.out.println("Index: " + index);
    }

    /**
     *
     * @param str1 original string
     * @param str2 target string
     * @param next partial matching table (corresponding to target string)
     * @return -1 for not found, otherwise return the first matched position
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        for(int i = 0, j = 0; i < str1.length(); i++) {

            while(j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j-1];
            }

            if(str1.charAt(i) == str2.charAt(j)) {
                j++;
            }

            if(j == str2.length()) { // found
                return i - j + 1;
            }
        }
        return -1;
    }

    public static int[] kmpNext(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0;
        for(int i = 1, j = 0; i < dest.length(); i++) {
            while(j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }

            if(dest.charAt(i) == dest.charAt(j)) { // partial matched
                j++;
            }
            next[i] = j;
        }
        return next;
    }

}
