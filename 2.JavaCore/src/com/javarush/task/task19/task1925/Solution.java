package com.javarush.task.task19.task1925;

/* 
Длинные слова
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
            boolean isFirstWord = true;
            while (in.hasNext()) {
                String word = in.next();
                if (word.length() > 6) {
                    if (!isFirstWord) {
                        out.write(",");
                    } else {
                        isFirstWord = false;
                    }
                    out.write(word);
                }
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
