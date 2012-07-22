package org.itplanker.strategies;

import org.itplanker.Variables;
import org.itplanker.constants.Data;
import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;

public class WalkToSawmill extends Strategy implements Task {

    @Override
    public boolean validate() {
        return Variables.guiDone && checkInventory() && checkWidget() && canWalk();
    }

    @Override
    public void run() {
        Variables.status = "walking sawmill";
        if (!Walking.isRunEnabled()) {
            Walking.setRun(true);
        }
        Data.PATH_TO_SAWMILL.traverse();
    }

    private boolean checkInventory() {
        return Inventory.getCount(Variables.log) > 0;
    }

    private boolean checkWidget() {
        return !Data.getLogWidget().validate();
    }

    private boolean canWalk() {
        return Data.PATH_TO_SAWMILL.getNext().isOnMap() && needToWalk();
    }

    private boolean needToWalk() {
        if (Players.getLocal().getAnimation() == 11786) {
            return Walking.getEnergy() > 90;
        } else {
            return Walking.getEnergy() > Variables.restAt || Calculations.distanceTo(Data.MUSICIAN_TILE) > 5;
        }
    }

}
