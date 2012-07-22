package org.itplanker;

import org.itplanker.constants.Data;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.util.Time;

import java.awt.*;

public class Paint {

    private static AlphaComposite makeComposite(final float alpha) {
        final int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
    }

    private static final Font title = new Font("Monotype Corsiva", Font.PLAIN, 25);
    private static final Font author = new Font("Monotype Corsiva", Font.PLAIN, 16);
    private static final Font info = new Font("Book Antiqua", Font.PLAIN, 15);

    private static final Shape bg = new Rectangle(10, 23, 250, 155);
    private static final Shape border = new Rectangle(8, 21, 254, 159);

    private static final Composite bgComposite = makeComposite(0.5F);
    private static final Composite borderComposite = makeComposite(1.0F);

    public static void onRepaint(final Graphics g) {

        final Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.GREEN);
        g2d.setComposite(bgComposite);
        g2d.fill(bg);
        g2d.setColor(Color.BLACK);
        g2d.fill(border);
        g2d.setComposite(borderComposite);

        g2d.setColor(Color.WHITE);

        g2d.fill(new Rectangle(Mouse.getX() + 1, Mouse.getY() - 4, 2, 15));
        g2d.fill(new Rectangle(Mouse.getX() - 6, Mouse.getY() + 2, 16, 2));

        final int planked = Variables.planked;
        final int plankedHour = (int) ((planked) * 3600000D / (System.currentTimeMillis() - Variables.startTime));
        final int profit = Data.getProfit();
        final int profitHour = (int) ((profit) * 3600000D / (System.currentTimeMillis() - Variables.startTime));

        g2d.setFont(title);
        g2d.drawString("iTPlanker", 20, 50);
        g2d.setFont(author);
        g2d.drawString("By: _phl0w", 20, 65);

        g2d.setFont(info);
        g2d.drawString("Planks made: " + planked + ".", 20, 85);
        g2d.drawString("Planks made /hour: " + plankedHour + ".", 20, 100);
        g2d.drawString("Profit made: " + profit + ".", 20, 115);
        g2d.drawString("Profit made /hour: " + profitHour + ".", 20, 130);
        g2d.drawString("Status: " + Variables.status + ".", 20, 145);
        g2d.drawString("Time running: " + Time.format((System.currentTimeMillis() - Variables.startTime)) + ".", 20, 160);
    }

}
