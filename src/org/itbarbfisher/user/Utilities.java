package org.itbarbfisher.user;

import org.itbarbfisher.iTBarbFisher;
import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.Item;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;

public class Utilities {

    public static NPC getNearestSpot() {
        return NPCs.getNearest(new Filter<NPC>() {
            @Override
            public boolean accept(NPC n) {
                return n != null && n.getId() == 2722 && Calculations.distance(Variables.startTile, n.getLocation()) <= 20;
            }
        });
    }

    public static Item getBait() {
        return Inventory.getCount(313) > 0 ? Inventory.getItem(313) : (Inventory.getCount(314) > 0 ? Inventory.getItem(314) : null);
    }

    public static boolean waitFor(long timeout, Condition condition) {
        Timer timer = new Timer(timeout);
        while (timer.isRunning() && !condition.validate()) {
            Time.sleep(50);
        }
        return condition.validate();
    }

    public static void doUpdates(String url) {
        try {
            double currentVersion = getVersion();
            double newVersion = -1;
            URL site = new URL(url);
            BufferedReader in = new BufferedReader(new InputStreamReader(site.openStream()));
            String line;
            Matcher m;
            while ((line = in.readLine()) != null) {
                if ((m = Variables.UPDATER_VERSION_PATTERN.matcher(line)).find()) {
                    newVersion = Double.parseDouble(m.group(1));
                    break;
                }
            }
            if (newVersion < 0) {
                System.out.println("Unable to find the new version number. Update failed");
            } else if (currentVersion == newVersion) {
                System.out.println("You already have the latest version of the script.");
            } else {
                System.out.println("You do not have the most recent version! Check the forums for version " + newVersion);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double getVersion() {
        return iTBarbFisher.class.getAnnotation(Manifest.class).version();
    }

    private static Object[][] data = {{"None", -1}, {"Granite crab (16 summoning)", 12009}, {"Ibis (56 summoning)", 12531},
            {"Granite lobster (74 summoning)", 12069}};

    public static int getPouch(String str) {
        for (Object[] aData : data) {
            if (aData[0].equals(str)) {
                return (Integer) aData[1];
            }
        }
        return -1;
    }

    public static String getTimeTillLevel(int skill, int expGained, int expPerHour) {
        int currentLevel = Skills.getLevel(skill);
        if (currentLevel == 99) {
            return "n/a";
        }
        int expTillNextLevel = Skills.getExperienceToLevel(skill, currentLevel + 1);
        if (expGained > 0) {
            return Time.format((long) ((double) expTillNextLevel / (double) expPerHour * 3600000));
        }
        return "0:0:0";
    }

    public static int getPercentToNextLevel(int index) {
        int lvl = Skills.getRealLevel(index);
        if (lvl == 99) {
            return 100;
        }
        int xpTotal = Skills.XP_TABLE[lvl + 1] - Skills.XP_TABLE[lvl];
        if (xpTotal == 0) {
            return 0;
        }
        int xpDone = Skills.getExperience(index) - Skills.XP_TABLE[lvl];
        return 100 * xpDone / xpTotal;
    }
}
