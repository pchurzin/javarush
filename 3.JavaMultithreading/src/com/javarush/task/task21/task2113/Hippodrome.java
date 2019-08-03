package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    private List<Horse> horses;
    static Hippodrome game;

    public static void main(String[] args) {
        game = new Hippodrome(new ArrayList<Horse>());
        game.getHorses().add(new Horse("horse1", 3, 0));
        game.getHorses().add(new Horse("horse2", 3, 0));
        game.getHorses().add(new Horse("horse3", 3, 0));
        game.run();
        game.printWinner();
    }

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            move();
            print();
            try {
                Thread.sleep(200);
            }
            catch (InterruptedException e) {

            }
        }
    }

    public void move() {
        for (Horse horse : getHorses()) {
            horse.move();
        }
    }

    public void print() {
        for (Horse horse : getHorses()) {
            horse.print();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public Horse getWinner() {
        if (horses.isEmpty()) return null;
        Horse winner = horses.get(0);
        for (Horse horse : horses) {
            if (horse.getDistance() > winner.getDistance()) winner = horse;
        }
        return winner;
    }

    public void printWinner() {
        System.out.println("Winner is " + getWinner().getName() + "!");
    }
}
