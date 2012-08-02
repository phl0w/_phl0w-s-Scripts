package org.phl0w.chatlogs;

import org.phl0w.chatlogs.logs.LogEntry;
import org.phl0w.chatlogs.logs.LogEntryComparator;

import java.io.*;
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
        FileInputStream fis = new FileInputStream(path);
        DataInputStream in = new DataInputStream(fis);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        StringBuilder sb = new StringBuilder();
        while ((strLine = br.readLine()) != null) {
            if (!strLine.contains("**** LOGGEN") && !strLine.contains("**** LOGGING") && !strLine.equals("")) {
                sb.append(strLine).append(Variables.SEPARATOR);
            }
        }
        br.close();
        in.close();
        fis.close();
        return sb.toString();
    }


    /**
     * @param f The file to scan through.
     * @return A string that contains all the logs ordered by date.
     * @throws IOException
     */
    public static String updateLines(File f) throws IOException {
        List<LogEntry> entries = new LinkedList<LogEntry>();
        FileInputStream fis = new FileInputStream(f.getAbsolutePath());
        DataInputStream dis = new DataInputStream(fis);
        BufferedReader br = new BufferedReader(new InputStreamReader(dis));
        StringBuilder sb = new StringBuilder();
        String line;
        Date date = null;
        while ((line = br.readLine()) != null) {
            String time = "2011 " + line.substring(0, 15);
            try {
                date = Variables.FORMAT.parse(time);
            } catch (Exception e) {
                e.printStackTrace();
            }
            entries.add(new LogEntry(date, line.substring(15)));
        }
        fis.close();
        dis.close();
        br.close();
        if (f.delete()) {
            if (f.createNewFile()) {
                Variables.log.info("Fixing line #'s");
            }
        }
        LogEntryComparator lec = new LogEntryComparator();
        Collections.sort(entries, lec);
        ArrayList<Date> used = new ArrayList<Date>();
        for (final LogEntry entry : entries) {
            if (!used.contains(entry.time)) {
                used.add(entry.time);
                String l = entry.time.toString().replaceAll("CEST 2011", "") + entry.entry;
                sb.append(l).append(Variables.SEPARATOR);
            }
        }
        return sb.toString();
    }
}
