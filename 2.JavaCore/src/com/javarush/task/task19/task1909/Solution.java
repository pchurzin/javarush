package com.javarush.task.task19.task1909;

/* 
Замена знаков
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
            BufferedReader in = new BufferedReader(new FileReader(fn1));
            BufferedWriter out = new BufferedWriter(new FileWriter(fn2));
            while (in.ready()) {
                int c = in.read();
                if (c == '.') c = '!';
                out.write(c);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
