package org.phl0w.parabot.fTeaCups.utilities;

import java.util.Random;

public class Functions {

    final static Random r = new Random();

    public static void sleepBetween(int min, int max) {
        sleep(r.nextInt(max - min) + min);
    }

    public static int randomBetween(int min, int max) {
        return r.nextInt((max - min) + min);
    }

    private static void sleep(int arg) {
        try {
            Thread.sleep(arg);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public static boolean waitFor(long timeout, Condition c) {
        final long startTime = System.currentTimeMillis();
        while ((System.currentTimeMillis() - startTime) < timeout && !c.validate()) {
            sleep(50);
        }
        return c.validate();
    }

    public static String format(final long time) {
        final StringBuilder t = new StringBuilder();
        final long total_secs = time / 1000;
        final long total_mins = total_secs / 60;
        final long total_hrs = total_mins / 60;
        final int secs = (int) total_secs % 60;
        final int mins = (int) total_mins % 60;
        final int hrs = (int) total_hrs % 24;
        if (hrs < 10) {
            t.append("0");
        }
        t.append(hrs);
        t.append(":");
        if (mins < 10) {
            t.append("0");
        }
        t.append(mins);
        t.append(":");
        if (secs < 10) {
            t.append("0");
        }
        t.append(secs);
        return t.toString();
    }

}
