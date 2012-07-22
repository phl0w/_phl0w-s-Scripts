package org.itpotionmixer.user;

import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Time;

import java.awt.*;

public class Paint {

    private static final Font FONT_TITLE = new Font("Monotype Corsiva", Font.PLAIN, 25);
    private static final Font FONT_AUTHOR = new Font("Monotype Corsiva", Font.PLAIN, 16);
    private static final Font FONT_INFO = new Font("Book Antiqua", Font.PLAIN, 15);

    private static final Shape bg = new Rectangle(10, 23, 250, 155);
    private static final Shape border = new Rectangle(8, 21, 254, 159);

    private static final Composite bgComposite = makeComposite(0.5f);
    private static final Composite borderComposite = makeComposite(1.0f);


    private static AlphaComposite makeComposite(final float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
    }

    public static void onRepaint(Graphics g) {
        final Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.MAGENTA);
        g2d.setComposite(bgComposite);
        g2d.fill(bg);
        g2d.setColor(Color.BLACK);
        g2d.fill(border);
        g2d.setComposite(borderComposite);

        g2d.setColor(Color.WHITE);

        g2d.fill(new Rectangle(Mouse.getX() + 1, Mouse.getY() - 4, 2, 15));
        g2d.fill(new Rectangle(Mouse.getX() - 6, Mouse.getY() + 2, 16, 2));

        final int expGained = Skills.getExperiences()[Skills.HERBLORE] - Variables.startXp;
        final int expHour = (int) ((expGained) * 3600000D / (System.currentTimeMillis() - Variables.startTime));
        final int madeHour = (int) ((Variables.made) * 3600000D / (System.currentTimeMillis() - Variables.startTime));

        g2d.setFont(FONT_TITLE);
        g2d.drawString("iTPotionMixer", 20, 50);

        g2d.setFont(FONT_AUTHOR);
        g2d.drawString("By: _phl0w", 20, 65);

        g2d.setFont(FONT_INFO);
        g2d.drawString("Herblore level: " + Skills.getLevels()[Skills.HERBLORE] + "/" + Variables.startLevel + ".", 20, 85);
        g2d.drawString("Herblore experience gained: " + expGained + ".", 20, 100);
        g2d.drawString("Herblore experience/hour: " + expHour + ".", 20, 115);
        g2d.drawString("Successfully made " + Variables.made + " potions.", 20, 130);
        g2d.drawString("Making " + madeHour + " potions per hour.", 20, 145);
        g2d.drawString("Status: " + Variables.status + ".", 20, 160);
        g2d.drawString("Time running: " + Time.format(System.currentTimeMillis() - Variables.startTime) + ".", 20, 175);
    }
}
