package com.javarush.task.task09.task0921;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Метод в try..catch
*/

public class Solution {
    public static void main(String[] args) {
        readData();
    }

    public static void readData() {
        //напишите тут ваш код
        List<Integer> list = new ArrayList<>();
        try {
            BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                list.add(Integer.parseInt(r.readLine()));
            }
        } catch (Exception e) {
            for (Integer i : list) {
                System.out.println(i);
            }
        }
    }
}
