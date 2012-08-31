package org.itherblore.strategies.mixing;

import org.itherblore.user.Variables;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;

public class Mix extends Strategy implements Runnable {

    @Override
    public boolean validate() {
        return Variables.guiInitialized && Widgets.get(905, 14).validate();

    }

    @Override
    public void run() {
        Widgets.get(905, 14).click(true);
        Variables.status = "mixing";
        Time.sleep(Random.nextInt(400, 900));
    }
}
