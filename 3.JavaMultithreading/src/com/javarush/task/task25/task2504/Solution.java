package com.javarush.task.task25.task2504;

/* 
Switch для нитей
*/
public class Solution {
    public static void processThreads(Thread... threads) {
        //implement this method - реализуйте этот метод
        for (Thread t : threads) {
            switch (t.getState()) {
                case NEW:
                    t.start();
                    break;
                case WAITING:
                case BLOCKED:
                case TIMED_WAITING:
                    t.interrupt();
                    break;
                case RUNNABLE:
                    t.isInterrupted();
                    break;
                case TERMINATED:
                    System.out.println(t.getPriority());
                    break;
            }
        }
    }

    public static void main(String[] args) {
    }
}
