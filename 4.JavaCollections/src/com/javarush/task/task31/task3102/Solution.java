package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> list = new ArrayList<>();
        Queue<File> directories = new LinkedList<>();
        directories.add(new File(root));

        while (directories.size() > 0) {
            File dir = directories.remove();
            for (File file : dir.listFiles()) {
                if (file.isDirectory()) {
                    directories.add(file);
                } else if (file.isFile()) {
                    list.add(file.getAbsolutePath());
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        
    }
}
