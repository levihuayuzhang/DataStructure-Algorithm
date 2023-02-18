package com.ph1nix.ascii;

/**
 * @author Huayu Zhang
 * @since 2023-02-18 20:14
 */
public class AsciiDemo {
    public static void main(String[] args) {
//        char a = 'A';
//        int b = a;
//        System.out.println(b);

        String a = "AaZ";
        char[] chars = a.toCharArray();
        for (char aChar : chars) {
            int asciiCode = aChar;
            System.out.println(asciiCode);
        }
        
    }
}
