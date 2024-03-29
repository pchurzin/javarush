package com.javarush.task.task39.task3903;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Неравноценный обмен
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Please type in a number: ");

        long number = Long.parseLong(reader.readLine());
        System.out.println("Please type in first index: ");
        int i = Integer.parseInt(reader.readLine());
        System.out.println("Please type in second index: ");
        int j = Integer.parseInt(reader.readLine());

        System.out.println("The result of swapping bits is " + swapBits(number, i, j));
    }

    public static long swapBits(long number, int i, int j) {
        if (i < 0 || j < 0) {
            return number;
        }
        long bitI = ((1L << i) & number) >> i;
        long bitJ = ((1L << j) & number) >> j;
        long result1 = bitI == 1L ? number | (1L << j) : number & ~(1L << j);
        long result2 = bitJ == 1L ? result1 | (1L << i) : result1 & ~(1L << i);
        return result2;
    }
}
