package org.itbarbfisher.strategies;

import org.itbarbfisher.user.Condition;
import org.itbarbfisher.user.Utilities;
import org.itbarbfisher.user.Variables;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Random;

public class SummonPouch extends Node {

    @Override
    public boolean activate() {
        return Variables.guiInitialized && Settings.get(448) == -1 || Widgets.get(662, 1).getModelId() == -1 && Variables.pouch != -1
                && Inventory.getCount(Variables.pouch) > 0;
    }

    @Override
    public void execute() {
        if (Skills.getLevel(Skills.SUMMONING) < 10) {
            if (canDrink()) {
                drink();
            }
        } else {
            if (Inventory.getItem(Variables.pouch).getWidgetChild().interact("Summon")) {
                Utilities.waitFor(2000, new Condition() {
                    @Override
                    public boolean validate() {
                        return Settings.get(448) != -1 || Widgets.get(662, 1).getModelId() != -1;
                    }
                });
            }
            Task.sleep(Random.nextInt(300, 400));
        }
    }

    public boolean canDrink() {
        int potCount = 0;
        int[] pots = {12140, 12142, 12144, 12146, 3024, 3026, 3028, 3030};
        for (int pot : pots) {
            if (Inventory.getCount(pot) > 0) {
                potCount++;
            }
        }
        return potCount > 0;
    }

    private void drink() {
        int[] pots = {12140, 12142, 12144, 12146, 3024, 3026, 3028, 3030};
        for (int pot : pots) {
            if (Inventory.getItem(pot) != null) {
                if (Inventory.getItem(pot).getWidgetChild().interact("Drink")) {
                    Task.sleep(800);
                    Utilities.waitFor(2000, new Condition() {
                        @Override
                        public boolean validate() {
                            return Skills.getLevel(Skills.SUMMONING) > 10;
                        }
                    });
                }
                Task.sleep(Random.nextInt(300, 400));
            }
        }
    }
}