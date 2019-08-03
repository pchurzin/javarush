package com.javarush.task.task39.task3912;

/* 
Максимальная площадь
*/

public class Solution {
    public static void main(String[] args) {
        int[][] test = {
                {0, 1, 1, 0, 1},
                {1, 1, 0, 1, 0},
                {0, 1, 1, 1, 0},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0}
        };

        System.out.println(maxSquare(test));
    }

    public static int maxSquare(int[][] matrix) {
        if (matrix == null) {
            return 0;
        }
        int[][] sizes = new int[matrix.length][matrix[0].length];
        int max = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (row ==0 || col == 0) {
                    sizes[row][col] = matrix[row][col];
                    continue;
                }
                if (matrix[row][col] == 1) {
                    sizes[row][col] = Math.min(sizes[row - 1][col], Math.min(sizes[row][col - 1], sizes[row - 1][col - 1])) + 1;
                    if (sizes[row][col] > max) {
                        max = sizes[row][col];
                    }
                } else {
                    sizes[row][col] = 0;
                }
            }
        }
        return max * max;
    }
}
