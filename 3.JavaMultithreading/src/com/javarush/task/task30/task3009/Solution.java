package com.javarush.task.task30.task3009;

import java.util.HashSet;
import java.util.Set;

/* 
Палиндром?
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []
    }

    private static Set<Integer> getRadix(String s) {
        Set<Integer> result = new HashSet<>();
        int i;
        try {
            i = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return result;
        }
        for (int radix = 2; radix <= 36; radix++) {
            String val1 = Integer.toString(i, radix);
            StringBuilder sb = new StringBuilder(val1);
            sb.reverse();
            if (val1.equals(sb.toString())) {
                result.add(radix);
            }
        }
        return result;
    }
}