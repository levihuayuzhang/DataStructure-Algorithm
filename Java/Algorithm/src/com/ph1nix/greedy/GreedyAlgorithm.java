package com.ph1nix.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * GreedyAlgorithm
 *
 * @author Huayu Zhang
 * @Create: 2023-04-10 22:14:02
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("Beijing");
        hashSet1.add("Shanghai");
        hashSet1.add("Tianjin");

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("Guangzhou");
        hashSet2.add("Beijing");
        hashSet2.add("Shenzhen");

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("Chengdu");
        hashSet3.add("Shanghai");
        hashSet3.add("Hangzhou");

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("Shanghai");
        hashSet4.add("Tianjin");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("Hangzhou");
        hashSet5.add("Dalian");

        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("Beijing");
        allAreas.add("Shanghai");
        allAreas.add("Tianjin");
        allAreas.add("Guangzhou");
        allAreas.add("Shenzhen");
        allAreas.add("Chengdu");
        allAreas.add("Hangzhou");
        allAreas.add("Dalian");

        ArrayList<String> selects = new ArrayList<>();

        // temporary collection for storing the intersection of the areas that already covered and not yet covered
        HashSet<String> tmpSet = new HashSet<>();

        // maxKey point to the key that can cover max area
        String maxKey = null;

        while(allAreas.size() != 0) {
            maxKey = null;

            for (String key : broadcasts.keySet()) {
                tmpSet.clear();

                HashSet<String> areas = broadcasts.get(key);
                tmpSet.addAll(areas);
                tmpSet.retainAll(allAreas); // intersection of tmpSet and allAreas
                if (tmpSet.size() > 0 && (maxKey == null || tmpSet.size() > broadcasts.get(maxKey).size())) { // Greedy
                    maxKey = key;
                }
            }

            if (maxKey != null) {
                selects.add(maxKey);
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

        System.out.println(selects);
    }
}
