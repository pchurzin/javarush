package com.javarush.task.task39.task3905;

import java.util.LinkedList;
import java.util.Queue;

public class PhotoPaint {
    public boolean paintFill(Color[][] image, int x, int y, Color desiredColor) {
        int r = y;
        int c = x;
        if (desiredColor == null || r >= image.length || c >= image[r].length || image[r][c] == desiredColor) {
            return false;
        }
        Color initialColor = image[r][c];
        Queue<Point> points = new LinkedList<>();
        points.add(new Point(r, c));
        while (!points.isEmpty()) {
            Point point = points.poll();
            if (point.r > 0 && image[r - 1][c] == initialColor) {
                points.add(new Point(r - 1, c));
            }

            if (point.c > 0 && image[r][c - 1] == initialColor) {
                points.add(new Point(r, c - 1));
            }

            if (point.r < image.length - 1 && image[r + 1][c] == initialColor) {
                points.add(new Point(r + 1, c));
            }

            if (point.c < image[r].length - 1 && image[r][c + 1] == initialColor) {
                points.add(new Point(r, c + 1));
            }
            image[point.r][point.c] = desiredColor;
        }
        return true;
    }

    private static class Point {
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
        int r;
        int c;
    }
}
