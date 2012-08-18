package org.itbarbfisher.strategies;

import org.itbarbfisher.user.Variables;
import org.itplanker.functions.Functions;
import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.tab.Inventory;

public class Urns extends Strategy implements Task {

    @Override
    public boolean validate() {
        return Variables.urns && Inventory.getCount(20348) > 0;
    }

    @Override
    public void run() {
        if (Widgets.get(905, 14).validate()) {
            if (Widgets.get(905, 14).click(true)) {
                Functions.waitFor(3000, new Condition() {
                    @Override
                    public boolean validate() {
                        return Inventory.getCount(20348) == 0;
                    }
                });
            }
        } else {
            if (Inventory.getItem(20348).getWidgetChild().interact("Teleport")) {
                Functions.waitFor(2000, new Condition() {
                    @Override
                    public boolean validate() {
                        return Widgets.get(905, 14).validate();
                    }
                });
            }
        }
    }
}