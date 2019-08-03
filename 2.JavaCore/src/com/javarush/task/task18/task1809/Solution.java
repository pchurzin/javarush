package com.javarush.task.task18.task1809;

/* 
Реверс файла
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = r.readLine();
        String fileName2 = r.readLine();
        r.close();

        FileInputStream file1 = new FileInputStream(fileName1);
        FileOutputStream file2 = new FileOutputStream(fileName2);

        byte[] buffer = new byte[file1.available()];
        if (file1.available() > 0) {
            file1.read(buffer);
            for (int i = buffer.length - 1; i >= 0; i--) {
                file2.write(buffer[i]);
            }
        }
        file1.close();
        file2.close();
    }
}
