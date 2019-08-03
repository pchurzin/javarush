package com.javarush.task.task16.task1630;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static String firstFileName;
    public static String secondFileName;

    //add your code here - добавьте код тут
    static {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        try {
            firstFileName = r.readLine();
            secondFileName = r.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        //add your code here - добавьте код тут
        f.join();
        System.out.println(f.getFileContent());
    }

    public interface ReadFileInterface {

        void setFileName(String fullFileName);

        String getFileContent();

        void join() throws InterruptedException;

        void start();
    }

    //add your code here - добавьте код тут
    public static class ReadFileThread extends Thread implements ReadFileInterface {
        private String fileName;
        private ArrayList<String> strings = new ArrayList<String>();
        @Override
        public void setFileName(String fullFileName) {
            fileName = fullFileName;
        }

        @Override
        public String getFileContent() {
            String result = "";
            for (String s : strings) {
                result += s + " ";
            }
            return result;
        }

        @Override
        public void run() {
            strings.clear();
            BufferedReader r = null;
            try {
                r = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                while (r.ready()) {
                    strings.add(r.readLine());
                }
                r.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
