package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) {
        String fname = args[0];
        long position = Long.parseLong(args[1]);
        String text = args[2];

        try (RandomAccessFile file = new RandomAccessFile(fname, "rw")) {
            file.seek(position);
            byte[] buffer = text.getBytes();
            file.read(buffer, 0, buffer.length);
            file.seek(file.length());
            if (text.equals(new String(buffer))) {
                file.write("true".getBytes());
            } else {
                file.write("false".getBytes());
            }
        } catch (IOException ios) {
            ios.printStackTrace();
        }
    }
}
