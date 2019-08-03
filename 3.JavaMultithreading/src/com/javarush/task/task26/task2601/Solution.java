package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
     //   System.out.println(Arrays.toString(sort(new Integer[]{13, 8, 15, 5, 17})));
    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here
        Arrays.sort(array);
        int median;
        if (array.length % 2 == 0) {
            median = (array[array.length / 2 - 1] + array[array.length / 2]) / 2;
        } else {
            median = array[array.length / 2];
        }

        Arrays.sort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int distance1 = Math.abs(median - o1);
                int distance2 = Math.abs(median - o2);
                return distance1 - distance2 != 0 ?
                       distance1 - distance2:
                       o1 - o2;
            }
        });
        return array;
    }
}
