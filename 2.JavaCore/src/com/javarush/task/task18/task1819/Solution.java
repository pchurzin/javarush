package com.javarush.task.task18.task1819;

/* 
Объединение файлов
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String fn1, fn2;
        try {
            fn1 = r.readLine();
            fn2 = r.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        FileInputStream in1, in2;
        FileOutputStream out;

        try {
            in1 = new FileInputStream(fn1);
            in2 = new FileInputStream(fn2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        try {
            int length = in1.available();
            byte[] buffer = new byte[length];
            in1.read(buffer);
            in1.close();
            out = new FileOutputStream(fn1);
            while (in2.available() > 0) {
                out.write(in2.read());
            }
            out.write(buffer);
            in2.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
