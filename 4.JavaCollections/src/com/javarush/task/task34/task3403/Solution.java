package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/
public class Solution {
    public void recursion(int n) {
        if (n < 2) {
            return;
        }
        int i = 0;
        for (i = 2; n % i != 0; i++);
        System.out.println(i + " ");
        recursion(n / i);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recursion(132);
    }
}
