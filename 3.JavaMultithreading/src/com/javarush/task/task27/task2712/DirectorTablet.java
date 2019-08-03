package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.*;

public class DirectorTablet {
    public void printAdvertisementProfit() {
        Map<Date, Long> profitMap = StatisticManager.getInstance().getProfitAmounts();
        List<Date> dates = new ArrayList<>(profitMap.keySet());
        Collections.sort(dates, Collections.reverseOrder());
        long totalAmount = 0;
        for (Date date : dates) {
            long amount = profitMap.get(date);
            totalAmount += amount;
            ConsoleHelper.writeMessage(String.format("%s - %.2f",
                    date.toString(), amount / 100.0));
        }
        ConsoleHelper.writeMessage(String.format("Total - %.2f", totalAmount / 100.0));
    }

    public void printCookWorkloading() {
        Map<Date, Map<String, Integer>> workloadMap = StatisticManager.getInstance().getCookWorkload();
        List<Date> dates = new ArrayList<>(workloadMap.keySet());
        Collections.sort(dates, Collections.reverseOrder());
        for (Date date : dates) {
            ConsoleHelper.writeMessage(date.toString());
            Map<String, Integer> dateRows = workloadMap.get(date);
            List<String> cookNames = new ArrayList<>(dateRows.keySet());
            Collections.sort(cookNames);
            for (String cookName : cookNames) {
                ConsoleHelper.writeMessage(cookName + " - " + (int)Math.ceil(dateRows.get(cookName) / 60) + " min");
            }
        }
    }

    public void printActiveVideoSet() {
        List<Advertisement> activeVideos = StatisticAdvertisementManager.getInstance().getActiveAdvertisement();
        activeVideos.sort(new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        for (Advertisement ad : activeVideos) {
            ConsoleHelper.writeMessage(String.format("%s - %d", ad.getName(), ad.getHits()));
        }
    }

    public void printArchivedVideoSet() {
        List<Advertisement> activeVideos = StatisticAdvertisementManager.getInstance().getInactiveAdvertisement();
        activeVideos.sort(new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        for (Advertisement ad : activeVideos) {
            ConsoleHelper.writeMessage(ad.getName());
        }
    }
}
