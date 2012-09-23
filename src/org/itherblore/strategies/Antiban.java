package org.itherblore.strategies;

import org.itherblore.user.Variables;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;

public class Antiban extends Node {

    @Override
    public boolean activate() {
        return Variables.abSupport && Variables.guiInitialized && Players.getLocal().getAnimation() != -1;
    }

    @Override
    public void execute() {
        Variables.status = "ab";
        if (Random.nextInt(1, 15) <= 2) {
            executeAntiBan();
        }
        Task.sleep(1000);
    }

    private void executeAntiBan() {
        Camera.setAngle(Random.nextInt(20, 300));
    }
}
