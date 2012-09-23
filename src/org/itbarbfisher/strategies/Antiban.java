package org.itbarbfisher.strategies;

import org.itbarbfisher.user.Variables;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;

public class Antiban extends Node {

    @Override
    public boolean activate() {
        return true;
    }

    @Override
    public void execute() {
        if (Random.nextInt(1, 250) < 14) {
            executeAntiban();
        }
        Task.sleep(Random.nextInt(1000, 2000));
    }

    public void executeAntiban() {
        int dx, dy;
        int r = Random.nextInt(0, 5);
        switch (r) {
            case 0:
            case 1:
            default:
                Variables.status = "moving camera";
                Camera.setAngle(Random.nextInt(20, 300));
                break;
            case 2:
            case 3:
                Variables.status = "moving mouse";
                dx = Random.nextInt(-30, 30);
                dy = Random.nextInt(-30, 30);
                Mouse.move(Mouse.getX() + dx, Mouse.getY() + dy);
                Task.sleep(Random.nextInt(20, 150));
                break;
            case 4:
                Tabs.STATS.open();
                Widgets.get(320, 30).hover();
                Task.sleep(1200, 1800);
                Tabs.INVENTORY.open();
                break;
        }
        Variables.status = "fishing";
    }
}