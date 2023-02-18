package com.ph1nix.caesar;

/**
 * CaesarDemo
 *
 * @author Huayu Zhang
 * @since 2023-02-18 20:25
 */
public class CaesarDemo {
    public static void main(String[] args) {
        String input = "Hello World";
        // shift 3 bit for the original string
        int key = 3;

        String cipher = encryptCaesar(input);

        String plain = decryptCaesar(cipher, key);
    }

    /**
     * decryption
     *
     * @param cipher cipher text
     * @param key cipher key (how many bit shift to right)
     * @return plain text
     */
    private static String decryptCaesar(String cipher, int key) {
        char[] chars = cipher.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            int b = aChar;
            b -= key;
            char newB = (char) b;
            sb.append(newB);
        }

        System.out.println("Plain text: " + sb.toString());

        return sb.toString();
    }


    /**
     * encryption
     *
     * @param input plain text
     * @return
     */
    private static String encryptCaesar(String input) {
        char[] chars = input.toCharArray();
        StringBuilder sb = new StringBuilder();

        for (char aChar : chars) {
            int b = aChar;
            b = b + 3;

            char newB = (char) b;
            sb.append(newB);

        }
        System.out.println("Cipher text: " + sb.toString());

        return sb.toString();
    }
}
