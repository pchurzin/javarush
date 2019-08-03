package com.javarush.task.task40.task4008;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;

/* 
Работа с Java 8 DateTime API
*/

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        //напишите тут ваш код
        boolean printDate = false;
        boolean printTime = false;
        DateTimeFormatter formatter;
        LocalDate localDate = null;
        LocalTime localTime = null;
        if (date.contains(".")) {
            printDate = true;
        }
        if (date.contains(":")) {
            printTime = true;
        }
        if (printDate && printTime) {
            formatter = DateTimeFormatter.ofPattern("d.M.y H:m:s");
            localDate = LocalDate.parse(date, formatter);
            localTime = LocalTime.parse(date, formatter);
        } else if (printDate) {
            formatter = DateTimeFormatter.ofPattern("d.M.y");
            localDate = LocalDate.parse(date, formatter);
        } else {
            formatter = DateTimeFormatter.ofPattern("H:m:s");
            localTime = LocalTime.parse(date, formatter);
        }

        if (printDate) {
            System.out.println("День: " + localDate.get(ChronoField.DAY_OF_MONTH));
            System.out.println("День недели: " + localDate.get(ChronoField.DAY_OF_WEEK));
            System.out.println("День месяца: " + localDate.get(ChronoField.DAY_OF_MONTH));
            System.out.println("День года: " + localDate.get(ChronoField.DAY_OF_YEAR));
            System.out.println("Неделя месяца: " + localDate.get(ChronoField.ALIGNED_WEEK_OF_MONTH));
            System.out.println("Неделя года: " + localDate.get(ChronoField.ALIGNED_WEEK_OF_YEAR));
            System.out.println("Месяц: " + localDate.get(ChronoField.MONTH_OF_YEAR));
            System.out.println("Год: " + localDate.get(ChronoField.YEAR));
        }
        if (printTime) {
            System.out.println("AM или PM: " + (localTime.get(ChronoField.AMPM_OF_DAY) == 0 ?
            "AM" : "PM"));
            System.out.println("Часы: " + localTime.get(ChronoField.HOUR_OF_AMPM));
            System.out.println("Часы дня: " + localTime.get(ChronoField.HOUR_OF_DAY));
            System.out.println("Минуты: " + localTime.get(ChronoField.MINUTE_OF_HOUR));
            System.out.println("Секунды: " + localTime.get(ChronoField.SECOND_OF_MINUTE));
        }

    }
}
