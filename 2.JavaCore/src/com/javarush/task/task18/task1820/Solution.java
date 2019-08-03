package com.javarush.task.task18.task1820;

/* 
Округление чисел
*/

import java.io.*;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String inFn, outFn;
        try {
            inFn = r.readLine();
            outFn = r.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        FileInputStream in;
        FileOutputStream out;

        try {
            in = new FileInputStream(inFn);
            out = new FileOutputStream(outFn);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        Scanner scanner = new Scanner(in);
        scanner.useDelimiter(" ");
        try {
            while (scanner.hasNextFloat()) {
                float f = scanner.nextFloat();
                int i = Math.round(f);
                out.write((String.valueOf(i) + " ").getBytes());
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
