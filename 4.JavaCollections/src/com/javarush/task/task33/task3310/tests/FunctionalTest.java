package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class FunctionalTest extends TestCase {

    public void testStorage(Shortener shortener) {
        String string1 = Helper.generateRandomString();
        String string2 = new String(string1);
        String string3 = Helper.generateRandomString();

        Long id1 = shortener.getId(string1);
        Long id2 = shortener.getId(string2);
        Long id3 = shortener.getId(string3);
        String out1 = shortener.getString(id1);
        String out2 = shortener.getString(id2);
        String out3 = shortener.getString(id3);

        Assert.assertNotEquals(id1, id3);
        Assert.assertNotEquals(id2, id3);
        Assert.assertEquals(id1, id2);


        Assert.assertEquals(string1, out1);
        Assert.assertEquals(string2, out2);
        Assert.assertEquals(string3, out3);
    }

    @Test
    public void testHashMapStorageStrategy() {
        Shortener shortener = new Shortener(new HashMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy() {
        Shortener shortener = new Shortener(new OurHashMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy() {
        Shortener shortener = new Shortener(new FileStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy() {
        Shortener shortener = new Shortener(new HashBiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy() {
        Shortener shortener = new Shortener(new DualHashBidiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy() {
        Shortener shortener = new Shortener(new OurHashBiMapStorageStrategy());
        testStorage(shortener);
    }
}
