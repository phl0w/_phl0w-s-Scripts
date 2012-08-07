package org.beta.itchopper.strategies;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.Locatable;

public class Turn extends Strategy implements Task {

    public static Locatable turnTo = null;

    @Override
    public boolean validate() {
        return turnTo != null;
    }

    @Override
    public void run() {
        Camera.turnTo(turnTo);
        turnTo = null;
    }
}
