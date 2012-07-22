package org.itplanker.strategies;

import org.itplanker.Variables;
import org.itplanker.constants.Data;
import org.itplanker.functions.Functions;
import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.tab.Inventory;

public class ConvertLogs extends Strategy implements Task {

    @Override
    public boolean validate() {
        return Variables.guiDone && Data.getSawmillOperator() != null && Data.getSawmillOperator().isOnScreen()
                && Inventory.getCount(Variables.log) > 0 && Game.getClientState() != Game.INDEX_MAP_LOADING;
    }

    @Override
    public void run() {

        Variables.status = "converting";
        if (!Data.getLogWidget().validate()) {
            if (Data.getSawmillOperator().interact("Buy-plank")) {
                Functions.waitFor(5000, new Condition() {
                    @Override
                    public boolean validate() {
                        return Data.getLogWidget().validate();
                    }
                });
            }
        } else {
            if (Data.getLogWidget().interact("Buy all")) {
                Functions.waitFor(3000, new Condition() {
                    @Override
                    public boolean validate() {
                        return Inventory.getCount(Data.getPlank()) > 0;
                    }
                });
                Variables.planked = Variables.planked + Inventory.getCount(Data.getPlank());
            }

        }
    }

}
