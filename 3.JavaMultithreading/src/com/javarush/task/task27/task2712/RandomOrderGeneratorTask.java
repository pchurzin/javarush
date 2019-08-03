package com.javarush.task.task27.task2712;

import java.util.List;

public class RandomOrderGeneratorTask implements Runnable {
    private List<Tablet> tablets;
    private int interval;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
        this.interval = interval;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int tabletNumber = (int) (Math.random() * tablets.size());
                Tablet tablet = tablets.get(tabletNumber);
                tablet.createTestOrder();
                Thread.sleep(interval);
            }
        } catch (InterruptedException ignored) {

        }
    }
}
