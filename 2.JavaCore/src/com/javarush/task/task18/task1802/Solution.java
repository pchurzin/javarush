package com.javarush.task.task18.task1802;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Минимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String fileName = r.readLine();

        FileInputStream file = new FileInputStream(fileName);

        int min = 255;
        while (file.available() > 0) {
            int i = file.read();
            if (i < min) min = i;
        }
        System.out.println(min);
        file.close();
    }
}
