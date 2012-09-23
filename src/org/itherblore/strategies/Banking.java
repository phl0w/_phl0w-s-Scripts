package org.itherblore.strategies;

import org.itherblore.user.Condition;
import org.itherblore.user.Utilities;
import org.itherblore.user.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;

import java.util.logging.Logger;

import static org.powerbot.game.bot.Context.get;

public class Banking extends Node {

    private final Logger log = Logger.getLogger(Banking.class.getName());

    @Override
    public boolean activate() {
        return (Variables.pots ? (Inventory.getCount(Variables.primary) == 0 || Inventory.getCount(Variables.secondary) == 0) : Inventory.getCount(Variables.primary) == 0) && Variables.guiInitialized && Inventory.getItem(14664) == null;
    }

    @Override
    public void execute() {
        if (Bank.isOpen()) {
            if (Variables.secondary == 12539) {
                Utilities.waitFor(2000, new Condition() {
                    @Override
                    public boolean validate() {
                        return !(Bank.deposit(15325, 0) && Inventory.getCount(Variables.primary) > 0) || Bank.deposit(Variables.primary, 0);
                    }
                });
            } else {
                Bank.depositInventory();
            }
            if (Variables.pots) {
                if (Variables.primary == 15309 && Variables.secondary == 15313) { // Overloads
                    Bank.withdraw(Variables.primary, 4);
                    Bank.withdraw(Variables.secondary, 4);
                    Bank.withdraw(15317, 4);
                    Bank.withdraw(15321, 4);
                    Bank.withdraw(15325, 4);
                    Bank.withdraw(269, 4);
                } else {
                    if (Bank.getItemCount(Variables.primary) == 0 || Bank.getItemCount(Variables.secondary) == 0) {
                        Bank.close();
                        Game.logout(true);
                        log.info("All out of supplies...");
                        get().getScriptHandler().stop();
                    }

                    if (Variables.secondary == 12539) {
                        if (Inventory.getCount(12539) == 0 && Bank.getItemCount(12539) > 0) {
                            Bank.withdraw(12539, 0);
                        }
                    } else if (Variables.secondary != 12539) {
                        Bank.withdraw(Variables.secondary, 14);
                    }
                    Bank.withdraw(Variables.primary, Variables.primary == 169 ? 0 : 14);
                }
            } else {
                if (Bank.getItemCount(Variables.primary) == 0) {
                    Bank.close();
                    Game.logout(true);
                    log.info("All out of herbs...");
                    get().getScriptHandler().stop();
                } else {
                    Bank.withdraw(Variables.primary, 0);
                }
            }
            Bank.close();
        } else {
            Bank.open();
        }
    }
}