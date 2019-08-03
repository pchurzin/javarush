package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        int minRadix = 0;
        try {
            String in = args[0];
            for (int radix = 2; radix <= 36; radix++) {
                try {
                    new BigInteger(in, radix);
                } catch (Exception e) {
                    continue;
                }
                minRadix = radix;
                break;
            }
        } catch (Exception e) {

        }
        System.out.println(minRadix == 0 ? "incorrect" : minRadix);
    }
}