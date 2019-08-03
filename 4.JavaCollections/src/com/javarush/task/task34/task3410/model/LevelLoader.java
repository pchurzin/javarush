package com.javarush.task.task34.task3410.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LevelLoader {

    private Path levels;
    private List<String> lvlDesc;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        level = (level - 1) % 60 + 1;
        
        try {
            lvlDesc = Files.readAllLines(levels);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        int currentLevel = 0;
        for (int i = 0; i < lvlDesc.size(); i++) {
            if (lvlDesc.get(i).equals("*************************************")) {
                currentLevel++;
            }
            if (currentLevel == level) {
                return loadLevelFromLine(i);
            }
        }

        return null;
    }

    private GameObjects loadLevelFromLine(int line) {
        if (line + 8 >= lvlDesc.size()) {
            return null;
        }
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;
        int size = Model.FIELD_CELL_SIZE;


        line += 8;
        int row = 0;
        while (line < lvlDesc.size() && !lvlDesc.get(line).isEmpty()) {
            String currentLine = lvlDesc.get(line);
            for (int col = 0; col < currentLine.length(); col++) {
                switch (currentLine.charAt(col)) {
                    case 'X':
                        walls.add(new Wall(size / 2 + size * col, size / 2 + size * row));
                        break;
                    case '*':
                        boxes.add(new Box(size / 2 + size * col, size / 2 + size * row));
                        break;
                    case '.':
                        homes.add(new Home(size / 2 + size * col, size / 2 + size * row));
                        break;
                    case '&':
                        boxes.add(new Box(size / 2 + size * col, size / 2 + size * row));
                        homes.add(new Home(size / 2 + size * col, size / 2 + size * row));
                        break;
                    case '@':
                        player = new Player(size / 2 + size * col, size / 2 + size * row);
                        break;
                }
            }
            row++;line++;
        }
        return new GameObjects(walls, boxes, homes, player);
    }
}
