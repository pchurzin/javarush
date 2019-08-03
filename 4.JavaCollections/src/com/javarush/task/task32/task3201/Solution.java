package com.javarush.task.task32.task3201;

import java.io.IOException;
import java.io.RandomAccessFile;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) {
        long position = Long.parseLong(args[1]);
        try (RandomAccessFile file = new RandomAccessFile(args[0], "w")) {
            if (position > file.length()) {
                file.seek(file.length());
            } else {
                file.seek(position);
            }
            file.write(args[2].getBytes());
        } catch (IOException ios) {
            ios.printStackTrace();
        }
    }
}
