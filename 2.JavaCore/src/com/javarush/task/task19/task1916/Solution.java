package com.javarush.task.task19.task1916;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) {
        try {
            BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
            String of = r.readLine();
            String mf = r.readLine();
            r.close();

            BufferedReader orig = new BufferedReader(new FileReader(of));
            BufferedReader mod = new BufferedReader(new FileReader(mf));

            String origCurrentLine = nextLine(orig);
            String origNextLine = nextLine(orig);
            String modCurrentLine = nextLine(mod);
            String modNextLine = nextLine(mod);

            while (!(origCurrentLine.isEmpty() && modCurrentLine.isEmpty())) {
                if (origCurrentLine.equals(modCurrentLine)) {
                    lines.add(new LineItem(Type.SAME, origCurrentLine));
                    origCurrentLine = origNextLine;
                    origNextLine = nextLine(orig);
                    modCurrentLine = modNextLine;
                    modNextLine = nextLine(mod);
                } else if (origNextLine.equals(modCurrentLine)) {
                    lines.add(new LineItem(Type.REMOVED, origCurrentLine));
                    origCurrentLine = origNextLine;
                    origNextLine = nextLine(orig);
                } else if (origCurrentLine.equals(modNextLine)) {
                    lines.add(new LineItem(Type.ADDED, modCurrentLine));
                    modCurrentLine = modNextLine;
                    modNextLine = nextLine(mod);
                }
            }

            orig.close();
            mod.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (LineItem item : lines) {
            System.out.println(item);
        }
    }

    private static String nextLine(BufferedReader r) {
        String s = "";
        try {
            s = r.readLine();
            if (s == null) s = "";
        } catch (IOException e) {

        }
        return s;
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }

        @Override
        public String toString() {
            return type + " " + line;
        }
    }
}
