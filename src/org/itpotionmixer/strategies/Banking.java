package org.itpotionmixer.strategies;

import org.itpotionmixer.user.Variables;
import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;

public class Banking extends Strategy implements Task {

    @Override
    public boolean validate() {
        return (Inventory.getCount(Variables.primary) == 0 || Inventory.getCount(Variables.secondary) == 0) && Variables.guiInitialized;
    }

    @Override
    public void run() {
        Variables.status = "banking";
        Bank.open();
        if (Bank.isOpen()) {
            Bank.depositInventory();
            if (Inventory.getCount() == 0) {
                if (Variables.primary == 15309 && Variables.secondary == 15313) { // Overloads
                    Bank.withdraw(Variables.primary, 4);
                    Bank.withdraw(Variables.secondary, 4);
                    Bank.withdraw(15317, 4);
                    Bank.withdraw(15321, 4);
                    Bank.withdraw(15325, 4);
                    Bank.withdraw(269, 4);
                }
                Bank.withdraw(Variables.primary, Variables.primary == 169 ? 0 : 14);
                if (Variables.secondary != 12539) {
                    Bank.withdraw(Variables.secondary, 14);
                }
                Bank.close();
            }
        }
    }
}
