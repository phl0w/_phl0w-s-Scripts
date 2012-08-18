package org.itbarbfisher.user;

import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Time;

import java.awt.*;

public class Paint {
    private static final Font title = new Font("Monotype Corsiva", Font.PLAIN, 25);
    private static final Font author = new Font("Monotype Corsiva", Font.PLAIN, 16);
    private static final Font info = new Font("Book Antiqua", Font.PLAIN, 15);

    private static final Shape bg = new Rectangle(10, 23, 245, 200);
    private static final Shape border = new Rectangle(8, 21, 245, 204);

    private static final Composite bgComposite = makeComposite(0.5F);
    private static final Composite borderComposite = makeComposite(1.0F);

    private static AlphaComposite makeComposite(final float alpha) {
        final int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
    }

    public static void onRepaint(Graphics g) {
        final Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.DARK_GRAY);
        g2d.setComposite(bgComposite);
        g2d.fill(bg);
        g2d.setColor(Color.BLACK);
        g2d.fill(border);
        g2d.setComposite(borderComposite);

        g2d.setColor(Color.WHITE);

        g2d.fill(new Rectangle(Mouse.getX() + 1, Mouse.getY() - 4, 2, 15));
        g2d.fill(new Rectangle(Mouse.getX() - 6, Mouse.getY() + 2, 16, 2));

        final int fishExpGained = Skills.getExperiences()[Skills.FISHING] - Variables.startFishingExp;
        final int fishExpHour = (int) ((fishExpGained) * 3600000D / (System.currentTimeMillis() - Variables.startTime));
        final int agilityExpGained = Skills.getExperiences()[Skills.AGILITY] - Variables.startAgilityExp;
        final int agilityExpHour = (int) ((agilityExpGained) * 3600000D / (System.currentTimeMillis() - Variables.startTime));
        final int sturgeonsHour = (int) ((Variables.caughtSturgeons) * 3600000D / (System.currentTimeMillis() - Variables.startTime));
        final int salmonsHour = (int) ((Variables.caughtSalmons) * 3600000D / (System.currentTimeMillis() - Variables.startTime));
        final int troutsHour = (int) ((Variables.caughtTrouts) * 3600000D / (System.currentTimeMillis() - Variables.startTime));
        final int urnsHour = (int) ((Variables.urnsCompleted) * 3600000D / (System.currentTimeMillis() - Variables.startTime));

        g2d.setFont(title);
        g2d.drawString("iTBarbFisher v" + Utilities.getVersion(), 12, 43);
        g2d.setFont(author);
        g2d.drawString("By: _phl0w", 12, 58);

        g2d.setFont(info);
        g2d.drawString("Fishing: " + Skills.getLevels()[Skills.FISHING] + "(+" + (Skills.getLevels()[Skills.FISHING] - Variables.startFishingLevel)
                + "). TTL: " + Utilities.getTimeTillLevel(Skills.FISHING, fishExpGained, fishExpHour) + " (" + Utilities.getPercentToNextLevel(Skills.FISHING)
                + "%).", 12, 78);
        g2d.drawString("Agility: " + Skills.getLevels()[Skills.AGILITY] + "(+" + (Skills.getLevels()[Skills.AGILITY] - Variables.startAgilityLevel)
                + "). TTL: " + Utilities.getTimeTillLevel(Skills.AGILITY, agilityExpGained, agilityExpHour) + " ("
                + Utilities.getPercentToNextLevel(Skills.AGILITY) + "%).", 12, 93);
        g2d.drawString("Fishing XP gained: " + fishExpGained + ". (" + fishExpHour + "/h)", 12, 108);
        g2d.drawString("Agility XP gained: " + agilityExpGained + ". (" + agilityExpHour + "/h)", 12, 123);
        g2d.drawString("Fished " + Variables.caughtSturgeons + " sturgeons. (" + sturgeonsHour + "/h)", 12, 138);
        g2d.drawString("Fished " + Variables.caughtSalmons + " salmons. (" + salmonsHour + "/h)", 12, 153);
        g2d.drawString("Fished " + Variables.caughtTrouts + " trouts. (" + troutsHour + "/h)", 12, 168);
        g2d.drawString("Teleported " + Variables.urnsCompleted + " urns. (" + urnsHour + "/h)", 12, 183);
        g2d.drawString("Status: " + Variables.status + ".", 12, 198);
        g2d.drawString("Time running: " + Time.format(System.currentTimeMillis() - Variables.startTime) + ".", 12, 213);
    }
}
