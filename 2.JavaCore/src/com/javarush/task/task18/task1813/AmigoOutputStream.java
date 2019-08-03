package com.javarush.task.task18.task1813;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/* 
AmigoOutputStream
*/

public class AmigoOutputStream extends FileOutputStream {
    public static String fileName = "C:/tmp/result.txt";

    public static void main(String[] args) throws FileNotFoundException {
        new AmigoOutputStream(new FileOutputStream(fileName));
    }

    private FileOutputStream original;

    public AmigoOutputStream(FileOutputStream stream) throws FileNotFoundException{
        super(fileName);
        original = stream;
    }

    @Override
    public void write(int b) throws IOException {
        original.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        original.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        original.write(b, off, len);
    }

    @Override
    public void close() throws IOException {
        original.flush();
        original.write("JavaRush © All rights reserved.".getBytes());
        original.close();
    }

    @Override
    public FileChannel getChannel() {
        return original.getChannel();
    }

    @Override
    public void flush() throws IOException {
        original.flush();
    }
}
