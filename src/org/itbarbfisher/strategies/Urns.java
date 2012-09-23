package org.itbarbfisher.strategies;

import org.itbarbfisher.user.Condition;
import org.itbarbfisher.user.Utilities;
import org.itbarbfisher.user.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.tab.Inventory;

public class Urns extends Node {

    @Override
    public boolean activate() {
        return Variables.urns && Inventory.getCount(20348) > 0;
    }

    @Override
    public void execute() {
        if (Widgets.get(905, 14).validate()) {
            if (Widgets.get(905, 14).click(true)) {
                Utilities.waitFor(3000, new Condition() {
                    @Override
                    public boolean validate() {
                        return Inventory.getCount(20348) == 0;
                    }
                });
            }
        } else {
            if (Inventory.getItem(20348).getWidgetChild().interact("Teleport")) {
                Utilities.waitFor(2000, new Condition() {
                    @Override
                    public boolean validate() {
                        return Widgets.get(905, 14).validate();
                    }
                });
            }
        }
    }
}