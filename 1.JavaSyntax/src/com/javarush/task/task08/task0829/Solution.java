package com.javarush.task.task08.task0829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* 
Модернизация ПО
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //list of addresses
        Map<String, String> cities = new HashMap<>();
        //List<String> addresses = new ArrayList<String>();
        while (true) {
            String city = reader.readLine();
            if (city.isEmpty()) break;

            String family = reader.readLine();

            cities.put(city, family);
        }

        //read home number
        String city = reader.readLine();

        if (cities.containsKey(city)) {
            System.out.println(cities.get(city));
        }
    }
}
