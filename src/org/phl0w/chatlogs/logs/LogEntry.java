package org.phl0w.chatlogs.logs;

import java.util.Date;

public class LogEntry implements Comparable<LogEntry> {
    public Date time = null;
    public String entry = "";

    public LogEntry(Date date, String entry) {
        this.time = date;
        this.entry = entry;
    }

    @Override
    public int compareTo(LogEntry le1) {
        return time.compareTo(le1.time);
    }
}
