package com.javarush.task.task19.task1920;

/* 
Самый богатый
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

        double max = Collections.max(data.values());
        ArrayList<String> theRichest = new ArrayList<>();
        for (Map.Entry<String, Double> pair : data.entrySet()) {
            if (pair.getValue() == max) theRichest.add(pair.getKey());
        }
        Collections.sort(theRichest);
        for (String name : theRichest) {
            System.out.println(name);
        }
    }
}
