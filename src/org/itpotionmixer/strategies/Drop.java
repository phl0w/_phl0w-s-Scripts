package org.itpotionmixer.strategies;

import org.itplanker.functions.Functions;
import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.node.Item;

public class Drop extends Strategy implements Task {

    Item box = Inventory.getItem(14664);

    @Override
    public boolean validate() {
        return !Bank.isOpen() && box != null;
    }

    @Override
    public void run() {
        if (box.getWidgetChild().interact("Drop")) {
            Functions.waitFor(2000, new Condition() {
                @Override
                public boolean validate() {
                    return box == null;
                }
            });
        }
    }
}
