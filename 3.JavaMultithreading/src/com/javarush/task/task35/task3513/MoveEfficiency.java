package com.javarush.task.task35.task3513;

public class MoveEfficiency implements Comparable<MoveEfficiency> {
    private int numberOfEmptyTiles;
    private int score;
    private Move move;

    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    @Override
    public int compareTo(MoveEfficiency o) {
        if (o == null) throw new NullPointerException();
        if (this == o) return 0;
//        if (!getClass().getName().equals(o.getClass().getName())) throw new ClassCastException();

        int emptyTilesDiff = Integer.compare(numberOfEmptyTiles, o.numberOfEmptyTiles);
        if (emptyTilesDiff != 0) return emptyTilesDiff;
        return Integer.compare(score, o.score);
    }
}
