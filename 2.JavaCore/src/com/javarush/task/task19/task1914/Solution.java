package com.javarush.task.task19.task1914;

/* 
Решаем пример
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream console = System.out;
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        System.setOut(new PrintStream(ba));
        testString.printSomething();
        System.setOut(console);
        String orig = ba.toString().replace("\n", "");
        String[] parts = orig.split("\\s");
        int a = Integer.parseInt(parts[0]);
        int b = Integer.parseInt(parts[2]);
        int result = 0;
        switch (parts[1]) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;

        }
        System.out.println(orig + result);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

