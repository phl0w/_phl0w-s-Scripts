package org.phl0w.parabot.fTeaCups;

import com.parabot.methods.GameObjects;
import com.parabot.methods.Inventory;
import com.parabot.methods.Players;
import com.parabot.methods.Walking;
import com.parabot.scripts.BasicLoop;
import com.parabot.scripts.Script;
import com.parabot.wrappers.GameObject;
import org.phl0w.parabot.fTeaCups.utilities.Condition;
import org.phl0w.parabot.fTeaCups.utilities.Functions;
import org.phl0w.parabot.fTeaCups.utilities.State;

public class fTeaCups extends Script implements BasicLoop {

    final GameObject stall = GameObjects.getNearest(635);

    @Override
    public int loop() {
        State s = getState();
        switch (s) {
            case INACTIVE:
                stall.interact("Steal-from");
                Functions.waitFor(3000, new Condition() {
                    @Override
                    public boolean validate() {
                        return Players.getMyPlayer().getAnimation() != -1;
                    }
                });
                break;
            case BANKING:
                break;
            case WALKING:
                Functions.sleep(300, 400);
                break;
            case STEALING:
                Functions.sleep(300, 400);
                break;
        }
        return 1000;
    }

    private State getState() {
        if (Players.getMyPlayer().getAnimation() == -1 && stall.isOnScreen() && Inventory.getCount() < 28) {
            return State.INACTIVE;
        } else if (Inventory.getCount() == 28) {
            return State.BANKING;
        } else if (Walking.getDestination() != null) {
            return State.WALKING;
        } else if (Players.getMyPlayer().getAnimation() != -1) {
            return State.STEALING;
        }
        return State.INACTIVE;
    }

    @Override
    public boolean onExecute() {
        return true;
    }
}
