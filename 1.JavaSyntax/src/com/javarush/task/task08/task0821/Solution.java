package com.javarush.task.task08.task0821;

import java.util.HashMap;
import java.util.Map;

/* 
Однофамильцы и тёзки
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, String> map = createPeopleList();
        printPeopleList(map);
    }

    public static Map<String, String> createPeopleList() {
        //напишите тут ваш код
        Map<String, String> map = new HashMap<>();
        map.put("ivanov", "ivan");
        map.put("ivanov", "vladimir");
        map.put("petrov", "alexey");
        map.put("sidorov", "ivan");
        map.put("sergeev", "pavel");
        map.put("alikov", "stas");
        map.put("pupkin", "ivan");
        map.put("putin", "petr");
        map.put("volkov", "sergey");
        map.put("ivanov", "vladimir");
        return map;
    }

    public static void printPeopleList(Map<String, String> map) {
        for (Map.Entry<String, String> s : map.entrySet()) {
            System.out.println(s.getKey() + " " + s.getValue());
        }
    }
}
