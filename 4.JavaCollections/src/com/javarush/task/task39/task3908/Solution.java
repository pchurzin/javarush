package com.javarush.task.task39.task3908;

import java.util.HashMap;
import java.util.Map;

/*
Возможен ли палиндром?
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isPalindromePermutation("sss"));
        System.out.println(isPalindromePermutation("swsw"));
        System.out.println(isPalindromePermutation(null));
        System.out.println(isPalindromePermutation(""));
        System.out.println(isPalindromePermutation("sws"));
        System.out.println(isPalindromePermutation("dw "));
    }

    public static boolean isPalindromePermutation(String s) {
        if (s == null) {
            return false;
        }
        if (s.isEmpty()) {
            return true;
        }

        Map<Character, Integer> stats = new HashMap<>();
        for (char c : s.toLowerCase().toCharArray()) {
            if (stats.containsKey(c)) {
                stats.put(c, stats.get(c) + 1);
            } else {
                stats.put(c, 1);
            }
        }

        if (s.length() % 2 == 0) {
            for (int i : stats.values()) {
                if (i % 2 != 0) {
                    return false;
                }
            }
            return true;
        } else {
            boolean wasOddNumber = false;
            for (int i : stats.values()) {
                if (i % 2 != 0) {
                    if (wasOddNumber) {
                        return false;
                    }
                    wasOddNumber = true;
                }
            }
            return wasOddNumber;
        }
    }
}
