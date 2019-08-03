package com.javarush.task.task05.task0529;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Консоль-копилка
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String input;
        int sum = 0;
        while (!(input = r.readLine()).equals("сумма")) {
            sum += Integer.parseInt(input);
        }
        System.out.println(sum);
    }
}
