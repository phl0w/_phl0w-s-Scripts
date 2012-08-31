package org.itherblore.strategies.cleaning;

import org.itherblore.user.Variables;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.node.Item;

public class Clean extends Strategy implements Runnable {

    @Override
    public boolean validate() {
        return Variables.guiInitialized && Inventory.getCount(Variables.primary) > 0 && !Bank.isOpen();
    }

    @Override
    public void run() {
        if (Variables.quick) {
            int[] path = {0, 4, 8, 12, 16, 20, 24, 25, 21, 17, 13, 9, 1, 5, 2,
                    6, 10, 14, 18, 22, 26, 27, 23, 19, 15, 11, 7, 3};
            for (int slot = 0; slot < path.length; slot++) {
                Item item = Inventory.getItemAt(path[slot]);
                if (item.getId() == Variables.primary) {
                    Mouse.hop(
                            item.getWidgetChild().getAbsoluteX()
                                    + Random.nextInt(0, 12),
                            item.getWidgetChild().getAbsoluteY()
                                    + Random.nextInt(0, 12));
                    Mouse.click(true);
                }
            }
        }
    }
}
