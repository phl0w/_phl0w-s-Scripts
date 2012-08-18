package org.itbarbfisher;

import org.itbarbfisher.strategies.*;
import org.itbarbfisher.user.GUI;
import org.itbarbfisher.user.Paint;
import org.itbarbfisher.user.Utilities;
import org.itbarbfisher.user.Variables;
import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.bot.event.MessageEvent;
import org.powerbot.game.bot.event.listener.MessageListener;
import org.powerbot.game.bot.event.listener.PaintListener;

import javax.swing.*;
import java.awt.*;

@Manifest(
        authors = {"_phl0w"},
        name = "iTBarbFisher",
        version = 1.7,
        description = "Does the barbarian fishing training! - Have barbarian rod in your toolbelt & start near the fishing spots!",
        website = "http://www.powerbot.org/community/topic/697455-itbarbfisher-fastest-fishing-xp-in-the-game-fishingagility-xp-50k-xph/")
public class iTBarbFisher extends ActiveScript implements PaintListener, MessageListener {

    @Override
    protected void setup() {
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
        provide(new Fish());
        provide(new Drop());
        provide(new Walker());
        provide(new Antiban());
        provide(new SummonPouch());
        provide(new Urns());
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

