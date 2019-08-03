package com.javarush.task.task18.task1828;

/* 
Прайсы 2
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String file;
        try {
            file = r.readLine();
            r.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        List<String> lines = new ArrayList<>();
        BufferedReader in;
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        try {
            while (in.ready()) {
                lines.add(in.readLine());
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if ("-d".equals(args[0])) {
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).substring(0, 8).trim().equals(args[1])) {
                    lines.remove(i);
                    break;
                }
            }
        }
        else if ("-u".equals(args[0])) {
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).substring(0, 8).trim().equals(args[1])) {
                    String s = String.format("%-8s%-30s%-8s%-4s", args[1], args[2], args[3], args[4]);
                    lines.set(i, s);
                    break;
                }
            }
        }

        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            for (String line : lines) {
                out.write(line);
                out.newLine();
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
