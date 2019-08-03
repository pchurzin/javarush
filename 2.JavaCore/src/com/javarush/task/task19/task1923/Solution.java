package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
*/

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new FileReader(args[0]));
            BufferedWriter out = new BufferedWriter(new FileWriter(args[1]));

            while (in.hasNext()) {
                String word = in.next();
                if (word.matches(".*[0-9]+.*")) out.write(word + " ");
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
