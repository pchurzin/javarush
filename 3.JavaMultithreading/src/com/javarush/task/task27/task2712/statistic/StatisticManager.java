package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

public class StatisticManager {
    private static StatisticManager ourInstance = new StatisticManager();
    private StatisticStorage statisticStorage;
//    private Set<Cook> cooks = new HashSet<>();

    public static StatisticManager getInstance() {
        return ourInstance;
    }

/*
    public Set<Cook> getCooks() {
        return cooks;
    }
*/

    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }

    /*public void register(Cook cook) {
        cooks.add(cook);
    }*/

    public Map<Date, Long> getProfitAmounts() {
        Map<Date, Long> profitMap = new HashMap<>();
        for (EventDataRow row : statisticStorage.getEvents(EventType.SELECTED_VIDEOS)) {
            VideoSelectedEventDataRow dataRow = (VideoSelectedEventDataRow) row;
            Date date = new StatisticDate(dataRow.getDate().getTime());

            if (profitMap.containsKey(date)) {
                long profit = profitMap.get(date);
                profitMap.put(date, profit + dataRow.getAmount());
            } else {
                profitMap.put(date, dataRow.getAmount());
            }
        }
        return profitMap;
    }

    public Map<Date, Map<String, Integer>> getCookWorkload() {
        Map<Date, Map<String, Integer>> workloadMap = new HashMap<>();
        for (EventDataRow row : statisticStorage.getEvents(EventType.COOKED_ORDER)) {
            CookedOrderEventDataRow dataRow = (CookedOrderEventDataRow) row;
            Date date = new StatisticDate(dataRow.getDate().getTime());
            if (workloadMap.containsKey(date)) {
                Map<String, Integer> cookMap = workloadMap.get(date);
                if (cookMap.containsKey(dataRow.getCookName())) {
                    int time = cookMap.get(dataRow.getCookName());
                    cookMap.put(dataRow.getCookName(), time + dataRow.getTime());
                } else {
                    cookMap.put(dataRow.getCookName(), dataRow.getTime());
                }
            } else {
                Map<String, Integer> cookMap = new HashMap<>();
                cookMap.put(dataRow.getCookName(), dataRow.getTime());
                workloadMap.put(date, cookMap);
            }
        }
        return workloadMap;
    }

    private StatisticManager() {
        statisticStorage = new StatisticStorage();
    }

    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        public StatisticStorage() {
            for (EventType type : EventType.values()) {
                storage.put(type, new ArrayList<>());
            }
        }

        public List<EventDataRow> getEvents(EventType type) {
            return storage.get(type);
        }

        private void put(EventDataRow data) {
            storage.get(data.getType()).add(data);
        }
    }

    private class StatisticDate extends Date {
        public StatisticDate(long date) {
            super(date);
        }

        @Override
        public String toString() {
            return String.format("%n%td-%tb-%tY", this, this, this);
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Date && toString().equals(obj.toString());
        }

        @Override
        public int hashCode() {
            return toString().hashCode();
        }
    }
}
