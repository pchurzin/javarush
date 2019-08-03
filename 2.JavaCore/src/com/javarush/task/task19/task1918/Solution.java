package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
*/

import java.io.*;
import java.util.*;

public class Solution {
    private static List<TagInfo> tagInfoList = new ArrayList<>();
    private static String text = "";
    private static String tag = "";
    public static void main(String[] args) {
        tag = args[0];
        String fn ;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            fn = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        StringBuffer sb = new StringBuffer();
        try (BufferedReader fin = new BufferedReader(new FileReader(fn))) {
            while (true) {
                String line = fin.readLine();
                if (line == null) break;
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        text = sb.toString();

        fillTagInfoList(tag);
        for (int i = 0; i < tagInfoList.size(); i++) {
            if (tagInfoList.get(i).levelDiff > 0) {
                printTag(i);
            }
        }
    }

    private static void printTag(final int tagInfoIndex) {
        int start = tagInfoList.get(tagInfoIndex).offset;

        int level = 0;
        int i = tagInfoIndex;
        for (; i < tagInfoList.size(); i++) {
            level += tagInfoList.get(i).levelDiff;
            if (level == 0) break;
        }

        int end = tagInfoList.get(i).offset;
        System.out.println(text.substring(start, end + tag.length() + 3));
    }

    private static void fillTagInfoList(String tag) {
        int index = -1;
        while (true) {
            index = text.indexOf("<" + tag, index + 1);
            if (index == -1) break;
            tagInfoList.add(new TagInfo(1, index));
        }

        while (true) {
            index = text.indexOf("</" + tag, index + 1);
            if (index == -1) break;
            tagInfoList.add(new TagInfo(-1, index));
        }

        Collections.sort(tagInfoList, new Comparator<TagInfo>() {
            @Override
            public int compare(TagInfo o1, TagInfo o2) {
                return o1.offset - o2.offset;
            }
        });
    }


    private static class TagInfo {
        public int levelDiff;
        public int offset;

        public TagInfo(int levelDiff, int offset) {
            this.levelDiff = levelDiff;
            this.offset = offset;
        }
    }
}
