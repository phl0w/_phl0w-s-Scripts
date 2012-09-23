package org.itbarbfisher.strategies;

import org.itbarbfisher.user.Condition;
import org.itbarbfisher.user.Utilities;
import org.itbarbfisher.user.Variables;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.interactive.NPC;

public class Fish extends Node {

    @Override
    public void execute() {
        if (Tabs.getCurrent() != Tabs.INVENTORY) {
            Tabs.INVENTORY.open();
            Task.sleep(Random.nextInt(300, 600));
        }
        if (Utilities.getNearestSpot().interact("Use-rod")) {
            Utilities.waitFor(2000, new Condition() {
                @Override
                public boolean validate() {
                    return Players.getLocal().getAnimation() != -1;
                }
            });
        }
        Variables.status = "fishing";
        Task.sleep(Random.nextInt(1200, 1800));
    }

    @Override
    public boolean activate() {
        return Variables.guiInitialized
                && (!Variables.isDropping && !isFishing() && Inventory.getCount() < 28 && Utilities.getBait() != null && Utilities.getBait().getStackSize() > 0
                && !Players.getLocal().isMoving() && Utilities.getNearestSpot() != null && Utilities.getNearestSpot().isOnScreen())
                || Variables.inactivityTimer.getElapsed() >= 30000;
    }

    private boolean isFishing() {
        return Players.getLocal().getAnimation() != -1 && Players.getLocal().getInteracting() != null
                && Players.getLocal().getInteracting() instanceof NPC;
    }
}
