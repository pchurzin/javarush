package com.javarush.task.task22.task2210;

import java.util.StringTokenizer;

/*
StringTokenizer
*/
public class Solution {
    public static void main(String[] args) {
        String[] words = getTokens("level22.lesson13.task01", ".");
        System.out.println(words);
    }
    public static String [] getTokens(String query, String delimiter) {
        StringTokenizer tokenizer = new StringTokenizer(query, delimiter);
        String[] result = new String[tokenizer.countTokens()];
        int i = 0;
        while (tokenizer.hasMoreTokens()) result[i++] = tokenizer.nextToken();
        return result;
    }
}
