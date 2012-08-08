package org.phl0w.parabot.fTeaCups.strategies;

import com.parabot.methods.GameObjects;
import com.parabot.methods.Inventory;
import com.parabot.methods.Players;
import com.parabot.scripts.Strategy;
import com.parabot.wrappers.GameObject;
import org.phl0w.parabot.fTeaCups.utilities.Condition;
import org.phl0w.parabot.fTeaCups.utilities.Functions;

public class Stealing extends Strategy {

    final GameObject stall = GameObjects.getNearest(635);

    @Override
    public void run() {
        stall.interact("Steal-from");
        Functions.waitFor(1500, new Condition() {
            @Override
            public boolean validate() {
                return Players.getMyPlayer().getAnimation() != -1;
            }
        });
    }

    @Override
    public boolean isValid() {
        return stall != null && stall.isOnScreen() && Players.getMyPlayer().getAnimation() == -1 && !Inventory.isFull();
    }

    @Override
    public String getStatus() {
        return "Stealing";
    }
}
