package org.itherblore;

import org.itherblore.strategies.Antiban;
import org.itherblore.strategies.Banking;
import org.itherblore.strategies.Drop;
import org.itherblore.strategies.cleaning.Clean;
import org.itherblore.strategies.mixing.ItemOnItem;
import org.itherblore.strategies.mixing.Mix;
import org.itherblore.user.Paint;
import org.itherblore.user.Utilities;
import org.itherblore.user.Variables;
import org.itherblore.user.iTHerbloreGUI;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.concurrent.strategy.StrategyGroup;
import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.bot.event.MessageEvent;
import org.powerbot.game.bot.event.listener.MessageListener;
import org.powerbot.game.bot.event.listener.PaintListener;

import javax.swing.*;
import java.awt.*;

@Manifest(authors = {"_phl0w"}, name = "iTHerblore", description = "Mixes potions & cleans herbs! - Follow instructions on the GUI.", version = 1.8, website = "http://www.powerbot.org/community/topic/674277-potionmixer-mix-potions-for-you/")
public class iTHerblore extends ActiveScript implements MessageListener, PaintListener {

    private static final StrategyGroup POTION_MIXING = new StrategyGroup();
    private static final StrategyGroup HERB_CLEANING = new StrategyGroup();

    //TODO: Update GUI for 1.8
    @Override
    protected void setup() {
        Variables.img = Utilities.getImage("http://i45.tinypic.com/rmsgh3.png");

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame ui = new iTHerbloreGUI();
                ui.setVisible(true);
            }
        });

        provide(new GUI());

        POTION_MIXING.group(new ItemOnItem());
        POTION_MIXING.group(new Mix());
        HERB_CLEANING.group(new Clean());

        provide(POTION_MIXING);
        provide(HERB_CLEANING);

        provide(new Banking());
        provide(new Drop());

        Strategy ab = new Antiban();
        ab.setLock(false);
        ab.setSync(true);
        provide(ab);

        Variables.startTime = System.currentTimeMillis();
        Variables.startXp = Skills.getExperience(Skills.HERBLORE);
        Variables.startLevel = Skills.getLevel(Skills.HERBLORE);
    }

    @Override
    public void onRepaint(Graphics g) {
        if (Variables.paintSupport) {
            Paint.onRepaint(g);
        }
    }

    @Override
    public void messageReceived(MessageEvent arg0) {
        String msg = arg0.getMessage();
        if (msg.contains("you put the") || msg.contains("into the vial") || msg.contains("mix the") || msg.contains("serum 207")) {
            Variables.made++;
        }
    }

    private class GUI extends Strategy implements Runnable {

        @Override
        public boolean validate() {
            return Variables.guiInitialized;
        }

        @Override
        public void run() {
            provide(Variables.pots ? POTION_MIXING : HERB_CLEANING);
            revoke(this);
        }
    }
}
