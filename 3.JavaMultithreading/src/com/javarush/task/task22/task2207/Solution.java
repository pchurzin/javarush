package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        try (
            BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        ) {
            String fname = r.readLine();
            Scanner in = new Scanner(new FileReader(fname));
//            in.useDelimiter("\\s");
            while (in.hasNext()) {
                words.add(in.next());
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean[] isWordUsed = new boolean[words.size()];
        for (int i = 0; i < words.size(); i++) {
            if (isWordUsed[i]) continue;
            for (int j = i + 1; j < words.size(); j++) {
                if (isWordUsed[j]) continue;
                String word1 = words.get(i);
                StringBuilder word2 = new StringBuilder(words.get(j)).reverse();
                if (word1.equals(word2.toString())) {
                    Pair pair = new Pair();
                    pair.first = word1;
                    pair.second = words.get(j);
                    result.add(pair);
                    isWordUsed[i] = true;
                    isWordUsed[j] = true;
                    break;
                }
            }
        }
        for (Pair p : result) {
            System.out.println(p);
        }
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
