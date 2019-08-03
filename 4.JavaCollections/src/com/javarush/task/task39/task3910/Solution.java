package com.javarush.task.task39.task3910;

/* 
isPowerOfThree
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isPowerOfThree(9));
        System.out.println(isPowerOfThree(18));
        System.out.println(isPowerOfThree(-9));
        System.out.println(isPowerOfThree(1));
    }

    public static boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        while (n % 3 == 0 && n > 3) {
            n = n / 3;
        }
        return n % 3 == 0;
    }
}
