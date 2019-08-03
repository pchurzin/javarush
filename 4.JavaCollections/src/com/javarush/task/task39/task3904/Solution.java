package com.javarush.task.task39.task3904;

/*
Лестница
*/
public class Solution {
    private static int n = 70;
    public static void main(String[] args) {
        System.out.println("Number of possible runups for " + n + " stairs is: " + countPossibleRunups(n));
    }

    public static long countPossibleRunups(int n) {
        if (n < 0) {
            return 0;
        }
        long[] runups = new long[n + 3];
        runups[0] = 1L;
        runups[1] = 1L;
        runups[2] = 2L;
        for (int i = 3; i <= n; i++) {
            runups[i] = runups[i - 1] + runups[i - 2] + runups[i - 3];
        }
        return runups[n];
    }
}

