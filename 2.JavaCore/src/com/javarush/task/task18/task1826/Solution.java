package com.javarush.task.task18.task1826;

/* 
Шифровка
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) {
        if (args.length != 3) return;
        if ("-e".equals(args[0])) {
            try {
                encrypt(args[1], args[2]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("-d".equals(args[0])){
            try {
                decrypt(args[1], args[2]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void encrypt(String inFn, String outFn) throws IOException {
        FileInputStream in = new FileInputStream(inFn);
        FileOutputStream out = new FileOutputStream(outFn);
        while (in.available() > 0) {
            out.write(in.read() ^ 55);
        }
        in.close();
        out.close();
    }

    private static void decrypt(String inFn, String outFn) throws IOException {
        FileInputStream in = new FileInputStream(inFn);
        FileOutputStream out = new FileOutputStream(outFn);
        while (in.available() > 0) {
            out.write(in.read() ^ 55);
        }
        in.close();
        out.close();
    }
}
