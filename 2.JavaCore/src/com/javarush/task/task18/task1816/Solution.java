package com.javarush.task.task18.task1816;

/* 
Английские буквы
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) {
        String inFileName = args[0];
        FileInputStream file = null;
        try {
            file = new FileInputStream(inFileName);
        } catch (FileNotFoundException e) {
            System.out.println("File " + inFileName + " not found");
            return;
        }
        int count = 0;
        try{
            while (file.available() > 0) {
                int i = file.read();
                if ((i >= 'a' && i <= 'z') || (i >= 'A' && i <= 'Z')) count++;
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(count);
    }
}
