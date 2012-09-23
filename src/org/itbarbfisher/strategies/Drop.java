package org.itbarbfisher.strategies;

import org.itbarbfisher.user.Utilities;
import org.itbarbfisher.user.Variables;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.node.Item;

public class Drop extends Node {

    @Override
    public void execute() {
        Variables.status = "dropping";
        if (Variables.quickDrop) {
            int[] path = {0, 4, 8, 12, 16, 20, 24, 25, 21, 17, 13, 9, 5, 1, 2, 6, 10, 14, 18, 22, 26, 27, 23, 19, 15, 11, 7, 3};
            for (int slot = 0; slot < path.length; slot++) {
                Item x = Inventory.getItemAt(path[slot]);
                if (x.getId() == 11328 || x.getId() == 11330 || x.getId() == 11332 || x.getId() == 229) {
                    int xPos = x.getWidgetChild().getAbsoluteX() + Random.nextInt(0, 5);
                    int yPos = x.getWidgetChild().getAbsoluteY() + Random.nextInt(0, 5);
                    Mouse.hop(xPos, yPos);
                    Mouse.click(false);
                    Task.sleep(10, 25);
                    Mouse.hop(xPos, yPos + (path[slot] >= 24 ? 28 : 60));
                    Mouse.click(true);
                }
            }
            Utilities.getNearestSpot().interact("Use-rod");
            Task.sleep(500, 1000);
        } else {
            for (Item i : Inventory.getItems(new Filter<Item>() {
                @Override
                public boolean accept(Item i) {
                    return i.getId() == 11328 || i.getId() == 11330 || i.getId() == 11332 || i.getId() == 229;
                }
            })) {
                i.getWidgetChild().interact("Drop");
            }
        }
    }

    @Override
    public boolean activate() {
        return Variables.guiInitialized && Inventory.getCount() == 28;
    }

}
