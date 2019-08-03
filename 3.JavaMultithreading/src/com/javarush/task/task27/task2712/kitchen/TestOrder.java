package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;

public class TestOrder extends Order {
    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected void initDishes() throws IOException {
        int numberOfDishes = (int)(Math.random() * Dish.values().length);

        for (int i = 0; i < numberOfDishes; i++) {
            int dishNumber = (int)(Math.random() * Dish.values().length);
            dishes.add(Dish.values()[dishNumber]);
        }
    }
}
