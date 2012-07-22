package org.itplanker.strategies;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;

public class Antiban extends Strategy implements Task {

    @Override
    public boolean validate() {
        return Random.nextInt(0, 70) == 3;
    }

    @Override
    public void run() {
        Camera.setAngle(Random.nextInt(20, 300));
    }

}
