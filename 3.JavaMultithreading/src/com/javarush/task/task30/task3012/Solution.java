package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
    }

    public void createExpression(int number) {
        StringBuilder sb = new StringBuilder();
        sb.append(number).append(" =");
        //напишите тут ваш код
        int digit = 1;
        while (number > 0) {

            int remainder = number % 3;
            number /= 3;
            if (remainder == 2) {
                number += 1;
                sb.append(" - ").append(digit);
            } else if (remainder == 1) {
                sb.append(" + ").append(digit);
            }
            digit *= 3;
        }

        System.out.println(sb.toString());
    }
}