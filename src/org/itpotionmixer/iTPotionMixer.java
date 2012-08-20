package org.itpotionmixer;

import org.itpotionmixer.strategies.*;
import org.itpotionmixer.user.Paint;
import org.itpotionmixer.user.Utilities;
import org.itpotionmixer.user.Variables;
import org.itpotionmixer.user.iTPotionMixerGUI;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.bot.event.MessageEvent;
import org.powerbot.game.bot.event.listener.MessageListener;
import org.powerbot.game.bot.event.listener.PaintListener;

import javax.swing.*;
import java.awt.*;

@Manifest(authors = {"_phl0w"}, name = "iTPotionMixer", description = "Mix potions! Follow instructions on GUI.", version = 1.71, website = "http://www.powerbot.org/community/topic/674277-potionmixer-mix-potions-for-you/")
public class iTPotionMixer extends ActiveScript implements MessageListener, PaintListener {


    @Override
    protected void setup() {
        Variables.img = Utilities.getImage("http://i45.tinypic.com/rmsgh3.png");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame ui = new iTPotionMixerGUI();
                ui.setVisible(true);
            }
        });
        provide(new Mix());
        provide(new ItemOnItem());
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
}
