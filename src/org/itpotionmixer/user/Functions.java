package org.itpotionmixer.user;

import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.util.Timer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.net.URL;

public class Functions {

    private static final Object[][] POTION_DATA = {{227, 249, "Unfinished Guam"}, {227, 251, "Unfinished Marrentill"},
            {227, 253, "Unfinished Tarromin"}, {227, 255, "Unfinished Harralander"}, {227, 257, "Unfinished Ranarr"},
            {227, 12224, "Unfinished Spirit Weed"}, {227, 14886, "Unfinished Wergali"}, {227, 259, "Unfinished Irit"},
            {227, 211, "Unfinished Avantoe"}, {227, 263, "Unfinished Kwuarm"}, {227, 3000, "Unfinished Snapdragon"},
            {227, 265, "Unfinished Cadantine"}, {227, 2535, "Unfinished Lantadyme"}, {227, 267, "Unfinished Dwarf Weed"},
            {227, 271, "Unfinished Torstol"}, {227, 21676, "Unfinished Fellstalk"}, {91, 221, "Attack Potion"},
            {93, 235, "Antipoison"}, {95, 225, "Strength Potion"}, {97, 223, "Restore Potion"}, {97, 1975, "Energy Potion"},
            {99, 239, "Defence Potion"}, {3002, 2152, "Agility Potion"}, {97, 9736, "Combat Potion"}, {99, 231, "Prayer Potion"},
            {101, 221, "Super Attack Potion"}, {101, 235, "Super Antipoison"}, {103, 231, "Fishing Potion"},
            {103, 2970, "Super Energy Potion"}, {103, 10111, "Hunter Potion"}, {105, 225, "Super Strength Potion"},
            {14856, 11525, "Fletching Potion"}, {105, 241, "Weapon Poison"}, {3004, 223, "Super Restore Potion"},
            {107, 239, "Super Defence"}, {2483, 241, "Antifire"}, {109, 245, "Ranging Potion"}, {2483, 3138, "Magic Potion"},
            {111, 247, "Zamorak Brew"}, {3002, 6693, "Saradomin Brew"}, {21628, 21622, "Prayer Renewal"}, {95, 592, "Serum 207"},
            {145, 261, "Extreme Attack"}, {157, 267, "Extreme Strength"}, {163, 2481, "Extreme Defence"},
            {169, 12539, "Extreme Ranging"}, {3042, 9594, "Extreme Magic"}, {15309, 15313, "Overload"}, {3018, 5972, "Recover Special"}};

    public static int[] getIngredients(String potion) {
        for (int i = 0; i < POTION_DATA.length; i++) {
            if (POTION_DATA[i][2].equals(potion)) {
                return new int[]{(Integer) POTION_DATA[i][0], (Integer) POTION_DATA[i][1]};
            }
        }
        return new int[]{-1, -1};
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
