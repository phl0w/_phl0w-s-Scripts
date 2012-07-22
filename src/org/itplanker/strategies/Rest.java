package org.itplanker.strategies;

import org.itplanker.Variables;
import org.itplanker.constants.Data;
import org.itplanker.functions.Functions;
import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.util.Time;

public class Rest extends Strategy implements Task {

    @Override
    public boolean validate() {
        return Variables.guiDone && Variables.restEnabled && Walking.getEnergy() < Variables.restAt
                && (Data.MUSICIAN_TILE != null && Data.MUSICIAN_TILE.isOnMap()) && Players.getLocal().getAnimation() != 11786;
    }

    @Override
    public void run() {
        Variables.status = "resting";
        if (Calculations.distanceTo(Data.MUSICIAN_TILE) < 5) {
            if (Data.getMusician() != null && Data.getMusician().interact("Listen-to")) {
                Functions.waitFor(3000, new Condition() {
                    @Override
                    public boolean validate() {
                        return Players.getLocal().getAnimation() == 11786;
                    }
                });
                Functions.waitFor(5000, new Condition() {
                    @Override
                    public boolean validate() {
                        return Walking.getEnergy() >= 90;
                    }
                });
            }
        } else if (Data.MUSICIAN_TILE.isOnMap()) {
            Walking.walk(Data.MUSICIAN_TILE);
            Time.sleep(450, 600);
        }
    }
}
