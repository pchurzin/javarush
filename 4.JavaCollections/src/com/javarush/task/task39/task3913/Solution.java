package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
//        LogParser logParser = new LogParser(Paths.get("c:/logs/"));
        LogParser logParser = new LogParser(Paths.get("/home/cpv/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task39/task3913/logs/"));
        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
        System.out.println(logParser.getUniqueIPs(null, new Date()));
        System.out.println(logParser.getIPsForEvent(Event.DONE_TASK, null, null));
        System.out.println(logParser.getIPsForStatus(Status.ERROR, null, null));
        System.out.println(logParser.getIPsForStatus(Status.OK, new Date(), null));
        System.out.println(logParser.getNumberOfUserEvents("Eduard Petrovich Morozko", null, null));
        System.out.println(logParser.execute("get user"));
        System.out.println(logParser.execute("get event for date = \"30.01.2014 12:56:22\""));
        System.out.println(logParser.execute("get date for user = \"Amigo\""));
        System.out.println(logParser.execute("get user for event = \"SOLVE_TASK\""));
        System.out.println(logParser.execute("get user for status = \"ERROR\""));
        System.out.println(logParser.execute("get ip for user = \"Eduard Petrovich Morozko\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\""));
    }
}