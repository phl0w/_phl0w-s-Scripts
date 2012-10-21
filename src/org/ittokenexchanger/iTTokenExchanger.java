package org.ittokenexchanger;

import org.ittokenexchanger.nodes.Buy;
import org.ittokenexchanger.nodes.Deposit;
import org.ittokenexchanger.user.Paint;
import org.ittokenexchanger.user.Utilities;
import org.ittokenexchanger.user.Variables;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.util.Random;

import java.awt.*;
import java.io.IOException;

@Manifest(authors = {"_phl0w"}, name = "iTTokenExchanger", description = "Exchanges your runecrafting guild tokens into water talismans! (Great money)", version = 1.0)
public class iTTokenExchanger extends ActiveScript implements PaintListener {

    private Tree jobs = null;

    @Override
    public void onStart() {
        Variables.startTime = System.currentTimeMillis();
        try {
            Variables.profit = Utilities.getPrice(1444);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public int loop() {
        if (Game.isLoggedIn()) {
            if (jobs == null) {
                jobs = new Tree(new Node[]{new Buy(), new Deposit()});
            } else {
                final Node job = jobs.state();
                if (job != null) {
                    jobs.set(job);
                    getContainer().submit(job);
                    job.join();
                }
            }
        }
        return Random.nextInt(200, 300);
    }

    @Override
    public void onRepaint(final Graphics render) {
        if (!Game.isLoggedIn()) {
            return;
        }
        Paint.paint(render);
    }
}
