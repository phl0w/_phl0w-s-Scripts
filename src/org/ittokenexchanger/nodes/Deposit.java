package org.ittokenexchanger.nodes;

import org.ittokenexchanger.user.Condition;
import org.ittokenexchanger.user.Constants;
import org.ittokenexchanger.user.Utilities;
import org.ittokenexchanger.user.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Keyboard;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class Deposit extends Node {

    private SceneObject box;

    @Override
    public boolean activate() {
        return (box = SceneEntities.getNearest(Constants.DEPOSIT)) != null && Inventory.getCount(1444) > 0;
    }

    @Override
    public void execute() {
        if (Widgets.get(1188, 34).validate()) {
            Variables.banked += Inventory.getCount(1444);
            Keyboard.sendKey('4');
            Utilities.waitFor(2000, new Condition() {
                @Override
                public boolean validate() {
                    return Inventory.getCount(1444) == 0;
                }
            });
        } else {
            if (box.isOnScreen()) {
                if (Inventory.getItem(1444).getWidgetChild().interact("Use")) {
                    Utilities.waitFor(800, new Condition() {
                        @Override
                        public boolean validate() {
                            return Inventory.getSelectedItem() != null;
                        }
                    });
                    if (Inventory.getSelectedItem() != null && box.interact("Use", "Water talisman -> Deposit box")) {
                        Utilities.waitFor(2000, new Condition() {
                            @Override
                            public boolean validate() {
                                return Widgets.get(1188, 34).validate();
                            }
                        });
                    }
                }
            } else {
                if (!Walking.isRunEnabled() && Walking.getEnergy() > 30) {
                    Walking.setRun(true);
                } else {
                    Walking.walk(box);
                }
            }
        }
    }
}
