package com.javarush.task.task18.task1807;

/* 
Подсчет запятых
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String fileName = r.readLine();
        r.close();

        FileInputStream file = new FileInputStream(fileName);

        int count = 0;
        while (file.available() > 0) {
            if (file.read() == ',') count++;
        }
        file.close();
        System.out.println(count);
    }
}
