package org.ittokenexchanger.user;

import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.util.Time;

import java.awt.*;

public class Paint {

    public static void paint(final Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);
        g2d.fill(new Rectangle(Mouse.getX() + 1, Mouse.getY() - 4, 2, 15));
        g2d.fill(new Rectangle(Mouse.getX() - 6, Mouse.getY() + 2, 16, 2));

        int profit = Variables.banked * Variables.profit;
        int profitHour = (int) ((profit) * 3600000D / (System
                .currentTimeMillis() - Variables.startTime));
        int bankedHour = (int) ((Variables.banked) * 3600000D / (System
                .currentTimeMillis() - Variables.startTime));

        g2d.drawString("iTTokenExchanger by _phl0w", 20, 80);
        g2d.drawString("Time running: " + Time.format(System.currentTimeMillis() - Variables.startTime), 20, 100);
        g2d.drawString("Profit: " + profit, 20, 120);
        g2d.drawString("Profit p/h: " + profitHour, 20, 140);
        g2d.drawString("Banked: " + Variables.banked, 20, 160);
        g2d.drawString("Banked p/h: " + bankedHour, 20, 180);
    }
}
