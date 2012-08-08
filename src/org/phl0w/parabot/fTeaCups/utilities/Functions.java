package org.phl0w.parabot.fTeaCups.utilities;

import com.parabot.methods.Skills;

import java.util.Random;

public class Functions {

    final static Random r = new Random();
    private static final int[] XP_TABLE = {0, 0, 83, 174, 276, 388, 512, 650, 801, 969, 1154, 1358, 1584, 1833, 2107,
            2411, 2746, 3115, 3523, 3973, 4470, 5018, 5624, 6291, 7028, 7842, 8740, 9730, 10824, 12031, 13363, 14833,
            16456, 18247, 20224, 22406, 24815, 27473, 30408, 33648, 37224, 41171, 45529, 50339, 55649, 61512, 67983,
            75127, 83014, 91721, 101333, 111945, 123660, 136594, 150872, 166636, 184040, 203254, 224466, 247886, 273742,
            302288, 333804, 368599, 407015, 449428, 496254, 547953, 605032, 668051, 737627, 814445, 899257, 992895,
            1096278, 1210421, 1336443, 1475581, 1629200, 1798808, 1986068, 2192818, 2421087, 2673114, 2951373, 3258594,
            3597792, 3972294, 4385776, 4842295, 5346332, 5902831, 6517253, 7195629, 7944614, 8771558, 9684577, 10692629,
            11805606, 13034431, 14391160, 15889109, 17542976, 19368992, 21385073, 23611006, 26068632, 28782069,
            31777943, 35085654, 38737661, 42769801, 47221641, 52136869, 57563718, 63555443, 70170840, 77474828,
            85539082, 94442737, 104273167};

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

    public static String getTimeTillLevel(int skill, int expGained, int expPerHour) {
        int currentLevel = Skills.getLevel(skill);
        int expTillNextLevel = Skills.getExperienceRemaining(skill);
        if (expGained > 0) {
            return format((long) ((double) expTillNextLevel / (double) expPerHour * 3600000));
        }
        return "0:0:0";
    }

    public static int getPercentToNextLevel(int index) {
        int lvl = Skills.getLevel(index);
        if (lvl == 99) {
            return 100;
        }
        int xpTotal = XP_TABLE[lvl + 1] - XP_TABLE[lvl];
        if (xpTotal == 0) {
            return 0;
        }
        int xpDone = Skills.getCurrentExp(index) - XP_TABLE[lvl];
        return 100 * xpDone / xpTotal;
    }

}
