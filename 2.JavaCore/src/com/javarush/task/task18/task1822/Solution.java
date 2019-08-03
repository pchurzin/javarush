package com.javarush.task.task18.task1822;

/* 
Поиск данных внутри файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String id = args[0];
        String fn;
        try {
            fn = r.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        BufferedReader in;
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(fn)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        try {
            while (in.ready()) {
                String line = in.readLine();
                String[] parts = line.split(" ");
                if (parts[0].equals(id)) {
                    System.out.print(parts[0] + " ");

                    for (int i = 1; i < parts.length - 2; i++) {
                        System.out.print(parts[i] + " ");
                    }

                    System.out.print(parts[parts.length - 2] + " ");
                    System.out.println(parts[parts.length - 1]);
                    break;
                }
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
