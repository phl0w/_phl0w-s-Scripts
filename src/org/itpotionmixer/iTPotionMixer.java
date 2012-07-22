package org.itpotionmixer;

import org.itpotionmixer.strategies.Antiban;
import org.itpotionmixer.strategies.Banking;
import org.itpotionmixer.strategies.ItemOnItem;
import org.itpotionmixer.strategies.Mix;
import org.itpotionmixer.user.Functions;
import org.itpotionmixer.user.Paint;
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

@Manifest(authors = {"_phl0w"}, name = "iTPotionMixer", description = "Mix potions! Follow instructions on GUI.", version = 1.6, website = "http://www.powerbot.org/community/topic/674277-potionmixer-mix-potions-for-you/")
public class iTPotionMixer extends ActiveScript implements MessageListener, PaintListener {


    @Override
    protected void setup() {
        if (Functions.downloadFont()) {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Variables.font);
        }
        Variables.img = Functions.getImage("http://phl0w.com/crap/mixer.png");
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
