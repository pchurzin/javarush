package com.javarush.task.task40.task4009;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Locale;

/* 
Buon Compleanno!
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(weekDayOfBirthday("30.05.1984", "2015"));
    }

    public static String weekDayOfBirthday(String birthday, String year) {
        //напишите тут ваш код
        LocalDate ldBirthday = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("d.M.y"));
        LocalDate queryBirthday = ldBirthday.withYear(Year.parse(year).getValue());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE").withLocale(Locale.ITALIAN);
        return queryBirthday.format(formatter);
    }
}
