package com.javarush.task.task09.task0930;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Задача по алгоритмам
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<String>();
        while (true) {
            String s = reader.readLine();
            if (s.isEmpty()) break;
            list.add(s);
        }

        String[] array = list.toArray(new String[list.size()]);
        sort(array);

        for (String x : array) {
            System.out.println(x);
        }
    }

    public static void sort(String[] array) {
        //напишите тут ваш код
        ArrayList<Integer> intList = new ArrayList<>();
        ArrayList<Integer> strList = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            if (isNumber(array[i])) {
                intList.add(i);
            } else {
                strList.add(i);
            }
        }

        if (!intList.isEmpty()) {
            int current = intList.size() - 1;
            while (current >= 0) {
                int minIndex = intList.get(current);
                int currentIndex = minIndex;
                int min = Integer.parseInt(array[currentIndex]);
                for (int i = current; i >=0 ; i--) {
                    int x = Integer.parseInt(array[intList.get(i)]);
                    if (min > x) {
                        minIndex = intList.get(i);
                        min = x;
                    }
                }
                String h = array[currentIndex];
                array[currentIndex] = array[minIndex];
                array[minIndex] = h;
                current--;
            }
        }

        if (!strList.isEmpty()) {
            int current = strList.size() - 1;
            while (current >= 0) {
                int minIndex = strList.get(current);
                int currentIndex = minIndex;
                String min = array[currentIndex];
                for (int i = current; i >= 0; i--) {
                    if (isGreaterThan(array[strList.get(i)], min)) {
                        minIndex = strList.get(i);
                        min = array[strList.get(i)];
                    }
                }
                String h = array[currentIndex];
                array[currentIndex] = array[minIndex];
                array[minIndex] = h;
                current--;
            }
        }
    }

    // Метод для сравнения строк: 'а' больше чем 'b'
    public static boolean isGreaterThan(String a, String b) {
        return a.compareTo(b) > 0;
    }


    // Переданная строка - это число?
    public static boolean isNumber(String s) {
        if (s.length() == 0) return false;

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if ((i != 0 && c == '-') // есть '-' внутри строки
                    || (!Character.isDigit(c) && c != '-')) // не цифра и не начинается с '-'
            {
                return false;
            }
        }
        return true;
    }
}
