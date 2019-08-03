package com.javarush.task.task19.task1926;

/* 
Перевертыши
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
            String fn = r.readLine();
            r.close();
            BufferedReader in = new BufferedReader(new FileReader(fn));
            while (in.ready()) {
                StringBuilder sb = new StringBuilder(in.readLine());
                sb.reverse();
                System.out.println(sb);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
