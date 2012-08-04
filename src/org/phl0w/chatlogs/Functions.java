package org.phl0w.chatlogs;

import org.phl0w.chatlogs.logs.LogEntry;
import org.phl0w.chatlogs.logs.LogEntryComparator;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;


/**
 * Provides a bunch of functions to be used with the file merging.
 *
 * @author _phl0w
 */
public class Functions {

    /**
     * @param path An absolute path linking to the file to read.
     * @return A string that contains all the logs.
     * @throws IOException
     */
    public static String readFile(String path) throws IOException {
        Path file = Paths.get(new File(path).toURI());
        List<String> l = Files.readAllLines(file, Charset.defaultCharset());
        StringBuilder sb = new StringBuilder();
        for (String s : l) {
            if (!s.contains("****") && !s.equals("")) {
                sb.append(s).append(Variables.SEPARATOR);
            }
        }
        return sb.toString();
    }


    /**
     * @param f The file to scan through.
     * @return A string that contains all the logs ordered by date.
     * @throws IOException
     */
    public static String updateLines(File f) throws IOException {
        Path file = Paths.get(f.toURI());
        List<String> l = Files.readAllLines(file, Charset.defaultCharset());
        List<LogEntry> entries = new LinkedList<LogEntry>();
        StringBuilder sb = new StringBuilder();
        Date d = null;
        for (String s : l) {
            String time = "2011 " + s.substring(0, 15).toLowerCase();
            if (time.contains("2011 *\t"))
                continue;
            try {
                d = format(time);
            } catch (ParseException pe) {
                pe.printStackTrace();
            }
            entries.add(new LogEntry(d, s.substring(15, s.length())));
        }
        if (f.delete()) {
            if (f.createNewFile()) {
                Variables.log.info("Fixing line #'s for " + f.getName());
            }
        }
        LogEntryComparator lec = new LogEntryComparator();
        Collections.sort(entries, lec);
        ArrayList<Date> used = new ArrayList<Date>();
        for (final LogEntry entry : entries) {
            if (!used.contains(entry.time)) {
                used.add(entry.time);
                String s = entry.time.toString().replaceAll("CEST 2011", "") + entry.entry;
                sb.append(s).append(Variables.SEPARATOR);
            }
        }
        return sb.toString();
    }

    /**
     * @param time The time to parse a date from.
     * @return The parsed date.
     * @throws ParseException
     */
    public static Date format(String time) throws ParseException {
        return Variables.formatter.parse(time);
    }
}
