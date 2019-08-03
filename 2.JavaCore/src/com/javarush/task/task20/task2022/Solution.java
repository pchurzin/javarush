package com.javarush.task.task20.task2022;

import java.io.*;

/* 
Переопределение сериализации в потоке
*/
public class Solution implements Serializable, AutoCloseable {
    transient private FileOutputStream stream;
    private String fileName;

    public Solution(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        this.stream = new FileOutputStream(this.fileName);
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        stream = new FileOutputStream(this.fileName, true);
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) {
        try {
            Solution solution = new Solution("/home/cpv/work/javarush/2022");
            solution.writeObject("test string");
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("/home/cpv/work/javarush/2022.obj"));
            out.writeObject(solution);
            out.close();
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("/home/cpv/work/javarush/2022.obj"));
            Solution loadedSolution = (Solution)in.readObject();
            in.close();
            loadedSolution.writeObject("test string2");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
