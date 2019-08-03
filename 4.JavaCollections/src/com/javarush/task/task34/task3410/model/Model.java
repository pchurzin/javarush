package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.Controller;
import com.javarush.task.task34.task3410.controller.EventListener;

import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class Model {
    public static final int FIELD_CELL_SIZE = 20;

    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(
            Paths.get(Controller.class.getResource("../res/levels.txt").getFile()));

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        restartLevel(++currentLevel);
    }

    public void move(Direction direction) {
        if (checkWallCollision(gameObjects.getPlayer(), direction)) {
            return;
        }
        if (checkBoxCollisionAndMoveIfAvaliable(direction)) {
            return;
        }
        Player player = gameObjects.getPlayer();
        switch (direction) {
            case RIGHT:
                player.move(FIELD_CELL_SIZE, 0);
                break;
            case LEFT:
                player.move(-FIELD_CELL_SIZE, 0);
                break;
            case DOWN:
                player.move(0, FIELD_CELL_SIZE);
                break;
            case UP:
                player.move(0, -FIELD_CELL_SIZE);
                break;
        }
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (GameObject wall : gameObjects.getWalls()) {
            if (gameObject.isCollision(wall, direction)) {
                return true;
            }
        }
        return false;
    }

    public void checkCompletion() {
        Set<Home> homes = new HashSet<>(gameObjects.getHomes());
        Set<Box> boxes = new HashSet<>(gameObjects.getBoxes());
        for (Box box : boxes) {
            for (Home home : gameObjects.getHomes()) {
                if (box.getX() == home.getX() && box.getY() == home.getY()) {
                    homes.remove(home);
                }
            }
        }
        if (homes.isEmpty()) {
            eventListener.levelCompleted(currentLevel);
        }
    }

    public boolean checkBoxCollisionAndMoveIfAvaliable(Direction direction) {
        Player player = gameObjects.getPlayer();
        for (Box box : gameObjects.getBoxes()) {
            if (player.isCollision(box, direction)) {
                if (isBoxCanMove(box, direction)) {
                    switch (direction) {
                        case RIGHT:
                            box.move(FIELD_CELL_SIZE, 0);
                            break;
                        case LEFT:
                            box.move(-FIELD_CELL_SIZE, 0);
                            break;
                        case DOWN:
                            box.move(0, FIELD_CELL_SIZE);
                            break;
                        case UP:
                            box.move(0, -FIELD_CELL_SIZE);
                            break;
                    }
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    private boolean isBoxCanMove(Box box, Direction direction) {
        if (checkWallCollision(box, direction)) {
            return false;
        }
        for (Box anotherBox : gameObjects.getBoxes()) {
            if (anotherBox == box) {
                continue;
            }
            if (box.isCollision(anotherBox, direction)) {
                return false;
            }
        }
        return true;
    }
}
