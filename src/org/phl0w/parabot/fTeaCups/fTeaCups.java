package org.phl0w.parabot.fTeaCups;

import com.parabot.methods.Skills;
import com.parabot.scripts.MessageEvent;
import com.parabot.scripts.Script;
import com.parabot.scripts.ScriptFormat;
import com.parabot.scripts.Strategy;
import interfaces.Painter;
import org.phl0w.parabot.fTeaCups.strategies.Antiban;
import org.phl0w.parabot.fTeaCups.strategies.Dropping;
import org.phl0w.parabot.fTeaCups.strategies.Stealing;

import java.awt.*;
import java.util.ArrayList;

@ScriptFormat(author = "phl0w", name = "fTeaCups", category = "Thieving", description = "lol", version = 1.0d)
public class fTeaCups extends Script implements Painter, MessageEvent {

    final ArrayList<Strategy> strategies = new ArrayList<Strategy>();

    @Override
    public boolean onExecute() {
        strategies.add(new Dropping());
        strategies.add(new Stealing());
        strategies.add(new Antiban());
        provide(strategies);
        Variables.startTime = System.currentTimeMillis();
        Variables.startLevel = Skills.getLevel(Skills.THIEVING);
        Variables.startXp = Skills.getCurrentExp(Skills.THIEVING);
        return true;
    }

    @Override
    public void paint(Graphics g) {
        Paint.onRepaint(g);
    }

    @Override
    public void messageRecieved(String msg) {
        if (msg.contains("successfully stole a cup")) {
            Variables.stolen++;
        }
    }
}
