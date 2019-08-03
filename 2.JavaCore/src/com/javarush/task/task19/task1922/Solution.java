package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in;
        String fn;
        try {
            fn = r.readLine();
            r.close();
            in = new BufferedReader(new FileReader(fn));
            while (in.ready()) {
                String line = in.readLine();
                String[] lineWords = line.split("\\s");
                int count = 0;
                for (String word : words) {
                    for (String lineWord : lineWords) {
                        if (word.equals(lineWord)) count++;
                    }
                }
                if (count == 2) {
                    System.out.println(line);
                }
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
