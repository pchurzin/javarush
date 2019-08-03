package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
Проход по дереву файлов
*/
public class Solution {
    private static List<File> fileList = new ArrayList<>();
    public static void main(String[] args) {
        File resultFile = new File(args[1]);
        String parentPath = resultFile.getParent();
        File newResultFile = new File(parentPath + "/allFilesContent.txt");
        if (FileUtils.isExist(newResultFile)) {
            FileUtils.deleteFile(newResultFile);
        }
        FileUtils.renameFile(resultFile, newResultFile);



        try (OutputStream out = new FileOutputStream(newResultFile)) {
            File start = new File(args[0]);
            processFolder(start);
            fileList.sort(new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });

            for (File file : fileList) {
                try (InputStream in = new FileInputStream(file)) {
                    copy(out, in);
                    out.write('\n');
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copy(OutputStream out, InputStream in) throws IOException {
        byte[] buffer = new byte[50];
        int count = in.read(buffer);
        out.write(buffer, 0, count);
    }

    private static void processFolder(File folder) {
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                processFolder(file);
            }
            if (file.isFile()) {
                if (file.length() > 50) {
                    FileUtils.deleteFile(file);
                } else {
                    fileList.add(file);
                }
            }
        }
    }
}
