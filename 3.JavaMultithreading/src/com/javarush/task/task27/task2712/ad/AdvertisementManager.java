package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException {
        List<Advertisement> storageList = storage.list();
        if (storageList == null || storageList.isEmpty()) {
            throw new NoVideoAvailableException();
        }
        List<Advertisement> videos = new ArrayList<>();
        for (Advertisement ad : storageList) {
            if (ad == null || ad.getHits() <= 0 || ad.getDuration() <= 0 || ad.getDuration() > timeSeconds) {
                continue;
            }
            videos.add(ad);
        }

        if (videos.isEmpty()) {
            throw new NoVideoAvailableException();
        }

        List<List<Advertisement>> maxAmountLists = getMaxAmountList(videos, timeSeconds);

        if (maxAmountLists.isEmpty()) {
            throw new NoVideoAvailableException();
        }
        //now we have list with maximum profit
        List<Advertisement> chosenList = Collections.max(maxAmountLists, new Comparator<List<Advertisement>>() {
            @Override
            public int compare(List<Advertisement> o1, List<Advertisement> o2) {
                int timeCompare = Integer.compare(getTotalTime(o1), getTotalTime(o2));
                if (timeCompare != 0) {
                    return timeCompare;
                }

                return -Integer.compare(o1.size(), o2.size());
            }
        });

        Collections.sort(chosenList, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                int amountDiff = Long.compare(o1.getAmountPerOneDisplaying(),
                        o2.getAmountPerOneDisplaying());
                if (amountDiff != 0) {
                    return -amountDiff;
                }

                long oneSecondCost1 = o1.getAmountPerOneDisplaying() * 1000 / o1.getDuration();
                long oneSecondCost2 = o2.getAmountPerOneDisplaying() * 1000 / o2.getDuration();

                return Long.compare(oneSecondCost1, oneSecondCost2);
            }
        });

        StatisticManager.getInstance().register(
                new VideoSelectedEventDataRow(chosenList, getTotalAmount(chosenList), getTotalTime(chosenList)));

        for (Advertisement ad : chosenList) {
            ConsoleHelper.writeMessage(ad.getName() + " is displaying... "
                    + ad.getAmountPerOneDisplaying() + ", "
                    + ad.getAmountPerOneDisplaying() * 1000 / ad.getDuration());
            ad.revalidate();
        }
    }


    private List<List<Advertisement>> getMaxAmountList(List<Advertisement> list, int time) {
        List<List<Advertisement>> result = new ArrayList<>();
//        result.add(new ArrayList<>());

        if (list == null || list.isEmpty() || time <= 0) {
            return result;
        }
        if (getTotalTime(list) <= time) {
            result.add(list);
            return result;
        }
        List<Advertisement> subList = new ArrayList<>(list);
        Advertisement last = subList.remove(subList.size() - 1);
        if (last.getDuration() > time) {
            return result;
        }

        List<List<Advertisement>> list1 = getMaxAmountList(subList, time - last.getDuration());
        if (list1.isEmpty()) {
            List<Advertisement> lastElementList = new ArrayList<>();
            lastElementList.add(last);
            list1.add(lastElementList);
        } else {
            for (List<Advertisement> l : list1) {
                l.add(last);
            }
        }

        List<List<Advertisement>> list2 = getMaxAmountList(subList, time);

        List<List<Advertisement>> helper = new ArrayList<>();
        helper.addAll(list1);
        helper.addAll(list2);

        Collections.sort(helper, new Comparator<List<Advertisement>>() {
            @Override
            public int compare(List<Advertisement> o1, List<Advertisement> o2) {
                return -Long.compare(getTotalAmount(o1), getTotalAmount(o2));
            }
        });

        if (helper.isEmpty()) return result;

        long maxAmount = getTotalAmount(helper.get(0));
        for (List<Advertisement> l : helper) {
            if (getTotalAmount(l) == maxAmount) {
                result.add(l);
            } else {
                break;
            }
        }

        return result;
    }

    private long getTotalAmount(List<Advertisement> list) {
        long total = 0;
        for (Advertisement ad : list) {
            total += ad.getAmountPerOneDisplaying();
        }
        return total;
    }

    private int getTotalTime(List<Advertisement> list) {
        int total = 0;
        for (Advertisement ad : list) {
            total += ad.getDuration();
        }
        return total;
    }
}
