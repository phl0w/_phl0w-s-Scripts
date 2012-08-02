package org.phl0w.chatlogs.logs;

import java.util.Comparator;

/**
 * A class supplying a comparator.
 *
 * @author _phl0w
 */
public class LogEntryComparator implements Comparator<LogEntry> {

    /**
     * Provides a Comparator between two LogEntry objects.
     *
     * @param le1 a LogEntry object.
     * @param le2 a LogEntry object.
     * @return A comparison between the two supplied objects.
     * @see LogEntry
     */
    @Override
    public int compare(LogEntry le1, LogEntry le2) {
        return le1.time.compareTo(le2.time);
    }
}
