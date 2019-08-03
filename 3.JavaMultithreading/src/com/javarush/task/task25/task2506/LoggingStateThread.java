package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {
    private Thread target;

    public LoggingStateThread(Thread target) {
        this.target = target;
    }

    @Override
    public void run() {
        Thread.State oldState = null;
        Thread.State newState = null;
        while (newState != State.TERMINATED) {
            newState = target.getState();
            if (newState != oldState) {
                System.out.println(newState);
                oldState = newState;
            }
        }
    }
}