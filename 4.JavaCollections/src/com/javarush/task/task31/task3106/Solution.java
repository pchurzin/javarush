package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.*;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
*/
public class Solution {
    private static byte[] buffer = new byte[1024 * 4];

    public static void main(String[] args) {

        List<String> filePartsNames = new ArrayList<>(Arrays.asList(args).subList(1, args.length));
        Collections.sort(filePartsNames);

        Enumeration<InputStream> input = new Enumeration<InputStream>() {
            @Override
            public boolean hasMoreElements() {
                return !filePartsNames.isEmpty();
            }

            @Override
            public InputStream nextElement() {
                try {
                    InputStream inputStream = new FileInputStream(filePartsNames.remove(0));
                    return inputStream;
//                    return new FileInputStream(filePartsNames.remove(0));
                } catch (FileNotFoundException e) {
                    return null;
                }
            }
        };

        try (ZipInputStream inputStream = new ZipInputStream(new SequenceInputStream(input));
             FileOutputStream outputStream = new FileOutputStream(args[0])) {
            inputStream.getNextEntry();
            copy(inputStream, outputStream);
        } catch (IOException ios) {
            ios.printStackTrace();
        }
    }

    private static void copy(InputStream in, OutputStream out) throws IOException {
        int count;
        while ((count = in.read(buffer)) != -1) {
            out.write(buffer, 0, count);
        }
    }
}
