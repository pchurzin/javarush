package com.javarush.task.task13.task1318;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.FileInputStream;

/* 
Чтение файла
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        // напишите тут ваш код
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String fileName = r.readLine();


        FileInputStream inFileReader = new FileInputStream(fileName);

        while (inFileReader.available() > 0) {
            System.out.print((char)inFileReader.read());
        }
        System.out.println("");

        inFileReader.close();
        r.close();
    }
}