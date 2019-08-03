package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Ivanov");
        map.put("country", "Russia");
        map.put("age", null);
        System.out.println(getQuery(map));
    }
    public static String getQuery(Map<String, String> params) {
        StringBuilder query = new StringBuilder();
        for (Map.Entry pair : params.entrySet()) {
            if (pair.getValue() == null) continue;
            if (query.length() > 0) query.append(" and ");
            query.append(pair.getKey()).append(" = '").append(pair.getValue()).append("'");
        }
        return query.toString();
    }
}
