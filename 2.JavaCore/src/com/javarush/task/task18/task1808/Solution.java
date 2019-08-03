package com.javarush.task.task18.task1808;

/* 
Разделение файла
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
        String fileName3 = r.readLine();
        r.close();

        FileInputStream inFile = new FileInputStream(fileName1);
        FileOutputStream file2 = new FileOutputStream(fileName2);
        FileOutputStream file3 = new FileOutputStream(fileName3);

        if (inFile.available() > 0) {
            byte[] buffer = new byte[inFile.available()];
            int count = inFile.read(buffer);
            int half = count % 2 == 0 ? count / 2 : count / 2 + 1;

            file2.write(buffer,0, half);
            file3.write(buffer, half, count - half);
        }
        inFile.close();
        file2.close();
        file3.close();
    }
}
