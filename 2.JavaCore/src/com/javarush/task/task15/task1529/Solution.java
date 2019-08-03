package com.javarush.task.task15.task1529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Осваивание статического блока
*/

public class Solution {
    public static void main(String[] args) {

    }
    
    static {
        //add your code here - добавьте код тут
        reset();
    }

    public static Flyable result;

    public static void reset() {
        //add your code here - добавьте код тут
        try {
            BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
            String val = r.readLine();
            if ("helicopter".equals(val)) {
                result = new Helicopter();
            } else if ("plane".equals(val)) {
                int pax = Integer.parseInt(r.readLine());
                result = new Plane(pax);
            }
            r.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
