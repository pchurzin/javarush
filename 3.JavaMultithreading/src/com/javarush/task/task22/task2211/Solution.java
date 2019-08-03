package com.javarush.task.task22.task2211;

import java.io.*;

/* 
Смена кодировки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(args[0]));
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(args[1]));
            byte[] inBuffer = new byte[1024];
            byte[] outBuffer = new byte[1024];
            while (in.available() > 0) {
                int length = in.read(inBuffer);
                String s = new String(inBuffer, 0, length, "Windows-1251");
                outBuffer = s.getBytes("UTF-8");
                out.write(outBuffer);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
