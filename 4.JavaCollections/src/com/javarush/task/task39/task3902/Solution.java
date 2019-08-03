package com.javarush.task.task39.task3902;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Биты были биты
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please type in a number: ");

        long l = Long.parseLong(reader.readLine());
        String result = isWeightEven(l) ? "even" : "odd";
        System.out.println("Number of ones in a given number is " + result);

    }

    public static boolean isWeightEven(long number) {
        long result = 0L;
        for (int i = 0; i < 64; i++) {
            result ^= number & 1L;
            number >>>= 1;
            if (number == 0L) {
                break;
            }
        }
        return result == 0L;
    }
}
