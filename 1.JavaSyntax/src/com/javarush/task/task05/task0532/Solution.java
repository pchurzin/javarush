package com.javarush.task.task05.task0532;

import java.io.*;

/* 
Задача по алгоритмам
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(reader.readLine());
        }
        int maximum = nums[0];
        for (int i = 1; i < n; i++) {
            if (maximum < nums[i]) maximum = nums[i];
        }


        //напишите тут ваш код

        System.out.println(maximum);
    }
}
