package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();
    private static AdvertisementStorage storage = AdvertisementStorage.getInstance();

    public static StatisticAdvertisementManager getInstance() {
        return ourInstance;
    }

    public List<Advertisement> getActiveAdvertisement() {
        List<Advertisement> list = new ArrayList<>();
        for (Advertisement ad : storage.list()) {
            if (ad.getHits() > 0) {
                list.add(ad);
            }
        }
        return list;
    }

    public List<Advertisement> getInactiveAdvertisement() {
        List<Advertisement> list = new ArrayList<>();
        for (Advertisement ad : storage.list()) {
            if (ad.getHits() <= 0) {
                list.add(ad);
            }
        }
        return list;
    }

    private StatisticAdvertisementManager() {
    }
}
