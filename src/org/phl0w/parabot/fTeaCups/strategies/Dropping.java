package org.phl0w.parabot.fTeaCups.strategies;

import com.parabot.methods.Inventory;
import com.parabot.scripts.Strategy;
import com.parabot.wrappers.RSItem;
import org.phl0w.parabot.fTeaCups.utilities.Functions;

public class Dropping extends Strategy {

    @Override
    public void run() {
        for (RSItem i : Inventory.getItems()) {
            i.interact("Drop");
            Functions.sleepBetween(30, 60);
        }
    }

    @Override
    public boolean isValid() {
        return Inventory.isFull();
    }

    @Override
    public String getStatus() {
        return "Dropping";
    }
}
