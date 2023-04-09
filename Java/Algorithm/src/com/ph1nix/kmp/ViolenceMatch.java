package com.ph1nix.kmp;

/**
 * ViolenceMatch
 *
 * @author Huayu Zhang
 * @create: 2023-04-09 20:19:33
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "aa 的打卡机 九分裤 aaA 的打卡机 发觉对 方克拉 九分裤";
        String str2 = "的打卡机 发觉对 方克拉 九分裤";

        int index = violenceMatch(str1, str2);
        System.out.println(index);
    }

    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        int i = 0; // index point to s1
        int j = 0; // index point to s2

        while (i < s1Len && j < s2Len) {
            if(s1[i] == s2[j]) { // matched
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }

        if(j == s2Len) {
            return i - j;
        } else {
            return -1;
        }
    }
}
