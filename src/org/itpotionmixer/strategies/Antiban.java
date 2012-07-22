package org.itpotionmixer.strategies;

import org.itpotionmixer.user.Variables;
import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;

public class Antiban extends Strategy implements Task {

    @Override
    public boolean validate() {
        return Variables.abSupport && Variables.guiInitialized && Players.getLocal().getAnimation() != -1;
    }

    @Override
    public void run() {
        Variables.status = "ab";
        if (Random.nextInt(1, 15) <= 2) {
            executeAntiBan();
        }
        Time.sleep(1000);
    }

    private final void executeAntiBan() {
        Camera.setAngle(Random.nextInt(20, 300));
    }
}
