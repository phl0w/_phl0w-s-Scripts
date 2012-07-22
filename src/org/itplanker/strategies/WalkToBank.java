package org.itplanker.strategies;

import org.itplanker.Variables;
import org.itplanker.constants.Data;
import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class WalkToBank extends Strategy implements Task {

    @Override
    public boolean validate() {
        return Variables.guiDone && checkInventory() && checkBank() && canWalk();
    }

    @Override
    public void run() {
        Variables.status = "walking bank";
        if (!Walking.isRunEnabled()) {
            Walking.setRun(true);
        }
        Data.PATH_TO_BANK.traverse();
    }

    private boolean checkInventory() {
        return Inventory.getCount(Variables.log) == 0;
    }

    private boolean checkBank() {
        SceneObject bank = (SceneObject) Bank.getNearest();
        return bank == null || Calculations.distanceTo(bank) >= 7;
    }

    private boolean canWalk() {
        return needToWalk() && Data.PATH_TO_BANK.getNext().isOnMap();
    }

    private boolean needToWalk() {
        return (Players.getLocal().getAnimation() == 11786 && Walking.getEnergy() > 90) || (Walking.getEnergy() > Variables.restAt || Calculations.distanceTo(Data.MUSICIAN_TILE) > 14);
    }

}
