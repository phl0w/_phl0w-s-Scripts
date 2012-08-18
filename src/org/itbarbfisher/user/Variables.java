package org.itbarbfisher.user;

import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Tile;

import java.util.regex.Pattern;

public class Variables {

    public static int caughtSturgeons;
    public static int caughtSalmons;
    public static int caughtTrouts;
    public static int startFishingLevel;
    public static int startFishingExp;
    public static int startAgilityLevel;
    public static int startAgilityExp;
    public static int pouch = -1;
    public static int urnsCompleted;
    public static long startTime;
    public static boolean isDropping;
    public static boolean quickDrop = true;
    public static boolean urns = false;
    public static boolean guiInitialized;
    public static String status = "waiting for gui";
    public static Timer inactivityTimer;
    public static Tile startTile = null;
    public static final Pattern UPDATER_VERSION_PATTERN = Pattern.compile("version\\s*=\\s*([0-9.]+)");
}
