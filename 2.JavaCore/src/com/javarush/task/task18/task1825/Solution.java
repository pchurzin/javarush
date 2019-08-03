package com.javarush.task.task18.task1825;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* 
Собираем файл
*/

public class Solution {
    //private Map<Integer, String> parts = new HashMap<>();
    private static List<Integer> parts = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String fn;
        String baseFileName = "";
        while (true) {
            fn = r.readLine();
            if ("end".equals(fn)) break;

            baseFileName = fn.substring(0, fn.lastIndexOf(".part"));
            int partNumber = Integer.valueOf(fn.substring(fn.lastIndexOf(".part") + 5));
            parts.add(partNumber);

        }
        r.close();
        Collections.sort(parts);

        FileOutputStream out = new FileOutputStream(baseFileName);
        try {
            for (Integer n : parts) {
                FileInputStream in = new FileInputStream(baseFileName + ".part" + n);
                byte[] buffer = new byte[in.available()];
                in.read(buffer);
                in.close();
                out.write(buffer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        out.close();
    }
}
