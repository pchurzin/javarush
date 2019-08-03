package com.javarush.task.task05.task0531;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Совершенствуем функциональность
*/

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] nums = new int[5];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(reader.readLine());
        }


        int minimum = min(nums[0], nums[1], nums[2], nums[3], nums[4]);

        System.out.println("Minimum = " + minimum);
    }


    public static int min(int a, int b, int c, int d, int e) {
        int[] array = new int[]{a, b, c, d, e};
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (min > array[i]) min = array[i];
        }
        return min;
    }
}
