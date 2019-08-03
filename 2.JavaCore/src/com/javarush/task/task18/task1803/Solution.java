package com.javarush.task.task18.task1803;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/* 
Самые частые байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String fileName = r.readLine();
        r.close();

//        HashMap<Integer, Integer> bytes = new HashMap<>();
        int[] list = new int[256];


        FileInputStream file = new FileInputStream(fileName);
        int max = 0;
        while (file.available() > 0) {
            int i = file.read();
            list[i] += 1;
            if (max < list[i]) max = list[i];
        }
        file.close();
        for (int i = 0; i < list.length; i++) {
            if (list[i] == max) System.out.print(i + " ");
        }

    }
}
