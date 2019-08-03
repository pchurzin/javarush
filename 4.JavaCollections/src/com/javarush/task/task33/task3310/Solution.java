package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        StorageStrategy hashMapStrategy = new HashMapStorageStrategy();
        testStrategy(hashMapStrategy, 10000L);
        StorageStrategy ourHashMapStrategy = new OurHashMapStorageStrategy();
        testStrategy(ourHashMapStrategy, 10000L);
        StorageStrategy fileStorageStrategy = new FileStorageStrategy();
//        ((FileStorageStrategy)fileStorageStrategy).setBucketSizeLimit(500);
        testStrategy(fileStorageStrategy, 200);
        StorageStrategy bimap = new OurHashBiMapStorageStrategy();
        testStrategy(bimap, 10_000L);
        StorageStrategy guava = new HashBiMapStorageStrategy();
        testStrategy(guava, 10_000L);
        StorageStrategy apache = new DualHashBidiMapStorageStrategy();
        testStrategy(apache, 10_000L);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> result = new HashSet<>();
        for (String value : strings) {
            result.add(shortener.getId(value));
        }
        return result;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> result = new HashSet<>();
        for (Long id : keys) {
            result.add(shortener.getString(id));
        }
        return result;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> testStrings = new HashSet<>();
        for (long i = 0; i < elementsNumber; i++) {
            testStrings.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);
        Set<Long> ids = new HashSet<>();
        Date idStartTime = new Date();
        for (String value : testStrings) {
            ids.add(shortener.getId(value));
        }
        Date idEndTime = new Date();
        Set<String> strings = new HashSet<>();
        Date stringsStartTime = new Date();
        for (Long id : ids) {
            strings.add(shortener.getString(id));
        }
        Date stringsEndTime = new Date();
        Helper.printMessage("id generation " + (idEndTime.getTime() - idStartTime.getTime()) + "ms");
        Helper.printMessage("string generation " + (stringsEndTime.getTime() - stringsStartTime.getTime()) + "ms");
        if (testStrings.equals(strings)) {
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }
    }
}
