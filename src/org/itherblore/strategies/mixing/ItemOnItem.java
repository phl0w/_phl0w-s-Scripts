package org.itherblore.strategies.mixing;

import org.itherblore.user.Condition;
import org.itherblore.user.Utilities;
import org.itherblore.user.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.node.Item;

public class ItemOnItem extends Node {

    @Override
    public boolean activate() {
        return Variables.guiInitialized && (Inventory.getCount(Variables.primary) > 0 && Inventory.getCount(Variables.secondary) > 0) && !Widgets.get(905, 14).validate() && !Bank.isOpen() && (
                (Inventory.isFull() && Variables.primary != 169)
                        || !Utilities.waitFor(1200, new Condition() {
                    @Override
                    public boolean validate() {
                        return Players.getLocal().getAnimation() != -1;
                    }
                }));
    }

    @Override
    public void execute() {
        Variables.status = "ioi";
        final Item prim, sec;
        final Item[] items = {Inventory.getItemAt(13), Inventory.getItemAt(14)};
        if (Inventory.isFull()) {
            prim = (items[0].getId() == Variables.primary || items[0].getId() == Variables.secondary) ? items[0] : Inventory.getItem(Variables.primary);
            sec = (items[1].getId() == Variables.primary || items[1].getId() == Variables.secondary) ? items[1] : Inventory.getItem(Variables.secondary);
        } else {
            prim = Inventory.getItem(Variables.primary);
            sec = Inventory.getItem(Variables.secondary);
        }
        if (prim.getWidgetChild().interact("Use")) {
            if (sec.getWidgetChild().interact("Use")) {
                Utilities.waitFor(3000, new Condition() {
                    @Override
                    public boolean validate() {
                        return Widgets.get(905, 14).validate();
                    }
                });
            }
        }
    }
}
