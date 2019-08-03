package com.javarush.task.task08.task0827;

import java.util.Date;

/* 
Работа с датой
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(isDateOdd("MAY 1 2013"));
    }

    public static boolean isDateOdd(String date) {
        Date d = new Date(date);
        Date startDate = (Date)d.clone();
        //startDate.setYear(d.getYear());
        startDate.setMonth(0);
        startDate.setDate(1);
        //startDate.setHours(0);
        //startDate.setMinutes(0);
        //startDate.setSeconds(0);
        //startDate.
        long span = d.getTime() - startDate.getTime();

        long numDays = span / (24 * 60 * 60 *1000) + 1;
        return numDays % 2 != 0;
    }
}
