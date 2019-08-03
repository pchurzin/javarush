package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return in.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        System.out.println("Choose a dish from menu or type \"exit\" when done:");
        System.out.println(Dish.allDishesToString());
        List<Dish> dishes = new ArrayList<>();
        while (true) {
            String line = readString();
            if ("exit".equals(line)) {
                break;
            }
            try {
                Dish dish = Dish.valueOf(line);
                dishes.add(dish);
            } catch (IllegalArgumentException ignored) {

            }
        }
        return dishes;
    }
}
