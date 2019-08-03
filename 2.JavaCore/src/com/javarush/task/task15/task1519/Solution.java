package com.javarush.task.task15.task1519;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/* 
Разные методы для разных типов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //напиште тут ваш код
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String value = r.readLine();
            if ("exit".equals(value)) break;
            try {
                if (value.contains(".")) {
                    print(Double.valueOf(value));
                } else {
                    int n = Integer.valueOf(value);
                    if (n > 0 && n < 128) {
                        print((short) n);
                    } else if (n <= 0 || n >= 128) {
                        print(Integer.valueOf(n));
                    }
                }
            } catch (NumberFormatException e) {
                print(value);
            }
        }
        r.close();
    }

    public static void print(Double value) {
        System.out.println("Это тип Double, значение " + value);
    }

    public static void print(String value) {
        System.out.println("Это тип String, значение " + value);
    }

    public static void print(short value) {
        System.out.println("Это тип short, значение " + value);
    }

    public static void print(Integer value) {
        System.out.println("Это тип Integer, значение " + value);
    }
}
