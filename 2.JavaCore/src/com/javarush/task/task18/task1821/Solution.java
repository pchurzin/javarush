package com.javarush.task.task18.task1821;

/* 
Встречаемость символов
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Solution {
    public static void main(String[] args) {
        FileInputStream in;
        try {
            in = new FileInputStream(args[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        HashMap<Integer, Integer> chrFreq = new HashMap<>();
        try {
            while (in.available() > 0) {
                int c = in.read();
                if (chrFreq.containsKey(c)) {
                    chrFreq.put(c, chrFreq.get(c) + 1);
                } else {
                    chrFreq.put(c, 1);
                }
            }
            in.close();
            ArrayList<Integer> codes = new ArrayList<>(chrFreq.keySet());
            Collections.sort(codes);
            for (int code : codes) {
                System.out.println((char)code + " " + chrFreq.get(code));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
