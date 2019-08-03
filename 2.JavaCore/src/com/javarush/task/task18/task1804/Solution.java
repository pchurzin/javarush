package com.javarush.task.task18.task1804;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/* 
Самые редкие байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String fileName = r.readLine();
        r.close();

        int[] list = new int[256];
        Arrays.fill(list, 0);


        FileInputStream file = new FileInputStream(fileName);
        while (file.available() > 0) {
            int i = file.read();
            list[i] += 1;
        }
        file.close();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list.length; i++) {
            if (list[i] > 0 && list[i] < min) min = list[i];
        }

        for (int i = 0; i < list.length; i++) {
            if (list[i] == min) System.out.print(i + " ");
        }


    }
}
