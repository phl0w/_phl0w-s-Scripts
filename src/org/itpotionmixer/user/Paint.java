package org.itpotionmixer.user;

import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Time;

import java.awt.*;

public class Paint {

    public static Font font = new Font("Arial", Font.PLAIN, 20);

    public static void onRepaint(Graphics g) {
        final Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);

        g2d.drawImage(Variables.img, 0, 0, null);

        g2d.setFont(font);
        g2d.setColor(Color.WHITE);

        g2d.fill(new Rectangle(Mouse.getX() + 1, Mouse.getY() - 4, 2, 15));
        g2d.fill(new Rectangle(Mouse.getX() - 6, Mouse.getY() + 2, 16, 2));

        final int expGained = Skills.getExperiences()[Skills.HERBLORE] - Variables.startXp;
        final int expHour = (int) ((expGained) * 3600000D / (System.currentTimeMillis() - Variables.startTime));
        final int madeHour = (int) ((Variables.made) * 3600000D / (System.currentTimeMillis() - Variables.startTime));
        g2d.drawString(Time.format(System.currentTimeMillis() - Variables.startTime), 85, 29);
        g2d.drawString("" + Skills.getLevel(Skills.HERBLORE) + "(+" + (Skills.getLevel(Skills.HERBLORE) - Variables.startLevel) + ")", 93, 68);
        g2d.drawString("" + Variables.made, 258, 29);
        g2d.drawString("" + madeHour, 278, 68);
        g2d.drawString("" + expGained, 417, 29);
        g2d.drawString("" + expHour, 439, 68);
    }
}
