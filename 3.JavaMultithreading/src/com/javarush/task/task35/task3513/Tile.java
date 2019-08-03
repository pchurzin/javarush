package com.javarush.task.task35.task3513;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Tile {
    private static final Color FONT_DARK = new Color(0x776e65);
    private static final Color FONT_LIGHT = new Color(0xf9f6f2);
    private static final Color TILE_DEFAULT = new Color(0xff0000);
    private static final Map<Integer, Color> TILE_COLORS = new HashMap<>();

    static {
        TILE_COLORS.put(0, new Color(0xcdc1b4));
        TILE_COLORS.put(2, new Color(0xeee4da));
        TILE_COLORS.put(4, new Color(0xede0c8));
        TILE_COLORS.put(8, new Color(0xf2b179));
        TILE_COLORS.put(16, new Color(0xf59563));
        TILE_COLORS.put(32, new Color(0xf67c5f));
        TILE_COLORS.put(64, new Color(0xf65e3b));
        TILE_COLORS.put(128, new Color(0xedcf72));
        TILE_COLORS.put(256, new Color(0xedcc61));
        TILE_COLORS.put(512, new Color(0xedc850));
        TILE_COLORS.put(1024, new Color(0xedc53f));
        TILE_COLORS.put(2048, new Color(0xedc22e));
    }

    int value;

    public Tile(int value) {
        this.value = value;
    }

    public Tile() {
        this(0);
    }

    public boolean isEmpty() {
        return value == 0;
    }

    public Color getFontColor() {
        return value < 16 ? FONT_DARK : FONT_LIGHT;
    }

    public Color getTileColor() {
        return TILE_COLORS.getOrDefault(value, TILE_DEFAULT);
    }
}
