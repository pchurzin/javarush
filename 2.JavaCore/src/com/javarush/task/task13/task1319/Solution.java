package com.javarush.task.task13.task1319;

import java.io.*;

/* 
Писатель в файл с консоли
*/

public class Solution {
    public static void main(String[] args) throws Exception{
        // напишите тут ваш код
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String fileName = in.readLine();

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));

        while (true) {
            String line = in.readLine();
            out.write(line + "\n");
            if (line.equals("exit")) break;
        }
        in.close();
        out.close();
    }
}
