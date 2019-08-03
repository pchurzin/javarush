package com.javarush.task.task20.task2025;

import java.util.ArrayList;

/*
Алгоритмы-числа
*/
public class Solution {
    //todo сумма степеней
    private static long[] powers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private static int length = 1;

    public static long[] getNumbers(long N) {

        ArrayList<Long> result = new ArrayList<>();
        for (long i = 0; i < N; i++) {
            if (sumOfPows(i) == i) {
                result.add(i);
            }

        }
        long[] r = new long[result.size()];
        for (int i = 0; i < result.size(); i++) {
            r[i] = result.get(i);
        }
        return r;
    }

    private static void nextPows() {
        for (int i = 1; i < powers.length; i++) {
            powers[i] *= i;
        }
    }

    private static long sumOfPows(long n) {
        long sum = 0;
        String ns = String.valueOf(n);
        int m = ns.length();
        if (m > length) {
            length = m;
            nextPows();
        }
        for (int i = 0; i < m; i++) {
            sum += powers[Integer.valueOf(ns.substring(i, i + 1))];
        }
        return sum;
    }

    private static long pow (long a, int b)
    {
        if (b == 0)        return 1;
        if (b == 1)        return a;
        if (b % 2 == 0)    return     pow ( a * a, b/2); //even a=(a^2)^b/2
        else               return a * pow ( a * a, b/2); //odd  a=a*(a^2)^b/2
    }

    public static void main(String[] args) {
        for (long n : getNumbers(10000)) {
            System.out.println(n);
        }
    }
}
