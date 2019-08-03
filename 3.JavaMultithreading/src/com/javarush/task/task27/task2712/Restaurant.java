package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        Waiter waiter = new Waiter();
        


        Cook cook1 = new Cook("Amigo");
        Cook cook2 = new Cook("Second cook");
        cook1.addObserver(waiter);
        cook2.addObserver(waiter);
        cook1.setQueue(orderQueue);
        cook2.setQueue(orderQueue);

        Thread cook1Thread =  new Thread(cook1);
        Thread cook2Thread =  new Thread(cook2);
//        cook1Thread.setDaemon(true);
//        cook2Thread.setDaemon(true);
        cook1Thread.start();
        cook2Thread.start();
//        StatisticManager.getInstance().register(cook1);
//        StatisticManager.getInstance().register(cook2);

        List<Tablet> tablets = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Tablet tablet = new Tablet(i);
            tablet.setQueue(orderQueue);
            tablets.add(tablet);
        }

        Thread thread = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();

        cook1Thread.interrupt();
        cook2Thread.interrupt();

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
    }
}
