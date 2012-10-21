package org.ittokenexchanger.nodes;

import org.ittokenexchanger.user.Condition;
import org.ittokenexchanger.user.Constants;
import org.ittokenexchanger.user.Utilities;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Keyboard;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

public class Buy extends Node {

    private NPC elriss;

    @Override
    public boolean activate() {
        return Inventory.getCount(Constants.TOKENS) > 0 && (elriss = NPCs.getNearest(Constants.ELRISS)) != null && !Inventory.isFull() && Inventory.getCount(1444) == 0;
    }

    private WidgetChild shopChild;

    @Override
    public void execute() {
        if ((shopChild = Widgets.get(779, 15)).validate()) {
            if (shopChild.interact("Buy X", "Water talisman")) {
                if (Utilities.waitFor(2000, new Condition() {
                    @Override
                    public boolean validate() {
                        return Widgets.get(752, 4).isOnScreen();
                    }
                })) {
                    final int count = (28 - Inventory.getCount());
                    Keyboard.sendText(Integer.toString(count), true);
                    if (Utilities.waitFor(1500, new Condition() {
                        @Override
                        public boolean validate() {
                            return Widgets.get(779, 141).getText().contains(Integer.toString(count));
                        }
                    })) {
                        if (Widgets.get(779, 130).interact("Confirm")) {
                            Utilities.waitFor(2000, new Condition() {
                                @Override
                                public boolean validate() {
                                    return Inventory.getCount(1444) > 0;
                                }
                            });
                            if (Widgets.get(779, 131).interact("Close")) {
                                Utilities.waitFor(1300, new Condition() {
                                    @Override
                                    public boolean validate() {
                                        return !shopChild.validate();
                                    }
                                });
                            }
                        }
                    }
                }
            }
        } else {
            if (Calculations.distanceTo(elriss) < 7) {
                if (elriss.interact("Exchange")) {
                    Utilities.waitFor(2000, new Condition() {
                        @Override
                        public boolean validate() {
                            return shopChild.validate();
                        }
                    });
                }
            } else {
                if (!Walking.isRunEnabled() && Walking.getEnergy() > 30) {
                    Walking.setRun(true);
                }
                Walking.walk(elriss);
            }
        }
    }
}
