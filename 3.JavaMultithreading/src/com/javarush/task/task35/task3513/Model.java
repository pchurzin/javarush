package com.javarush.task.task35.task3513;

import java.util.*;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles = new Tile[FIELD_WIDTH][];
    private Stack<Tile[][]> previousStates = new Stack<>();
    private Stack<Integer> previousScores = new Stack<>();
    private boolean isSaveNeeded = true;
    int score;
    int maxTile;

    public Model() {
        resetGameTiles();
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public boolean canMove() {
        Tile[][] dup = duplicateGameTiles(gameTiles);
        int oldScore = score;
        int oldMaxTile = maxTile;

        boolean moved = false;
        outer:
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < FIELD_WIDTH; i++) {
                if (compressTiles(gameTiles[i]) || mergeTiles(gameTiles[i])) {
                    moved = true;
                    break outer;
                }
            }
            rotate();
        }
        if (moved) {
            score = oldScore;
            maxTile = oldMaxTile;
            gameTiles = dup;
        }
        return moved;
    }

    public void resetGameTiles() {
        for (int i = 0; i < FIELD_WIDTH; i++) {
            gameTiles[i] = new Tile[FIELD_WIDTH];
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();

        score = 0;
        maxTile = 2;
    }

    public void left() {
        if (isSaveNeeded) {
            saveState(gameTiles);
        }

        boolean changed = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i])) changed = true;
        }
        if (changed) addTile();
        isSaveNeeded = true;
    }

    public void right() {
        saveState(gameTiles);
        rotate();
        rotate();
        left();
        rotate();
        rotate();
    }

    public void down() {
        saveState(gameTiles);
        rotate();
        left();
        rotate();rotate();rotate();
    }



    public void up() {
        saveState(gameTiles);
        rotate();rotate();rotate();
        left();
        rotate();
    }

    public void saveState(Tile[][] originalState) {
        Tile[][] state = duplicateGameTiles(originalState);
        previousStates.push(state);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollback() {
        if (previousScores.isEmpty() || previousStates.isEmpty()) return;
        gameTiles = previousStates.pop();
        score = previousScores.pop();
    }

    public void randomMove() {
        int choice = (int) (Math.random() * 100) % 4;
        switch (choice) {
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
                break;
        }
    }

    public boolean hasBoardChanged() {
        if (previousStates.isEmpty()) return true;
        Tile[][] previousState = previousStates.peek();
        int previousWeight = 0;
        int currentWeight = 0;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                previousWeight += previousState[i][j].value;
                currentWeight += gameTiles[i][j].value;
            }
        }
        return previousWeight != currentWeight;
    }

    public MoveEfficiency getMoveEfficiency(Move move) {
        move.move();
        MoveEfficiency me;
        if (hasBoardChanged()) {
            me = new MoveEfficiency(getEmptyTiles().size(), score, move);
        } else {
            me = new MoveEfficiency(-1, 0, move);
        }

        rollback();
        return me;
    }

    public void autoMove() {
        PriorityQueue<MoveEfficiency> queue = new PriorityQueue<>(4, Collections.reverseOrder());
        queue.add(getMoveEfficiency(new Move() {
            @Override
            public void move() {
                left();
            }
        }));
        queue.add(getMoveEfficiency(new Move() {
            @Override
            public void move() {
                right();
            }
        }));
        queue.add(getMoveEfficiency(new Move() {
            @Override
            public void move() {
                up();
            }
        }));
        queue.add(getMoveEfficiency(new Move() {
            @Override
            public void move() {
                down();
            }
        }));
        queue.peek().getMove().move();
    }

    private void rotate() {
        Tile[][] newTiles = new Tile[FIELD_WIDTH][];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            newTiles[i] = new Tile[FIELD_WIDTH];
        }
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                newTiles[j][FIELD_WIDTH - i - 1] = gameTiles[i][j];
            }
        }
        gameTiles = newTiles;
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> emptyTiles = new ArrayList<>();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].isEmpty()) {
                    emptyTiles.add(gameTiles[i][j]);
                }
            }
        }
        return emptyTiles;
    }

    private void addTile() {
        List<Tile> emptyTiles = getEmptyTiles();
        if (emptyTiles.isEmpty()) return;
        int i = (int)(Math.random() * emptyTiles.size());
        emptyTiles.get(i).value = (Math.random() < 0.9 ? 2 : 4);
    }

    private boolean compressTiles(Tile[] tiles) {
        Tile[] copy = Arrays.copyOf(tiles,tiles.length);
        Arrays.sort(tiles, new Comparator<Tile>() {
            @Override
            public int compare(Tile o1, Tile o2) {
                if (o1 == null || o2 == null) throw new NullPointerException();
                if (o1.value > 0 && o2.value == 0) return -1;
                if (o1.value == 0 && o2.value > 0) return 1;
                return 0;
            }
        });
        return !Arrays.equals(copy, tiles);
    }

    private boolean mergeTiles(Tile[] tiles) {
        int i = 0;
        boolean changed = false;
        while (i < tiles.length - 1) {
            if (tiles[i].value == tiles[i + 1].value && tiles[i].value != 0) {
                tiles[i].value *= 2;
                tiles[i + 1].value = 0;
                maxTile = tiles[i].value > maxTile ? tiles[i].value : maxTile;
                score += tiles[i].value;
                changed = true;

                i += 2;
            } else {
                i++;
            }
        }
        compressTiles(tiles);
        return changed;
    }

    private Tile[][] duplicateGameTiles(Tile[][] original) {
        Tile[][] dup = new Tile[FIELD_WIDTH][];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            dup[i] = new Tile[FIELD_WIDTH];
            for (int j = 0; j < FIELD_WIDTH; j++) {
                dup[i][j] = new Tile(original[i][j].value);
            }
        }
        return dup;
    }



    public static void main(String[] args) {
        Model model = new Model();
        model.right();
        Tile[] tiles = {new Tile(4), new Tile(0), new Tile(8), new Tile(8)};
        /*System.out.println(model.compressTiles(tiles));
        System.out.println(model.compressTiles(tiles));
        System.out.println(model.mergeTiles(tiles));
        System.out.println(model.mergeTiles(tiles));*/

    }
}
