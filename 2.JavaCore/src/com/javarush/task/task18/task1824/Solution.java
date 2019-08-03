package com.javarush.task.task18.task1824;

/* 
Файлы и исключения
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String fn;
            try {
                fn = r.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            if (fn.isEmpty()) break;

            FileInputStream in;
            try {
                in = new FileInputStream(fn);
                in.close();
            } catch (FileNotFoundException e) {
                System.out.println(fn);
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
