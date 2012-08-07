package org.beta.itchopper.strategies;

import org.beta.itchopper.functions.Functions;
import org.beta.itchopper.functions.Mode;
import org.beta.itchopper.functions.State;
import org.beta.itchopper.functions.Tree;
import org.beta.itchopper.user.Variables;
import org.powerbot.beta.core.script.BetaScript;
import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.Entity;
import org.powerbot.game.api.wrappers.Locatable;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class Chopper extends BetaScript {

    //private final Tree NORMAL = new Tree(1511, 38782, 38783, 38784, 38785, 38786, 38787, 38788);
    private final Tree NORMAL = new Tree(1519, 38616, 38627);
    private final int[] BIRD_NESTS = {5070, 5071, 5072, 5073, 5074, 5075,
            7413, 11966};

    @Override
    public int loop() {
        State state = getState();
        switch (state) {
            case WALKING:
                return Random.nextInt(400, 500);
            case BUSY:
                return Random.nextInt(400, 500);
            case FULL:
                Mode m = Variables.MODE;
                switch (m) {
                    case DROPPING:
                        for (Item i : NORMAL.getItems()) {
                            if (SceneEntities.getAt(Players.getLocal()) != null) {
                                Walking.walk(Players.getLocal().getLocation().derive(-1, 1));
                            } else {
                                if (i.getWidgetChild().interact("Light")) {
                                    Functions.waitFor(1000, new Condition() {
                                        @Override
                                        public boolean validate() {
                                            return Players.getLocal().getAnimation() != -1;
                                        }
                                    });
                                    Functions.waitFor(1000, new Condition() {
                                        @Override
                                        public boolean validate() {
                                            return Players.getLocal().getAnimation() == -1;
                                        }
                                    });
                                }
                            }
                        }
                        break;
                    case BANKING:
                        Entity bank = Bank.getNearest();
                        if (bank != null) {
                            if (bank.isOnScreen()) {
                                if (Bank.open()) {
                                    if (Bank.isOpen()) {
                                        Bank.depositInventory();
                                        if (Inventory.getCount() < 3) {
                                            Bank.close();
                                        }
                                    }
                                }
                            } else {
                                Turn.turnTo = (Locatable) bank;
                                if (!bank.isOnScreen()) {
                                    Walking.walk((Locatable) bank);
                                }
                            }
                        }
                        break;
                }
                break;
            case INACTIVE:
                SceneObject tree = NORMAL.get();
                if (tree != null) {
                    if (!tree.isOnScreen()) {
                        Turn.turnTo = tree;
                        if (!tree.isOnScreen()) {
                            Walking.walk(tree);
                        }
                    } else {
                        if (tree.interact("Chop down")) {
                            Functions.waitFor(3000, new Condition() {
                                @Override
                                public boolean validate() {
                                    return getState() == State.BUSY;
                                }
                            });
                        }
                    }
                }
                break;
            case PICKUP:
                GroundItem nest = GroundItems.getNearest(BIRD_NESTS);
                if (nest.interact("Take", "Bird's nest")) {
                    final int curNestCount = Inventory.getCount(BIRD_NESTS);
                    Functions.waitFor(3000, new Condition() {
                        @Override
                        public boolean validate() {
                            return Inventory.getCount() > curNestCount;
                        }
                    });
                }
                Variables.nests++;
                break;
        }
        return Random.nextInt(800, 1000);
    }

    private State getState() {
        if (Players.getLocal().isMoving()) {
            return State.WALKING;
        } else if (GroundItems.getNearest(BIRD_NESTS) != null) {
            return State.PICKUP;
        } else if (Players.getLocal().getAnimation() != -1) {
            return State.BUSY;
        } else if (Inventory.isFull()) {
            return State.FULL;
        }
        return State.INACTIVE;
    }
}
