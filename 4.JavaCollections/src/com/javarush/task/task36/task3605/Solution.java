package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        TreeSet<Character> characters = new TreeSet<>();
        while (reader.ready()) {
            char ch = (char) reader.read();
            if (Character.isLetter(ch)) {
                characters.add(Character.toLowerCase(ch));
            }
        }
        int count = 0;
        for (Character ch : characters) {
            if (count++ > 4) {
                break;
            }
            System.out.print(ch);
        }
    }
}
