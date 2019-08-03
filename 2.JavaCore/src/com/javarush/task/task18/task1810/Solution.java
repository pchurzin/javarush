package com.javarush.task.task18.task1810;

/* 
DownloadException
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws DownloadException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                String fileName = r.readLine();
                if (fileName.isEmpty()) break;
                FileInputStream inFile = new FileInputStream(fileName);
                if (inFile.available() < 1000) {
                    inFile.close();
                    break;
                }
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
        throw new DownloadException();
    }

    public static class DownloadException extends Exception {

    }
}
