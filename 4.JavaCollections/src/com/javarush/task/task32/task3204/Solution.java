package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        StringWriter dict = new StringWriter();
        for (char c = 'a'; c < 'z'; c++) {
            dict.write(c);
        }
        for (char c = 'A'; c < 'Z'; c++) {
            dict.write(c);
        }
        for (char c = '0'; c < '9'; c++) {
            dict.write(c);
        }
        byte[] dictBytes = dict.toString().getBytes();

        boolean hasNumber = false;
        boolean hasUpper = false;
        boolean hasLower = false;
        ByteArrayOutputStream bos = null;


        while (!hasNumber || !hasUpper || !hasLower) {
            hasNumber = false;
            hasUpper = false;
            hasLower = false;
            bos = new ByteArrayOutputStream();
            for (int i = 0; i < 8; i++) {
                int index = (int) (Math.random() * dictBytes.length);
                byte b = dictBytes[index];
                if (b >= 'A' && b <= 'Z') {
                    hasUpper = true;
                }
                if (b >= '0' && b <= '9') {
                    hasNumber = true;
                }
                if (b >= 'a' && b <= 'z') {
                    hasLower = true;
                }
                bos.write(b);
            }
        }
        return bos;
    }
}