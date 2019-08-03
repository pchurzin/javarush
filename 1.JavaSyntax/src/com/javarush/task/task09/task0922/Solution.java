package com.javarush.task.task09.task0922;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* 
Какое сегодня число?
*/

public class Solution {

    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Date d = format.parse(r.readLine());
        format.applyPattern("MMM dd, yyyy");
        //SimpleDateFormat outFormat = new SimpleDateFormat("MM dd, yyyy");
        System.out.println(format.format(d).toUpperCase());
    }
}
