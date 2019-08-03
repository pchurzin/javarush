package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        for (Word w : detectAllWords(crossword, "home", "same", "emoh", "prek", "ran")) {
            System.out.println(w);
        }
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> wordList = new ArrayList<>();
        for (String word : words) {
            wordList.addAll(detectWord(crossword, word));
        }
        return wordList;
    }

    private static List<Word> detectWord(int[][] crossword, String word) {
        int numRows = crossword.length;
        int numCols = crossword[0].length;
        List<Word> wordList = new ArrayList<>();

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                wordList.addAll(detectWord(crossword, word, row, col));
            }
        }
        return wordList;
    }

    private static List<Word> detectWord(int[][] crossword, String word, int startRow, int startCol) {
        int numRows = crossword.length;
        int numCols = crossword[0].length;
        List<Word> wordList = new ArrayList<>();
        //horizontal forward
        if (startCol + word.length() <= numCols) {
            boolean wordFound = true;
            for (int i = 0; i < word.length(); i++) {
                if (crossword[startRow][startCol + i] != word.charAt(i)) {
                    wordFound = false;
                    break;
                }
            }
            if (wordFound) {
                Word w = new Word(word);
                w.setStartPoint(startCol, startRow);
                w.setEndPoint(startCol + word.length() - 1, startRow);
                wordList.add(w);
            }
        }

        //horizontal backward
        if (startCol - word.length() >= -1) {
            boolean wordFound = true;
            for (int i = 0; i < word.length(); i++) {
                if (crossword[startRow][startCol - i] != word.charAt(i)) {
                    wordFound = false;
                    break;
                }
            }
            if (wordFound) {
                Word w = new Word(word);
                w.setStartPoint(startCol, startRow);
                w.setEndPoint(startCol - word.length() + 1, startRow);
                wordList.add(w);
            }
        }

        //vertical forward
        if (startRow + word.length() <= numRows) {
            boolean wordFound = true;
            for (int i = 0; i < word.length(); i++) {
                if (crossword[startRow + i][startCol] != word.charAt(i)) {
                    wordFound = false;
                    break;
                }
            }
            if (wordFound) {
                Word w = new Word(word);
                w.setStartPoint(startCol, startRow);
                w.setEndPoint(startCol, startRow + word.length() - 1);
                wordList.add(w);
            }
        }

        //vertical backward
        if (startRow - word.length() >= -1) {
            boolean wordFound = true;
            for (int i = 0; i < word.length(); i++) {
                if (crossword[startRow - i][startCol] != word.charAt(i)) {
                    wordFound = false;
                    break;
                }
            }
            if (wordFound) {
                Word w = new Word(word);
                w.setStartPoint(startCol, startRow);
                w.setEndPoint(startCol, startRow - word.length() + 1);
                wordList.add(w);
            }
        }

        //top-left-bottom-right diagonal forward
        if (startRow + word.length() <= numRows &&
            startCol + word.length() <= numCols) {
            boolean wordFound = true;
            for (int i = 0; i < word.length(); i++) {
                if (crossword[startRow + i][startCol + i] != word.charAt(i)) {
                    wordFound = false;
                    break;
                }
            }
            if (wordFound) {
                Word w = new Word(word);
                w.setStartPoint(startCol, startRow);
                w.setEndPoint(startCol + word.length() - 1, startRow + word.length() - 1);
                wordList.add(w);
            }
        }
        //top-left-bottom-right diagonal backward
        if (startRow - word.length() >= -1 &&
            startCol - word.length() >= -1) {
            boolean wordFound = true;
            for (int i = 0; i < word.length(); i++) {
                if (crossword[startRow - i][startCol - i] != word.charAt(i)) {
                    wordFound = false;
                    break;
                }
            }
            if (wordFound) {
                Word w = new Word(word);
                w.setStartPoint(startCol, startRow);
                w.setEndPoint(startCol - word.length() + 1, startRow - word.length() + 1);
                wordList.add(w);
            }
        }

        //bottom-left-top-right diagonal forward
        if (startRow - word.length() >= -1 &&
            startCol + word.length() <= numCols) {
            boolean wordFound = true;
            for (int i = 0; i < word.length(); i++) {
                if (crossword[startRow - i][startCol + i] != word.charAt(i)) {
                    wordFound = false;
                    break;
                }
            }
            if (wordFound) {
                Word w = new Word(word);
                w.setStartPoint(startCol, startRow);
                w.setEndPoint(startCol + word.length() - 1, startRow - word.length() + 1);
                wordList.add(w);
            }
        }
        //bottom-left-top-right diagonal backward
        if (startRow + word.length() <= numRows &&
            startCol - word.length() >= -1) {
            boolean wordFound = true;
            for (int i = 0; i < word.length(); i++) {
                if (crossword[startRow + i][startCol - i] != word.charAt(i)) {
                    wordFound = false;
                    break;
                }
            }
            if (wordFound) {
                Word w = new Word(word);
                w.setStartPoint(startCol, startRow);
                w.setEndPoint(startCol - word.length() + 1, startRow + word.length() - 1);
                wordList.add(w);
            }
        }

        return wordList;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
