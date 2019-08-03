package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable {
    private String name;
    private boolean busy;
    private LinkedBlockingQueue<Order> queue;

    public Cook(String name) {
        this.name = name;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Order order = queue.poll();
                if (order != null) {
                    startCookingOrder(order);
                }
                Thread.sleep(10);
            }
        } catch (InterruptedException ignored) {

        }
    }

    @Override
    public String toString() {
        return name;
    }

    public void startCookingOrder(Order order) {
        busy = true;
        ConsoleHelper.writeMessage("Start cooking - " + order
                + ", cooking time " + order.getTotalCookingTime() + "min");
        try {
            Thread.sleep(order.getTotalCookingTime() * 10);
        } catch (InterruptedException ignored) {

        }
        StatisticManager.getInstance().register(
                new CookedOrderEventDataRow(
                        order.getTablet().toString(),
                        name,
                        order.getTotalCookingTime() * 60,
                        order.getDishes()));
        setChanged();
        notifyObservers(order);
        busy = false;
    }

    public boolean isBusy() {
        return busy;
    }
}
