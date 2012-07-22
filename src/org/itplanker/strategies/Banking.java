package org.itplanker.strategies;

import org.itplanker.Variables;
import org.itplanker.constants.Data;
import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.Entity;

public class Banking extends Strategy implements Task {

    private Entity nearestBank;

    @Override
    public boolean validate() {
        nearestBank = Bank.getNearest();
        return Variables.guiDone && Inventory.getCount(Variables.log) == 0 && (nearestBank != null && nearestBank.isOnScreen());
    }

    @Override
    public void run() {
        Variables.status = "banking";
        if (Bank.isOpen()) {
            if (Inventory.getCount() > 0) {
                Bank.depositInventory();
            } else {
                if (Bank.withdraw(Variables.log, 0)) {
                    if (Inventory.getCount(Variables.log) > 0) {
                        Walking.walk(Data.PATH_TO_SAWMILL.getStart());
                    }
                }
            }
        } else {
            Bank.open();
        }

    }
}
