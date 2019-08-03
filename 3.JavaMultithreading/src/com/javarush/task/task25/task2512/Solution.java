package com.javarush.task.task25.task2512;

import java.util.ArrayList;
import java.util.List;

/*
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        List<String> log = new ArrayList<>();
        for (Throwable th = e; th != null; th = th.getCause()) {
            log.add(th.getClass().getName() + ": " + th.getMessage());
        }
        for (int i = log.size() - 1; i >= 0 ; i--) {
            System.out.println(log.get(i));
        }
    }

    public static void main(String[] args) {
        new Thread() {
            {
                setUncaughtExceptionHandler(new Solution());
            }
            @Override
            public void run() {
                throw new RuntimeException("DEF", new IllegalAccessException("GHI"));
            }
        }.start();
    }
}
