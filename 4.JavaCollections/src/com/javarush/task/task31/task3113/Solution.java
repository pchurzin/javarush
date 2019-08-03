package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?
*/
public class Solution {
    private static int dirCount = -1;
    private static int fileCount = 0;
    private static long fullSize = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Path directory = Paths.get(in.readLine());
        if (!Files.isDirectory(directory)) {
            System.out.println(directory + " - не папка");
            return;
        }

        Files.walkFileTree(directory, new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                fileCount++;
                fullSize += Files.size(file);
                return super.visitFile(file, attrs);
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                dirCount++;
                return super.preVisitDirectory(dir, attrs);
            }
        });

        System.out.println("Всего папок - " + dirCount);
        System.out.println("Всего файлов - " + fileCount);
        System.out.println("Общий размер - " + fullSize);
    }
}
