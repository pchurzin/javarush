package com.javarush.task.task18.task1827;

/* 
Прайсы
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String file = r.readLine();
        r.close();
        ArrayList<String> lines = new ArrayList<>();

        if (!"-c".equals(args[0]) || args.length != 4) return;

        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        int maxId = -1;
        while (in.ready()) {
            String line = in.readLine();
            lines.add(line);
            int id = Integer.parseInt(line.substring(0, 8).trim());
            maxId = id > maxId ? id : maxId;
        }
        in.close();

        FileOutputStream out = new FileOutputStream(file);
        for (String line : lines) {
            out.write((line + "\n").getBytes());
        }

        String outLine = String.format("%-8d%-30s%-8s%-4s%n", maxId + 1, args[1], args[2], args[3]);
        out.write(outLine.getBytes());
        out.close();
    }
}
