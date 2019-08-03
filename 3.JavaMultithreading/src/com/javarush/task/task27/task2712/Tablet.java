package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.TestOrder;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.NoAvailableVideoEventDataRow;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet {
    final int number;
    static final Logger logger = Logger.getLogger(Tablet.class.getName());
    private LinkedBlockingQueue<Order> queue;

    public Tablet(int number) {
        this.number = number;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public Order createOrder() {
        Order order = null;
        try {
            order = new Order(this);
        } catch (IOException exception) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }

        processOrder(order);

        return order;
    }

    public void createTestOrder() {
        Order order = null;
        try {
            order = new TestOrder(this);
        } catch (IOException exception) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }

        processOrder(order);
    }

    private void processOrder(Order order) {
        if (order != null && !order.isEmpty()) {
//            setChanged();
//            notifyObservers(order);
            try {
                queue.put(order);
            } catch (InterruptedException ignored) {

            }
            AdvertisementManager manager =
                    new AdvertisementManager(order.getTotalCookingTime() * 60);
            try {
                manager.processVideos();
            } catch (NoVideoAvailableException exception) {
                logger.log(Level.INFO, "No video is available for the order " + order);
                StatisticManager.getInstance().register(
                        new NoAvailableVideoEventDataRow(order.getTotalCookingTime() * 60));
            }
        }
    }

    @Override
    public String toString() {
        return String.format("Tablet{number=%d}", number);
    }
}
