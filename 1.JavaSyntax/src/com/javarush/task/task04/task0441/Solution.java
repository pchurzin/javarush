package com.javarush.task.task04.task0441;


/* 
Как-то средненько
*/
import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        int[] nums = new int[3];
        for (int i =0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(r.readLine());
        }

        int index = 0;
        while (index < nums.length) {
            int min = nums[index];
            int minIndex = index;
            for (int i = index; i < nums.length; i++) {
                if (nums[i] < min) {
                    min = nums[i];
                    minIndex = i;
                }
            }
            nums[minIndex] = nums[index];
            nums[index++] = min;
        }
        System.out.println(nums[1]);
    }
}
