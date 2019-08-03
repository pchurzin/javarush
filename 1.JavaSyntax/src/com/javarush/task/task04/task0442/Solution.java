package com.javarush.task.task04.task0442;


/* 
Суммирование
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        int in = Integer.parseInt(r.readLine());
        int sum = 0;
        while (in != -1) {
            sum += in;
            in = Integer.parseInt(r.readLine());
        }
        System.out.println(--sum);
    }
}
