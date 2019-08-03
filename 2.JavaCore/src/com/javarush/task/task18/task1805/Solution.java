package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String fileName = r.readLine();
        r.close();

        ArrayList<Integer> bytes = new ArrayList<>();

        FileInputStream file = new FileInputStream(fileName);
        while (file.available() > 0) {
            int i = file.read();
            if (!bytes.contains(i)) bytes.add(i);
        }
        file.close();

        Collections.sort(bytes);
        for (int i : bytes) {
            System.out.print(i + " ");
        }
    }
}
