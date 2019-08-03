package com.javarush.task.task18.task1818;

/* 
Два в одном
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String fn1, fn2, fn3;
        try {
            fn1 = r.readLine();
            fn2 = r.readLine();
            fn3 = r.readLine();
            r.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try {
            FileInputStream in1 = new FileInputStream(fn2);
            FileInputStream in2 = new FileInputStream(fn3);
            FileOutputStream out = new FileOutputStream(fn1);
            while (in1.available() > 0) {
                out.write(in1.read());
            }
            in1.close();
            while(in2.available() > 0) {
                out.write(in2.read());
            }
            in2.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
