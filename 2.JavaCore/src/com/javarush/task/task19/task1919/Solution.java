package com.javarush.task.task19.task1919;

/* 
Считаем зарплаты
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader(args[0]));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Map<String, Double> data = new HashMap<>();
        try {
            while (in.ready()) {
                String[] parts = in.readLine().split("\\s");
                if (data.containsKey(parts[0])) {
                    data.put(parts[0], data.get(parts[0]) + Double.parseDouble(parts[1]));
                } else {
                    data.put(parts[0], Double.parseDouble(parts[1]));
                }
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        ArrayList<String> names = new ArrayList<>(data.keySet());
        Collections.sort(names);
        for (String name : names) {
            System.out.println(name + " " + data.get(name));
        }
    }
}
