package com.javarush.task.task15.task1514;

import java.util.HashMap;
import java.util.Map;

/* 
Статики-1
*/

public class Solution {
    public static Map<Double, String> labels = new HashMap<Double, String>();

    static {
        labels.put(3.14, "pi");
        labels.put(1.18, "sqrt2");
        labels.put(3.56, "gh");
        labels.put(4.789, "lll");
        labels.put(13.14, "pi2");
    }

    public static void main(String[] args) {
        System.out.println(labels);
    }
}
