package com.javarush.task.task27.task2701;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Избавляемся от меток
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        System.out.println(isSubstringPresent(null, null));
        System.out.println(isSubstringPresent(null, ""));
        System.out.println(isSubstringPresent("", ""));
        System.out.println(isSubstringPresent("a", ""));
        System.out.println(isSubstringPresent("a", null));
        System.out.println(isSubstringPresent(null, "a"));
        System.out.println(isSubstringPresent(null, ""));

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String string = reader.readLine();
        String substring = reader.readLine();

        if (isSubstringPresent(substring, string)) {
            System.out.println("String: \n" + substring + "\nis present in the string: \n" + string);
        } else {
            System.out.println("String: \n" + substring + "\nis not present in the string: \n" + string);
        }
    }

    static boolean isSubstringPresent(String substring, String string) {
        return string.contains(substring);
    }
}

