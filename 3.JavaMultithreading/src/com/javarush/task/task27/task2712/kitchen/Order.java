package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes = new ArrayList<>();

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        initDishes();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Dish d : dishes) {
            if (sb.length() > 0) {
                sb.append(", ").append(d);
            } else {
                sb.append("[").append(d);
            }
        }
        sb.append("]");
        return "Your order: " + sb.toString() + " of " + tablet;
    }

    public int getTotalCookingTime() {
        int totalTime = 0;
        for (Dish d : dishes) {
            totalTime += d.getDuration();
        }
        return totalTime;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public Tablet getTablet() {
        return tablet;
    }

    public boolean isEmpty() {
        return dishes.isEmpty();
    }

    protected void initDishes() throws IOException {
        dishes = ConsoleHelper.getAllDishesForOrder();
    }
}
