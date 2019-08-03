package com.javarush.task.task08.task0818;

import com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl;

import java.util.HashMap;
import java.util.Map;

/* 
Только для богачей
*/

public class Solution {
    public static HashMap<String, Integer> createMap() {
        //напишите тут ваш код
        HashMap<String, Integer> map = new HashMap<>();
        map.put("ivanov", 1000);
        map.put("petrov", 700);
        map.put("sidorov", 340);
        map.put("pupkin", 1200);
        map.put("putin", 1034);
        map.put("medvedev", 300);
        map.put("sokolov", 500);
        map.put("sergeev", 600);
        map.put("strokov", 130);
        map.put("volkov", 100);
        return map;
    }

    public static void removeItemFromMap(HashMap<String, Integer> map) {
        //напишите тут ваш код
        HashMap<String, Integer> copy = new HashMap<>(map);
        for (Map.Entry<String, Integer> pair : copy.entrySet()) {
            if (pair.getValue() < 500) map.remove(pair.getKey());
        }
    }

    public static void main(String[] args) {

    }
}