package com.javarush.task.task39.task3909;

import java.util.HashMap;
import java.util.Map;

/*
Одно изменение
*/
public class Solution {
    public static void main(String[] args) {
        /*System.out.println(isOneEditAway("", "a"));
        System.out.println(isOneEditAway("abcde", "abde"));
        System.out.println(isOneEditAway("bcde", "abcde"));
        System.out.println(isOneEditAway("bcdef", "abcde"));
        System.out.println(isOneEditAway("a", "b"));
        System.out.println(isOneEditAway("aa", "aa"));
        System.out.println(isOneEditAway("abc", "a b"));
*/
        Map<Pair<String>, Boolean> tests = new HashMap<>();
        tests.put(new Pair<>("", "a"), true);
        tests.put(new Pair<>(null, null), false);
        tests.put(new Pair<>("abcde", "abde"), true);
        tests.put(new Pair<>("abc", "a c"), true);
        tests.put(new Pair<>("abc", "a b"), false);
        tests.put(new Pair<>("abc", "cdef"), false);


        for (Map.Entry<Pair<String>, Boolean> test : tests.entrySet()) {
            String s1 = test.getKey().x;
            String s2 = test.getKey().y;
            if (test.getValue() != isOneEditAway(s1, s2)) {
                System.out.println("Test failed:'" + s1 + "' '" + s2 + "'");
            }
        }
    }

    public static boolean isOneEditAway(String first, String second) {
        if (first == null || second == null) {
            return false;
        }

        if (Math.abs(first.length() - second.length()) > 1) {
            return false;
        }

        if (first.equals(second)) {
            return true;
        }

        if (first.length() == second.length()) {
            boolean wasChanged = false;
            for (int i = 0; i < first.length(); i++) {
                if (first.charAt(i) != second.charAt(i)) {
                    if (wasChanged) {
                        return false;
                    }
                    wasChanged = true;
                }
            }
            return wasChanged;
        } else {
            String shorter = first.length() < second.length() ? first : second;
            String longer = shorter == first ? second : first;
            int i = 0;
            int j = 0;
            boolean wasDeletion = false;
            while (i < shorter.length()) {
                if (shorter.charAt(i) != longer.charAt(j)) {
                    if (wasDeletion) {
                        return false;
                    }
                    wasDeletion = true;
                    j++;
                    continue;
                }
                i++;
                j++;
            }
            return true;
        }
    }

    private static class Pair<T> {
        T x;
        T y;

        public Pair(T x, T y) {
            this.x = x;
            this.y = y;
        }
    }
}
