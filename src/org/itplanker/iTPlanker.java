package org.itplanker;

import org.itplanker.strategies.*;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.bot.event.listener.PaintListener;

import javax.swing.*;
import java.awt.*;

@Manifest(authors = {"_phl0w"}, name = "iTPlanker", version = 1.0, description = "Turns logs into planks to make money.", vip = true)
public class iTPlanker extends ActiveScript implements PaintListener {

    @Override()
    protected void setup() {
        Variables.startTime = System.currentTimeMillis();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                iTPlankerGUI itpg = new iTPlankerGUI();
                itpg.setVisible(true);
            }
        });
        provide(new WalkToSawmill());
        provide(new WalkToBank());
        provide(new Rest());
        provide(new ConvertLogs());
        provide(new Banking());
        Strategy ab = new Antiban();
        ab.setLock(false);
        ab.setSync(true);
        provide(ab);
    }

    @Override
    public void onRepaint(Graphics g) {
        Paint.onRepaint(g);
    }

}
