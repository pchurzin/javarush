package com.javarush.task.task19.task1927;

/* 
Контекстная реклама
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    private static class AdsPrintStream extends PrintStream {
        private int lineCount = 0;
        private PrintStream out;

        public AdsPrintStream(PrintStream out) {
            super(System.out);
            this.out = out;
        }

        @Override
        public void println(String x) {
            out.println(x);
            lineCount = (lineCount + 1) % 2;
            if (lineCount == 0) {
                out.println("JavaRush - курсы Java онлайн");
            }
        }
    }

    public static void main(String[] args) {
        PrintStream console = System.out;
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(ba);
        System.setOut(stream);
        testString.printSomething();
        System.setOut(console);
        String[] lines = ba.toString().split("\n");
        for (int i = 0; i < lines.length; i++) {
            if (i > 0 && i % 2 == 0) System.out.println("JavaRush - курсы Java онлайн");
            System.out.println(lines[i]);
        }
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
