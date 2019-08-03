package com.javarush.task.task06.task0622;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Числа по возрастанию
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] nums = new int[5];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(reader.readLine());
        }

        int current = 0;
        while (current < nums.length) {
            int min = current;
            for (int i = current; i < nums.length; i++) {
                if (nums[i] < nums[current]) min = i;
            }
            int h = nums[current];
            nums[current] = nums[min];
            nums[min] = h;
            current++;
        }
        for (int a : nums) {
            System.out.println(a);
        }
        //напишите тут ваш код
    }
}
