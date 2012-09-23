package org.itbarbfisher;

import org.itbarbfisher.strategies.*;
import org.itbarbfisher.user.GUI;
import org.itbarbfisher.user.Paint;
import org.itbarbfisher.user.Utilities;
import org.itbarbfisher.user.Variables;
import org.powerbot.core.event.events.MessageEvent;
import org.powerbot.core.event.listeners.MessageListener;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Tile;

import javax.swing.*;
import java.awt.*;

@Manifest(
        authors = {"_phl0w"},
        name = "iTBarbFisher",
        version = 1.8,
        description = "Does the barbarian fishing training! - Have barbarian rod in your toolbelt & start near the fishing spots!",
        website = "http://www.powerbot.org/community/topic/697455-itbarbfisher-fastest-fishing-xp-in-the-game-fishingagility-xp-50k-xph/")
public class iTBarbFisher extends ActiveScript implements PaintListener, MessageListener {

    private Tree jobs = null;

    @Override
    public int loop() {
        if (jobs == null) {
            jobs = new Tree(new Node[]{new Fish(), new Drop(), new Walker(), new Antiban(), new SummonPouch(), new Urns()});
        }
        final Node node = jobs.state();
        if (node != null) {
            jobs.set(node);
            getContainer().submit(node);
            node.join();
            return 0;
        }
        return Random.nextInt(200, 300);
    }

    @Override
    public void onStart() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI g = new GUI();
                g.setVisible(true);
            }
        });
        Variables.startTime = System.currentTimeMillis();
        Variables.startFishingLevel = Skills.getLevel(Skills.FISHING);
        Variables.startFishingExp = Skills.getExperience(Skills.FISHING);
        Variables.startAgilityLevel = Skills.getLevel(Skills.AGILITY);
        Variables.startAgilityExp = Skills.getExperience(Skills.AGILITY);
        Variables.inactivityTimer = new Timer(0);
        Variables.startTile = new Tile(2499, 3508, 0);
        Utilities.doUpdates("http://phl0w.site88.net/scripts/barb.php");
    }

    @Override
    public void onRepaint(Graphics g) {
        Paint.onRepaint(g);
    }

    @Override
    public void messageReceived(MessageEvent arg0) {
        String msg = arg0.getMessage();
        if (msg.contains("leaping sturgeon")) {
            Variables.caughtSturgeons++;
            Variables.inactivityTimer = new Timer(0);
        } else if (msg.contains("leaping salmon")) {
            Variables.caughtSalmons++;
            Variables.inactivityTimer = new Timer(0);
        } else if (msg.contains("leaping trout")) {
            Variables.caughtTrouts++;
            Variables.inactivityTimer = new Timer(0);
        } else if (msg.contains("it is teleported away")) {
            Variables.urnsCompleted++;
        }
    }

}

