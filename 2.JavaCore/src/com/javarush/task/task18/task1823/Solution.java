package com.javarush.task.task18.task1823;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/* 
Нити и байты
*/

public class Solution {
    public volatile static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String fn;
            try {
                fn = r.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            if ("exit".equals(fn)) break;
            new ReadThread(fn).start();
        }
    }

    public static class ReadThread extends Thread {
        private String fileName;

        public ReadThread(String fileName) {
            //implement constructor body
            this.fileName = fileName;
        }
        // implement file reading here - реализуйте чтение из файла тут

        @Override
        public void run() {
            FileInputStream in;
            try {
                in = new FileInputStream(fileName);
            } catch (FileNotFoundException e) {
                System.out.println("File " + fileName + " not found");
                return;
            }

            Map<Integer, Integer> freq = new HashMap<>();
            int maxByte = -1;
            int maxCount = 0;
            try {
                while (in.available() > 0) {
                    int i = in.read();
                    if (freq.containsKey(i)) {
                        freq.put(i, freq.get(i) + 1);
                    } else {
                        freq.put(i, 1);
                    }
                    if (freq.get(i) > maxCount) {
                        maxCount = freq.get(i);
                        maxByte = i;
                    }
                }
                in.close();
                synchronized (resultMap) {
                    resultMap.put(fileName, maxByte);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
