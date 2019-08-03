package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {
    private static AtomicInteger priority = new AtomicInteger(0);

    public MyThread() {
        initPriority();
    }

    public MyThread(Runnable target) {
        super(target);
        initPriority();
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        initPriority();
    }

    public MyThread(String name) {
        super(name);
        initPriority();
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        initPriority();
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        initPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        initPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        initPriority();
    }

    private void initPriority() {
        int p = priority.getAndIncrement();
        p = (p % 10) + 1;
        int maxPriority = getThreadGroup().getMaxPriority();
        if (p > maxPriority) p = maxPriority;
        setPriority(p);
    }
}
