package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Парсер реквестов
*/

public class Solution {
    public static void main(String[] args) {
        //add your code here
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String url = "";
        try {
            url = r.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String paramsString = url.substring(url.indexOf("?") + 1);
        String[] paramsPairs = paramsString.split("&");
        for (String pair : paramsPairs) {
            System.out.print(pair.split("=")[0] + " ");
        }
        for (String pair : paramsPairs) {
            String[] p = pair.split("=");
            if ("obj".equals(p[0])) {
                try {
                    alert(Double.parseDouble(p[1]));
                } catch (Exception e) {
                    alert(p[1]);
                }
            }
        }
    }

    public static void alert(double value) {
        System.out.println("double " + value);
    }

    public static void alert(String value) {
        System.out.println("String " + value);
    }
}
