package org.itherblore.strategies.cleaning;

import org.itherblore.user.Variables;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.node.Item;

public class Clean extends Strategy implements Runnable {

    private final int[] path = {0, 4, 8, 12, 16, 20, 24, 1, 5, 9, 13, 17, 21, 25, 2, 6, 10, 14, 18, 22, 26, 3, 7, 11, 15, 19, 23, 27};

    @Override
    public boolean validate() {
        return Variables.guiInitialized && Inventory.getCount(Variables.primary) > 0 && !Bank.isOpen();
    }

    @Override
    public void run() {
        for (int slot = 0; slot < path.length; slot++) {
            Item item = Inventory.getItemAt(path[slot]);
            if (item.getId() == Variables.primary) {
                if (Variables.quick) {
                    int xPos = item.getWidgetChild().getAbsoluteX() + Random.nextInt(0, 10);
                    int yPos = item.getWidgetChild().getAbsoluteY() + Random.nextInt(0, 10);
                    if (path[slot] == 1 || path[slot] == 2 || path[slot] == 3) {
                        Mouse.move(xPos, yPos);
                    } else {
                        Mouse.hop(xPos, yPos);
                    }
                    Mouse.click(true);
                } else {
                    item.getWidgetChild().click(true);
                }
            }
        }
        Time.sleep(400);
    }
}
