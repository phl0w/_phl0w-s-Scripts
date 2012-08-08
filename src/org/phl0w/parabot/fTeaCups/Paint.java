package org.phl0w.parabot.fTeaCups;

import com.parabot.input.Mouse;
import com.parabot.methods.Skills;
import org.phl0w.parabot.fTeaCups.utilities.Functions;

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

        final int xpGained = Skills.getCurrentExp(Skills.THIEVING) - Variables.startXp;
        final int xpHour = (int) ((xpGained) * 3600000D / (System.currentTimeMillis() - Variables.startTime));
        final int stolen = Variables.stolen;
        final int stolenHour = (int) ((stolen) * 3600000D / (System.currentTimeMillis() - Variables.startTime));
        final int levelsGained = Skills.getLevel(Skills.THIEVING) - Variables.startLevel;

        g2d.setFont(title);
        g2d.drawString("fTeaCups", 20, 50);
        g2d.setFont(author);
        g2d.drawString("By: flow_hrs", 20, 65);

        g2d.setFont(info);
        g2d.drawString("XP Gained: " + xpGained + ".", 20, 85);
        g2d.drawString("XP Gained/h: " + xpHour + ".", 20, 100);
        g2d.drawString("Levels gained: " + levelsGained + ".", 20, 115);
        g2d.drawString("Stolen: " + stolen + ".", 20, 130);
        g2d.drawString("Stolen/h: " + stolenHour + ".", 20, 145);
        g2d.drawString("Time running: " + Functions.format((System.currentTimeMillis() - Variables.startTime)) + ".", 20, 160);
    }

}

