package org.phl0w.chatlogs.logs;

import java.util.Date;

/**
 * Allows creation of LogEntry objects.
 *
 * @author _phl0w
 */
public class LogEntry {
    public Date time = null;
    public String entry = "";

    public LogEntry(Date date, String entry) {
        this.time = date;
        this.entry = entry;
    }
}
