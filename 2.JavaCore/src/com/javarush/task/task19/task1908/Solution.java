package com.javarush.task.task19.task1908;

/* 
Выделяем числа
*/

import java.io.*;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String fn1, fn2;
        try {
            fn1 = r.readLine();
            fn2 = r.readLine();
            r.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try {
            BufferedReader in = new BufferedReader(new FileReader(fn1));
            BufferedWriter out = new BufferedWriter(new FileWriter(fn2));
            while (in.ready()) {
                String line = in.readLine();
                String[] words = line.split("\\s");
                for (String word : words) {
                    if (Pattern.matches("\\d+", word)) {
                        out.write(word + " ");
                    }
                }
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
