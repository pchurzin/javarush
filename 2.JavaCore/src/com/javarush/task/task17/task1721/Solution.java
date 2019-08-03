package com.javarush.task.task17.task1721;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = null;
        String fileName2 = null;
        try {
            fileName1 = r.readLine();
            fileName2 = r.readLine();
            r.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            r = new BufferedReader(new InputStreamReader(new FileInputStream(fileName1)));
            while (r.ready()) {
                allLines.add(r.readLine());
            }
            r.close();

            r = new BufferedReader(new InputStreamReader(new FileInputStream(fileName2)));
            while (r.ready()) {
                forRemoveLines.add(r.readLine());
            }
            r.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Solution solution = new Solution();
        try {
            solution.joinData();
        } catch (CorruptedDataException e) {
            e.printStackTrace();
        }
    }

    public void joinData () throws CorruptedDataException {
        if (allLines.containsAll(forRemoveLines)) {
            allLines.removeAll(forRemoveLines);
        } else {
            allLines.clear();
            throw new CorruptedDataException();
        }
    }
}
