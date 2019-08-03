package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String fn;
        try {
            fn = r.readLine();
            r.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }


        int count = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fn));
            while (reader.ready()) {
                String line = reader.readLine();
                String[] words = line.split("[^\\w]");
                for (String word : words) {
                    if ("world".equals(word)) count++;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(count);
    }
}
