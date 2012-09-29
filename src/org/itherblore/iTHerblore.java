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
import org.powerbot.core.event.events.MessageEvent;
import org.powerbot.core.event.listeners.MessageListener;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Random;

import javax.swing.*;
import java.awt.*;

@Manifest(authors = {"_phl0w"}, name = "iTHerblore", description = "Mixes potions & cleans herbs! - Follow instructions on the GUI.", version = 1.83, website = "http://www.powerbot.org/community/topic/674277-potionmixer-mix-potions-for-you/")
public class iTHerblore extends ActiveScript implements MessageListener, PaintListener {


    private Tree jobs = null;

    @Override
    public int loop() {
        if (!Variables.guiInitialized) {
            return Random.nextInt(400, 600);
        } else {
            if (jobs == null) {
                if (Variables.pots) {
                    jobs = new Tree(new Node[]{new ItemOnItem(), new Mix(), new Banking(), new Drop(), new Antiban()});
                } else {
                    jobs = new Tree(new Node[]{new Clean(), new Banking(), new Drop(), new Antiban()});
                }
            }
            final Node job = jobs.state();
            if (job != null) {
                jobs.set(job);
                getContainer().submit(job);
                job.join();
            }
        }
        return Random.nextInt(200, 300);
    }

    @Override
    public void onStart() {
        Variables.img = Utilities.getImage("http://i45.tinypic.com/rmsgh3.png");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame ui = new iTHerbloreGUI();
                ui.setVisible(true);
            }
        });
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
        if (msg.contains("you put the") || msg.contains("into the vial") || msg.contains("mix the") || msg.contains("serum 207") || msg.contains("clean the dirt")) {
            Variables.made++;
        }
    }
}
