package com.javarush.task.task18.task1817;

/* 
Пробелы
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) {
        FileInputStream file;
        try {
            file = new FileInputStream(args[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int size = 0;
        int spaceCount = 0;
        try {
            size = file.available();
            while (file.available() > 0) {
                int i = file.read();
                if (i == ' ') spaceCount++;
            }
            file.close();
            System.out.println(String.format("%.2f", 100.0 * spaceCount / size));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
