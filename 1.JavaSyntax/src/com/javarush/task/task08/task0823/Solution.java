package com.javarush.task.task08.task0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Омовение Рамы
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();

        //напишите тут ваш код
        String[] words = s.trim().split("\\s+");
        for (String word : words) {
            System.out.print(word.substring(0, 1).toUpperCase() + word.substring(1) + " ");
        }
    }
}
