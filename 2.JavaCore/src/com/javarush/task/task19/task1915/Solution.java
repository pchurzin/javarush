package com.javarush.task.task19.task1915;

/* 
Дублируем текст
*/

import java.io.*;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String fn;
        try {
            fn = r.readLine();
            r.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        PrintStream console = System.out;
        System.setOut(new PrintStream(ba));
        testString.printSomething();
        System.setOut(console);
        System.out.println(ba.toString());
        try {
            FileOutputStream out = new FileOutputStream(fn);
            out.write(ba.toByteArray());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}

