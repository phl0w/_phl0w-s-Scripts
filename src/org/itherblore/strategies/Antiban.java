package org.itherblore.strategies;

import org.itherblore.user.Variables;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.interactive.Player;

import java.awt.*;

public class Antiban extends Node {

    private Player last = null;

    @Override
    public boolean activate() {
        return Variables.abSupport && Variables.guiInitialized && Players.getLocal().getAnimation() != -1;
    }

    @Override
    public void execute() {
        Variables.status = "ab";
        final int t = Random.nextInt(0, 20);
        switch (t) {
            case 0:
            case 1:
            case 2:
                Camera.setAngle(Random.nextInt(20, 300));
                break;
            case 3:
            case 4:
                Camera.setPitch(Random.nextInt(0, 100));
                break;
            case 5:
                final Player pl = Players.getNearest(new Filter<Player>() {
                    @Override
                    public boolean accept(Player p) {
                        return !p.equals(Players.getLocal()) && (last == null || !last.equals(p));
                    }
                });
                if (pl != null) {
                    if (!pl.isOnScreen()) {
                        Camera.turnTo(pl);
                    }
                    pl.click(false);
                    doMouseStuff();
                    last = pl;
                }
                break;
            case 6:
                doMouseStuff();
                break;
            case 7:
            case 8:
            case 9:
                final Point p = Mouse.getLocation();
                Mouse.move(new Point(Random.nextInt(-50, 50) + p.x, Random.nextInt(-50, 50) + p.y));
                break;
        }
        Task.sleep(1000);
    }

    private void doMouseStuff() {
        final boolean r = Random.nextBoolean();
        if (r && Menu.isOpen()) {
            Menu.select("Cancel");
        } else {
            final double height = Game.getDimensions().getHeight();
            final double width = Game.getDimensions().getWidth();
            Mouse.move(new Point(Random.nextInt(0, (int) height), Random.nextInt(0, (int) width)));
        }
    }
}
