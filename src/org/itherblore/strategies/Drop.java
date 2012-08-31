package org.itherblore.strategies;

import org.itherblore.user.Utilities;
import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.node.Item;

public class Drop extends Strategy implements Runnable {

    Item drop = Inventory.getItem(14664, 24154);

    @Override
    public boolean validate() {
        drop = Inventory.getItem(14664, 24154);
        return !Bank.isOpen() && drop != null;
    }

    @Override
    public void run() {
        switch (drop.getId()) {
            case 14664:
                if (drop.getWidgetChild().interact("Drop")) {
                    Utilities.waitFor(2000, new Condition() {
                        @Override
                        public boolean validate() {
                            return Inventory.getCount(drop.getId()) == 0;
                        }
                    });
                }
                break;
            case 24154:
                if (drop.getWidgetChild().interact("Claim")) {
                    Utilities.waitFor(2000, new Condition() {
                        @Override
                        public boolean validate() {
                            return Inventory.getCount(drop.getId()) == 0;
                        }
                    });
                }
                break;
        }
    }
}
