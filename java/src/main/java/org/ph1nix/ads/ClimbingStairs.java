package org.ph1nix.ads;

import java.util.*;

/**
 * Description: dp or loop
 *
 * LeetCode.70
 *
 * input: 3, return: 3
 * input: 15, return: 987
 *
 * @name ClimbingStairs
 * @author Huayu Zhang
 * @date 2023-08-26 12:47
 * @since 1.0.0
 */
public class ClimbingStairs {
    private static Map<Integer, Integer> map = new HashMap<>(); // use hash map to store the repeated calculation result

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        System.out.println(climbStairsDP(n));
    }

    // Using dynamic programming
    private static int climbStairsDP(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (null != map.get(n)) { // check if the resul is already calculated
            return map.get(n);
        } else {
            int result = climbStairsDP(n - 1) + climbStairsDP( n - 2);
            map.put(n, result);
            return result;
        }
    }
}
