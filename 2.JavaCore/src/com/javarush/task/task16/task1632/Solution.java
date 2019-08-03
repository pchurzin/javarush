package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);

    static {
        threads.add(new ForeverThread());
        threads.add(new InterruptedThread());
        threads.add(new HoorayThread());
        threads.add(new MessageThread());
        threads.add(new NumberThread());
    }

    public static void main(String[] args) {
    }

    public static class ForeverThread extends Thread {
        @Override
        public void run() {
            while (true);
        }
    }

    public static class InterruptedThread extends Thread {

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            }
        }
    }

    public static class HoorayThread extends Thread {

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("Ура");
                    sleep(500);
                }
            } catch (InterruptedException e) {

            }
        }
    }

    public static class MessageThread extends Thread implements Message {

        @Override
        public void showWarning() {
            interrupt();
        }

        @Override
        public void run() {
            while (!isInterrupted());
        }
    }

    public static class NumberThread extends Thread {

        @Override
        public void run() {
            int sum = 0;
            BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
            try {
                while (true) {
                    String s = r.readLine();
                    if ("N".equals(s)) break;
                    int n = Integer.parseInt(s);
                    sum += n;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(sum);
        }
    }
}