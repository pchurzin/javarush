package com.javarush.task.task26.task2611;

import java.util.concurrent.ConcurrentHashMap;

public class Producer implements Runnable {
    private ConcurrentHashMap<String, String> map;

    Producer(ConcurrentHashMap map) {
        this.map = map;
    }

    @Override
    public void run() {
        try {
            int i = 1;
            while (true) {
                map.put(String.valueOf(i), "Some text for " + i);
                Thread.sleep(500);
                i++;
            }
        } catch (InterruptedException e) {
            System.out.println("[" + Thread.currentThread().getName() + "] thread was terminated");
        }
    }
}
