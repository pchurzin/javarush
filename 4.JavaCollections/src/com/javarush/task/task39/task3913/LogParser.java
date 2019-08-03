package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    private Path logDir;
    private List<LogEntry> logEntries = new LinkedList<>();
    public static final Pattern TWO_PARAMS = Pattern.compile("get\\s+(\\w+)\\s+for\\s+(\\w+)\\s+=\\s+\"(.+?)\"\\s+and\\s+date\\s+between\\s+\"(.+?)\"\\s+and\\s+\"(.+?)\"");
    public static final Pattern ONE_PARAM = Pattern.compile("get\\s+(\\w+)\\s+for\\s+(\\w+)\\s+=\\s+\"(.+?)\"");
    public static final Pattern NO_PARAM = Pattern.compile("get\\s+(\\w+)");
//    private static final Pattern QL_PATTERN = Pattern.compile("get\\s+(\\w+)(?:\\s+for\\s+(\\w+)\\s+=\\s+\"(.+?)\")?", Pattern.CASE_INSENSITIVE);

    public LogParser(Path logDir) {
        this.logDir = logDir;
        try {
            Files.walkFileTree(logDir, new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.toFile().getName().endsWith(".log")) {
                        parseFile(file);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Set<Object> execute(String query) {
        if (query == null) {
            return new HashSet<>();
        }

        if (TWO_PARAMS.matcher(query).matches()) {
            return execute2params(query);
        }

        if (ONE_PARAM.matcher(query).matches()) {
            return execute1param(query);
        }

        if (NO_PARAM.matcher(query).matches()) {
            return executeNoParam(query);
        }

        return new HashSet<>();

        /*Collection<String> fields = Arrays.asList("user", "ip", "status", "event", "date");

        Matcher matcher = QL_PATTERN.matcher(query);
        if (!matcher.matches()) {
            return result;
        }

        String field1 = matcher.group(1).toLowerCase();
        if (!fields.contains(field1)) {
            return result;
        }
        String field2 = matcher.group(2) == null ? null : matcher.group(2).toLowerCase();
        Object value1 = matcher.group(3);
        if ("date".equals(field2)) {
            try {
                value1 = LogEntry.DATE_FORMAT.parse((String) value1);
            } catch (ParseException e) {
                e.printStackTrace();
                return result;
            }
        } else if ("status".equals(field2)) {
            value1 = Status.valueOf((String) value1);
        } else if ("event".equals(field2)) {
            value1 = Event.valueOf((String) value1);
        }

        for (LogEntry entry : logEntries) {
            if (field2 != null) {
                if (entry.getField(field2).equals(value1)) {
                    result.add(entry.getField(field1));
                }
            } else {
                result.add(entry.getField(field1));
            }
        }

        return result;
        */
    }

    private Set<Object> executeNoParam(String query) {
        Set<Object> result = new HashSet<>();
        Matcher matcher = NO_PARAM.matcher(query);
        if (!matcher.matches()) {
            return result;
        }
        String field = matcher.group(1);
        for (LogEntry entry : logEntries) {
            if (entry.getField(field) != null) {
                result.add(entry.getField(field));
            }
        }
        return result;
    }

    private Set<Object> execute1param(String query) {
        Set<Object> result = new HashSet<>();
        Matcher matcher = ONE_PARAM.matcher(query);
        if (!matcher.matches()) {
            return result;
        }
        String field1 = matcher.group(1);
        String field2 = matcher.group(2);
        Object value1 = matcher.group(3);
        if ("date".equals(field2)) {
            try {
                value1 = LogEntry.DATE_FORMAT.parse((String) value1);
            } catch (ParseException e) {
                e.printStackTrace();
                return result;
            }
        } else if ("status".equals(field2)) {
            value1 = Status.valueOf((String) value1);
        } else if ("event".equals(field2)) {
            value1 = Event.valueOf((String) value1);
        }

        for (LogEntry entry : logEntries) {
            if (entry.getField(field2).equals(value1)) {
                result.add(entry.getField(field1));
            }
        }
        return result;
    }

    private Set<Object> execute2params(String query) {
        Set<Object> result = new HashSet<>();
        Matcher matcher = TWO_PARAMS.matcher(query);
        if (!matcher.matches()) {
            return result;
        }

        String field1 = matcher.group(1);
        String field2 = matcher.group(2);
        Object value1 = matcher.group(3);
        Date after = null;
        Date before = null;
        try {
            after = LogEntry.DATE_FORMAT.parse(matcher.group(4));
            before = LogEntry.DATE_FORMAT.parse(matcher.group(5));
        } catch (ParseException e) {
            e.printStackTrace();
            return result;
        }
        if ("date".equals(field2)) {
            try {
                value1 = LogEntry.DATE_FORMAT.parse((String) value1);
            } catch (ParseException e) {
                e.printStackTrace();
                return result;
            }
        } else if ("status".equals(field2)) {
            value1 = Status.valueOf((String) value1);
        } else if ("event".equals(field2)) {
            value1 = Event.valueOf((String) value1);
        }

        for (LogEntry entry : logEntries) {
            if (entry.getField(field2).equals(value1) && isDateInPeriod7(entry.date, after, before)) {
                result.add(entry.getField(field1));
            }
        }

        return result;
    }

    private Set<Status> getStatuses() {
        Set<Status> statuses = new HashSet<>();
        for (LogEntry entry : logEntries) {
            statuses.add(entry.status);
        }
        return statuses;
    }

    private Set<Date> getDates() {
        Set<Date> dates = new HashSet<>();
        for (LogEntry entry : logEntries) {
            dates.add(entry.date);
        }
        return dates;
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        Set<Event> events = new HashSet<>();
        for (LogEntry entry : logEntries) {
            if (isDateInPeriod(entry.date, after, before)) {
                events.add(entry.event);
            }
        }
        return events;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        Set<Event> events = new HashSet<>();
        if (ip == null) {
            return events;
        }
        for (LogEntry entry : logEntries) {
            if (ip.equals(entry.ip) && isDateInPeriod(entry.date, after, before)) {
                events.add(entry.event);
            }
        }
        return events;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        Set<Event> events = new HashSet<>();
        if (user == null) {
            return events;
        }
        for (LogEntry entry : logEntries) {
            if (user.equals(entry.user) && isDateInPeriod(entry.date, after, before)) {
                events.add(entry.event);
            }
        }
        return events;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        Set<Event> events = new HashSet<>();
        for (LogEntry entry : logEntries) {
            if (entry.status == Status.FAILED && isDateInPeriod(entry.date, after, before)) {
                events.add(entry.event);
            }
        }
        return events;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        Set<Event> events = new HashSet<>();
        for (LogEntry entry : logEntries) {
            if (entry.status == Status.ERROR && isDateInPeriod(entry.date, after, before)) {
                events.add(entry.event);
            }
        }
        return events;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        int number = 0;
        for (LogEntry entry : logEntries) {
            if (entry.event == Event.SOLVE_TASK &&
                isDateInPeriod(entry.date, after, before) &&
                entry.taskNumber == task) {
                number++;
            }
        }
        return number;
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        int number = 0;
        for (LogEntry entry : logEntries) {
            if (entry.event == Event.DONE_TASK &&
                isDateInPeriod(entry.date, after, before) &&
                entry.taskNumber == task /*&&
                entry.status == Status.OK*/) {
                number++;
            }
        }
        return number;
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> result = new HashMap<>();
        for (LogEntry entry : logEntries) {
            if (entry.event == Event.SOLVE_TASK &&
                    isDateInPeriod(entry.date, after, before)) {
                if (!result.containsKey(entry.taskNumber)) {
                    result.put(entry.taskNumber, 1);
                } else {
                    result.put(entry.taskNumber, result.get(entry.taskNumber) + 1);
                }
            }
        }
        return result;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> result = new HashMap<>();
        for (LogEntry entry : logEntries) {
            if (entry.event == Event.DONE_TASK &&
                isDateInPeriod(entry.date, after, before)) {
                if (!result.containsKey(entry.taskNumber)) {
                    result.put(entry.taskNumber, 1);
                } else {
                    result.put(entry.taskNumber, result.get(entry.taskNumber) + 1);
                }
            }
        }
        return result;
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        Set<Date> dates = new HashSet<>();
        if (user == null || event == null) {
            return dates;
        }
        for (LogEntry entry : logEntries) {
            if (user.equals(entry.user) &&
                event == entry.event &&
                isDateInPeriod(entry.date, after, before)) {
                dates.add(entry.date);
            }
        }
        return dates;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        Set<Date> dates = new HashSet<>();
        for (LogEntry entry : logEntries) {
            if (entry.status == Status.FAILED && isDateInPeriod(entry.date, after, before)) {
                dates.add(entry.date);
            }
        }
        return dates;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        Set<Date> dates = new HashSet<>();
        for (LogEntry entry : logEntries) {
            if (entry.status == Status.ERROR && isDateInPeriod(entry.date, after, before)) {
                dates.add(entry.date);
            }
        }
        return dates;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        if (user == null) {
            return null;
        }
        Date date = null;
        for (LogEntry entry : logEntries) {
            if (entry.event == Event.LOGIN &&
                user.equals(entry.user) &&
                isDateInPeriod(entry.date, after, before)) {
                if (date == null || date.getTime() > entry.date.getTime()) {
                    date = entry.date;
                }
            }
        }
        return date;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        if (user == null) {
            return null;
        }
        Date date = null;
        for (LogEntry entry : logEntries) {
            if (entry.event == Event.SOLVE_TASK &&
                user.equals(entry.user) &&
                entry.taskNumber == task &&
                isDateInPeriod(entry.date, after, before)) {
                if (date == null || date.getTime() > entry.date.getTime()) {
                    date = entry.date;
                }
            }
        }
        return date;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        if (user == null) {
            return null;
        }
        Date date = null;
        for (LogEntry entry : logEntries) {
            if (entry.event == Event.DONE_TASK &&
                    user.equals(entry.user) &&
                    entry.taskNumber == task &&
                    isDateInPeriod(entry.date, after, before)) {
                if (date == null || date.getTime() > entry.date.getTime()) {
                    date = entry.date;
                }
            }
        }
        return date;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        Set<Date> dates = new HashSet<>();
        if (user == null) {
            return dates;
        }
        for (LogEntry entry : logEntries) {
            if (entry.event == Event.WRITE_MESSAGE &&
                user.equals(entry.user) &&
                isDateInPeriod(entry.date, after, before)) {
                dates.add(entry.date);
            }
        }
        return dates;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        Set<Date> dates = new HashSet<>();
        if (user == null) {
            return dates;
        }
        for (LogEntry entry : logEntries) {
            if (entry.event == Event.DOWNLOAD_PLUGIN &&
                    user.equals(entry.user) &&
                    isDateInPeriod(entry.date, after, before)) {
                dates.add(entry.date);
            }
        }
        return dates;
    }

    @Override
    public Set<String> getAllUsers() {
        Set<String> users = new HashSet<>();
        for (LogEntry entry : logEntries) {
            users.add(entry.user);
        }
        return users;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        Set<String> users = new HashSet<>();
        for (LogEntry entry : logEntries) {
            if (isDateInPeriod(entry.date, after, before)) {
                users.add(entry.user);
            }
        }
        return users.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        Set<Event> events = new HashSet<>();
        if (user == null) {
            return 0;
        }
        for (LogEntry entry : logEntries) {
            if (user.equals(entry.user) && isDateInPeriod(entry.date, after, before)) {
                events.add(entry.event);
            }
        }
        return events.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        Set<String> result = new HashSet<>();
        if (ip == null) {
            return result;
        }
        for (LogEntry entry : logEntries) {
            if (ip.equals(entry.ip) && isDateInPeriod(entry.date, after, before)) {
                result.add(entry.user);
            }
        }
        return result;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        Set<String> users = new HashSet<>();
        for (LogEntry entry : logEntries) {
            if (entry.event == Event.LOGIN && entry.status != Status.ERROR && isDateInPeriod(entry.date, after, before)) {
                users.add(entry.user);
            }
        }
        return users;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        Set<String> users = new HashSet<>();
        for (LogEntry entry : logEntries) {
            if (entry.event == Event.DOWNLOAD_PLUGIN && entry.status == Status.OK && isDateInPeriod(entry.date, after, before)) {
                users.add(entry.user);
            }
        }
        return users;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        Set<String> users = new HashSet<>();
        for (LogEntry entry : logEntries) {
            if (entry.event == Event.WRITE_MESSAGE && entry.status == Status.OK && isDateInPeriod(entry.date, after, before)) {
                users.add(entry.user);
            }
        }
        return users;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        Set<String> users = new HashSet<>();
        for (LogEntry entry : logEntries) {
            if (entry.event == Event.SOLVE_TASK && entry.status != Status.ERROR && isDateInPeriod(entry.date, after, before)) {
                users.add(entry.user);
            }
        }
        return users;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        Set<String> users = new HashSet<>();
        for (LogEntry entry : logEntries) {
            if (entry.event == Event.SOLVE_TASK
                    && entry.status != Status.ERROR
                    && isDateInPeriod(entry.date, after, before)
                    && entry.taskNumber == task) {
                users.add(entry.user);
            }
        }
        return users;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        Set<String> users = new HashSet<>();
        for (LogEntry entry : logEntries) {
            if (entry.event == Event.DONE_TASK /*&& entry.status != Status.ERROR*/ && isDateInPeriod(entry.date, after, before)) {
                users.add(entry.user);
            }
        }
        return users;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        Set<String> users = new HashSet<>();
        for (LogEntry entry : logEntries) {
            if (entry.event == Event.DONE_TASK
                    /*&& entry.status == Status.OK*/
                    && isDateInPeriod(entry.date, after, before)
                    && entry.taskNumber == task) {
                users.add(entry.user);
            }
        }
        return users;
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (LogEntry entry : logEntries) {
            if (isDateInPeriod(entry.date, after, before)) {
                result.add(entry.ip);
            }
        }
        return result;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<String> result = new HashSet<>();
        if (user == null) {
            return result;
        }
        for (LogEntry entry : logEntries) {
            if (user.equals(entry.user) && isDateInPeriod(entry.date, after, before)) {
                result.add(entry.ip);
            }
        }
        return result;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<String> result = new HashSet<>();
        if (event == null) {
            return result;
        }
        for (LogEntry entry : logEntries) {
            if (event == entry.event && isDateInPeriod(entry.date, after, before)) {
                result.add(entry.ip);
            }
        }
        return result;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> result = new HashSet<>();
        if (status == null) {
            return result;
        }
        for (LogEntry entry : logEntries) {
            if (status == entry.status && isDateInPeriod(entry.date, after, before)) {
                result.add(entry.ip);
            }
        }
        return result;
    }



    private boolean isDateInPeriod(Date date, Date after, Date before) {
        if (date == null) {
            throw new NullPointerException();
        }
        if (after != null && after.getTime() > date.getTime()) {
            return false;
        }
        if (before != null && before.getTime()< date.getTime()) {
            return false;
        }
        return true;
    }

    private boolean isDateInPeriod7(Date date, Date after, Date before) {
        if (date == null) {
            throw new NullPointerException();
        }
        if (after != null && after.getTime() >= date.getTime()) {
            return false;
        }
        if (before != null && before.getTime() <= date.getTime()) {
            return false;
        }
        return true;
    }

    private void parseFile(Path file) throws IOException {
        List<String> lines = Files.readAllLines(file);
        for (String line : lines) {
            try {
                logEntries.add(new LogEntry(line));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }


    private static class LogEntry {
//        private static final Pattern PATTERN = Pattern.compile("^(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})\\s+(.+)\\s+(\\d{1,2}\\.\\d{1,2}\\.\\d{4}\\s+\\d{1,2}:\\d{1,2}:\\d{1,2})\\s+(\\w+)\\s+(\\d*)\\b\\s*(\\w+)");
        public static final DateFormat DATE_FORMAT = new SimpleDateFormat("d.M.y H:m:s");

        private String ip;
        private String user;
        private Date date;
        private Event event;
        private int taskNumber;
        private Status status;

        public LogEntry(String logString) {
            parseEntry(logString);
            /*
            Matcher matcher = PATTERN.matcher(logString);
            if (!matcher.matches()) {
                throw new IllegalArgumentException();
            }

            ip = matcher.group(1);
            user = matcher.group(2);
            try {
                date = DATE_FORMAT.parse(matcher.group(3));
            } catch (ParseException e) {
                e.printStackTrace();
                throw new IllegalArgumentException();
            }
            event = Event.valueOf(matcher.group(4));
            if (event == Event.DONE_TASK || event == Event.SOLVE_TASK) {
                taskNumber = Integer.valueOf(matcher.group(5));
            }
            status = Status.valueOf(matcher.group(6));*/
        }

        private void parseEntry(String text) {
            String[] parts = text.split("\t");
            if (parts.length != 5) {
                throw new IllegalArgumentException();
            }
            ip = parts[0];
            user = parts[1];
            try {
                date = DATE_FORMAT.parse(parts[2]);
            } catch (ParseException e) {
                e.printStackTrace();
                throw new IllegalArgumentException();
            }
            String[] eventParts = parts[3].split(" ");
            event = Event.valueOf(eventParts[0]);
            if (eventParts.length == 2 && (event == Event.DONE_TASK || event == Event.SOLVE_TASK)) {
                taskNumber = Integer.valueOf(eventParts[1]);
            }
            status = Status.valueOf(parts[4]);
        }

        public Object getField(String field) {
            if (field == null) {
                return null;
            }
            field = field.toLowerCase();
            /*Collection<String> fields = Arrays.asList("user", "ip", "status", "event", "date");
            if (!fields.contains(field)) {
                return null;
            }*/

            switch (field) {
                case "user":
                    return user;
                case "ip":
                    return ip;
                case "status":
                    return status;
                case "event":
                    return event;
                case "date":
                    return date;
                default:
                    return null;
            }
        }
    }
}
