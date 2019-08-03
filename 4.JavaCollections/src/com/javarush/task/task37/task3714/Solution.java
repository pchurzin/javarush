package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Древний Рим
*/
public class Solution {
    private static Map<Character, Integer> romanToInt = new HashMap<>();
    static {
        romanToInt.put('I', 1);
        romanToInt.put('V', 5);
        romanToInt.put('X', 10);
        romanToInt.put('L', 50);
        romanToInt.put('C', 100);
        romanToInt.put('D', 500);
        romanToInt.put('M', 1000);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        int result = 0;
        int prevNum = 0;
//        StringBuilder sb = new StringBuilder(s);
        for (Character digit : s.toCharArray()) {
            int num = romanToInt.get(digit);

            if (prevNum > 0 && num > prevNum) {
                result += num - prevNum - prevNum;
            } else {
                result += num;
            }
            prevNum = num;
        }
        return result;
    }
}
