package com.javarush.task.task19.task1906;

/* 
Четные символы
*/

import java.io.*;

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
            FileReader reader = new FileReader(fn1);
            FileWriter writer = new FileWriter(fn2);

            while (reader.ready()) {
                char[] buf = new char[2];
                if (reader.read(buf) == 2) {
                    writer.write(buf[1]);
                }
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
