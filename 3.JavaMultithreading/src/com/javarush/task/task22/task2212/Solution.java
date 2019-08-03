package com.javarush.task.task22.task2212;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/*
Проверка номера телефона
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if (telNumber == null) return false;
        if (telNumber.isEmpty()) return false;
        if (!telNumber.matches("^[\\d+(]?.+\\d$")) return false;
        int numbersCount = (telNumber.replaceAll("\\D", "")).length();
        if (telNumber.indexOf('+') == 0 && numbersCount != 12) return false;
        if (telNumber.indexOf('+') == -1 && numbersCount != 10) return false;
        if (telNumber.contains("(") && !telNumber.matches(".*\\(\\d{3}\\).*")) return false;
        if (telNumber.contains(")") && !telNumber.matches(".*\\(\\d{3}\\).*")) return false;
        if (telNumber.contains("-") && telNumber.matches(".*-{2,}.*")) return false;
        if (telNumber.contains("-") && (telNumber.replaceAll("[^\\-]", "")).length() > 2) return false;
        if (telNumber.contains("(") && (telNumber.replaceAll("[^(]", "")).length() > 1) return false;
        if (telNumber.contains(")") && (telNumber.replaceAll("[^)]", "")).length() > 1) return false;
        if (telNumber.contains("(") && telNumber.contains("-") && telNumber.indexOf('-') < telNumber.indexOf(')')) return false;



        return true;
    }

    public static void main(String[] args) {
        Map<String, Boolean> tests = new LinkedHashMap<>();
        tests.put("+380501234567", true);
        tests.put("+38(050)1234567", true);
        tests.put("+38(050)12(345)67", false);
        tests.put("+38050123-45-67", true);
        tests.put("+380-501234567", true);
        tests.put("+380-50-1234567", true);
        tests.put("+380-50--1234567", false);
        tests.put("+380-50-12-34567", false);
        tests.put("+380-50-123(456)7", false);
        tests.put("+3(805)0123-45-67", true);
        tests.put("050123-4567", true);
        tests.put("+38)050(1234567", false);
        tests.put("+38(050)1-23-45-6-7", false);
        tests.put("050ххх4567", false);
        tests.put("050123456", false);
        tests.put("(0)501234567", false);
        tests.put(null, false);
        tests.put("", false);


        for (Map.Entry<String, Boolean> test : tests.entrySet()) {
            boolean result = checkTelNumber(test.getKey());
            String s = (test.getValue() != result) ? "!!! " : "";
            System.out.println(s + test.getKey() + " " + result + "/" + test.getValue());
        }

    }
}
