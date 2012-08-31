package org.itherblore.strategies;

import org.itherblore.user.Variables;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;

import java.util.logging.Logger;

import static org.powerbot.game.bot.Context.get;

public class Banking extends Strategy implements Runnable {

    private final Logger log = Logger.getLogger(Banking.class.getName());

    @Override
    public boolean validate() {
        return (Inventory.getCount(Variables.primary) == 0 || Inventory.getCount(Variables.secondary) == 0) && Variables.guiInitialized && Inventory.getItem(14664) == null;
    }

    @Override
    public void run() {
        Bank.open();
        if (Bank.isOpen()) {
            Bank.depositInventory();
            if (Variables.pots) {
                if (Variables.primary == 15309 && Variables.secondary == 15313) { // Overloads
                    Bank.withdraw(Variables.primary, 4);
                    Bank.withdraw(Variables.secondary, 4);
                    Bank.withdraw(15317, 4);
                    Bank.withdraw(15321, 4);
                    Bank.withdraw(15325, 4);
                    Bank.withdraw(269, 4);
                } else {
                    Bank.withdraw(Variables.primary, Variables.primary == 169 ? 0 : 14);
                    if (Variables.secondary != 12539) {
                        Bank.withdraw(Variables.secondary, 14);
                    }
                }
            } else {
                if (Bank.getItemCount(Variables.primary) == 0) {
                    Bank.close();
                    Game.logout(true);
                    log.info("All out of herbs...");
                    get().getActiveScript().stop();
                } else {
                    Bank.withdraw(Variables.primary, 0);
                }
            }
            Bank.close();
        }
    }
}
