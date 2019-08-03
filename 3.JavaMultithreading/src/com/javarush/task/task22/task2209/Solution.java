package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) {
        //...
        String[] words = new String[1];
        try {
            ArrayList<String> wordlist = new ArrayList<>();
            BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
            Scanner in = new Scanner(new FileReader(r.readLine()));
            r.close();
//            in.useDelimiter("\\s");
            while (in.hasNext()) {
                wordlist.add(in.next());
            }
            in.close();
            words = wordlist.toArray(words);
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder result = getLine(words);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        if (words == null || words.length == 0) return new StringBuilder();
        Arrays.sort(words);
        StringBuilder result = new StringBuilder();
        StringBuilder rest = new StringBuilder();
        result.append(words[0]);
        for (int i = 1; i < words.length; i++) {
            if (Character.toLowerCase(words[i].charAt(0)) ==
                Character.toLowerCase(result.charAt(result.length() - 1))) {
                result.append(' ');
                result.append(words[i]);
            } else {
                rest.append(' ');
                rest.append(words[i]);
            }
        }
        result.append(rest);
        return result;
    }
}
