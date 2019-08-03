package com.javarush.task.task08.task0817;

import java.util.HashMap;
import java.util.Map;

/* 
Нам повторы не нужны
*/

public class Solution {
    public static HashMap<String, String> createMap() {
        //напишите тут ваш код
        HashMap<String, String> map = new HashMap<>();
        map.put("ivanov", "ivan");
        map.put("petrov", "ivan");
        map.put("sidorov", "ivan");
        map.put("pupkin", "ivan");
        map.put("putin", "sergey");
        map.put("volkov", "ivan");
        map.put("sokolov", "mihail");
        map.put("babenko", "ivan");
        map.put("medvedev", "ivan");
        map.put("suslov", "petr");
        return map;
    }

    public static void removeTheFirstNameDuplicates(HashMap<String, String> map) {
        //напишите тут ваш код
        HashMap<String, Integer> countMap = new HashMap<>();
        for (String name : map.values()) {
            if (countMap.containsKey(name)) {
                countMap.put(name, countMap.get(name) + 1);
            } else {
                countMap.put(name, 0);
            }
        }
        for (Map.Entry<String, Integer> pair : countMap.entrySet()) {
            if (pair.getValue() > 1) removeItemFromMapByValue(map, pair.getKey());
        }
    }

    public static void removeItemFromMapByValue(HashMap<String, String> map, String value) {
        HashMap<String, String> copy = new HashMap<String, String>(map);
        for (Map.Entry<String, String> pair : copy.entrySet()) {
            if (pair.getValue().equals(value))
                map.remove(pair.getKey());
        }
    }

    public static void main(String[] args) {

    }
}
