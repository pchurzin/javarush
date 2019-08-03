package com.javarush.task.task13.task1326;

/* 
Сортировка четных чисел из файла
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) {
        // напишите тут ваш код
        ArrayList<Integer> list = new ArrayList<>();
        String fileName = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            fileName = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            while (in.ready()) {
                int n = Integer.parseInt(in.readLine());
                if (n % 2 == 0) list.add(n);
            }
            in.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        Collections.sort(list);

        for (int n : list) {
            System.out.println(n);
        }
    }
}
