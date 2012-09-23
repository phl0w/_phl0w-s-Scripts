package org.itherblore.strategies.mixing;

import org.itherblore.user.Condition;
import org.itherblore.user.Utilities;
import org.itherblore.user.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.Item;

public class ItemOnItem extends Node {

    @Override
    public boolean activate() {
        return Variables.guiInitialized && !Widgets.get(905, 14).validate() && (
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
        Item prim = Inventory.getItem(Variables.primary);
        Item sec = Inventory.getItem(Variables.secondary);
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
