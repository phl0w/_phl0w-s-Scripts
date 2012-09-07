package org.itherblore.user;

import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.util.Timer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.net.URL;

public class Utilities {

    private enum Herbs {
        GUAM(199),
        MARRENTIL(201),
        TARROMIN(203),
        HARRALANDER(205),
        RANARR(207),
        IRIT(209),
        AVANTOE(211),
        KWUARM(213),
        CADANTINE(215),
        LANTADYME(2485),
        DWARF_WEED(217),
        TORSTOL(221),
        TOADFLAX(3049),
        SPIRIT_WEED(12174),
        WERGALI(14836),
        SNAPDRAGON(3051),
        FELLSTALK(21626);

        int id;

        Herbs(int id) {
            this.id = id;
        }
    }

    private enum Potions {

        UNFINISHED_GUAM(227, 249),
        UNFINISHED_MARRENTILL(227, 251),
        UNFINISHED_TARROMIN(227, 253),
        UNFINISHED_HARRALANDER(227, 255),
        UNFINISHED_RANARR(227, 257),
        UNFINISHED_SPIRIT_WEED(227, 12224),
        UNFINISHED_WERGALI(227, 14886),
        UNFINISHED_IRIT(227, 259),
        UNFINISHED_AVANTOE(227, 211),
        UNFINISHED_KWUARM(227, 263),
        UNFINISHED_SNAPDRAGON(227, 3000),
        UNFINISHED_CADANTINE(227, 265),
        UNFINISHED_LANTADYME(227, 2535),
        UNFINISHED_DWARF_WEED(227, 267),
        UNFINISHED_TORSTOL(227, 271),
        UNFINISHED_FELLSTALK(227, 21676),
        UNFINISHED_TOADFLAX(227, 2998),
        ATTACK_POTION(91, 221),
        ANTIPOISON(93, 235),
        STRENGTH_POTION(95, 225),
        RESTORE_POTION(97, 223),
        ENERGY_POTION(97, 1975),
        DEFENCE_POTION(99, 239),
        AGILITY_POTION(3002, 2152),
        COMBAT_POTION(97, 9736),
        PRAYER_POTION(99, 231),
        SUPER_ATTACK_POTION(101, 221),
        SUPER_ANTIPOISON(101, 235),
        FISHING_POTION(103, 231),
        SUPER_ENERGY_POTION(103, 2970),
        HUNTER_POTION(103, 10111),
        SUPER_STRENGTH_POTION(105, 225),
        FLETCHING_POTION(14856, 11525),
        WEAPON_POISON(105, 241),
        SUPER_RESTORE_POTION(3004, 223),
        SUPER_DEFENCE(107, 239),
        ANTIFIRE(2483, 241),
        RANGING_POTION(109, 245),
        MAGIC_POTION(2483, 3138),
        ZAMORAK_BREW(111, 247),
        SARADOMIN_BREW(3002, 6693),
        PRAYER_RENEWAL(21628, 21622),
        SERUM_207(95, 592),
        SUPER_ANTIFIRE(2454, 4621),
        EXTREME_ATTACK(145, 261),
        EXTREME_STRENGTH(157, 267),
        EXTREME_DEFENCE(163, 2481),
        EXTREME_RANGING(169, 12539),
        EXTREME_MAGIC(3042, 9594),
        OVERLOAD(15309, 15313),
        RECOVER_SPECIAL(3018, 5972),
        SUMMONING_POTION(12181, 12109);
        int primary, secondary;

        Potions(int primary, int secondary) {
            this.primary = primary;
            this.secondary = secondary;
        }
    }

    public static int[] getIngredients(String potion) {
        for (Potions p : Potions.values()) {
            if (p.name().equals(potion.replaceAll((" "), "_").toUpperCase())) {
                return new int[]{p.primary, p.secondary};
            }
        }
        return new int[]{-1, -1};
    }

    public static int getHerb(String herb) {
        for (Herbs h : Herbs.values()) {
            if (h.name().equals(herb.replaceAll((" "), "_").toUpperCase())) {
                return h.id;
            }
        }
        return -1;
    }

    public static boolean waitFor(long timeout, Condition condition) {
        Timer timer = new Timer(timeout);
        while (timer.isRunning() && !condition.validate()) {
            Time.sleep(50);
        }
        return condition.validate();
    }

    public static Image getImage(String url) {
        Image img = null;
        try {
            img = ImageIO.read(new URL(url));
        } catch (Exception ignored) {
        }
        return img;
    }
}
