package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
//        System.out.println(getPartOfString("JavaRush-лучшийсервис"));
//        System.out.println(getPartOfString("JavaRush - лучший сервисобучения "));
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) {
        if (string == null) throw new TooShortStringException();
        /*int begin = string.indexOf(' ');
        if (begin == -1) throw new TooShortStringException();
        begin += 1;
        int end = begin ;
        for (int i = 0; i < 3; i++) {
            end = string.indexOf(' ', end + 1);
            if (end == -1) throw new TooShortStringException();
        }
        end = string.indexOf(' ', end + 1);
        if (end == -1) {
            return string.substring(begin);
        }
        return string.substring(begin, end);
        */
        String[] words = (string.trim()).split(" ");
        if (words.length < 5) throw new TooShortStringException();
        return words[1] + " " + words[2] + " " + words[3] + " " + words[4];
    }

    public static class TooShortStringException extends RuntimeException {
    }
}
