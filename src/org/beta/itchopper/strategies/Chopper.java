package org.beta.itchopper.strategies;

import org.beta.itchopper.functions.Functions;
import org.beta.itchopper.functions.State;
import org.beta.itchopper.functions.Tree;
import org.powerbot.beta.core.script.BetaScript;
import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class Chopper extends BetaScript {

    private final Tree NORMAL = new Tree(1511, 38782, 38783, 38784, 38785, 38786, 38787, 38788);
    private SceneObject tree = null;

    @Override
    public int loop() {
        State state = getState();
        switch (state) {
            case WALKING:
                return Random.nextInt(400, 500);
            case CHOPPING:
                return Random.nextInt(400, 500);
            case DROPPING:
                for (Item i : NORMAL.getItems()) {
                    i.getWidgetChild().interact("Drop");
                }
                break;
            case INACTIVE:
                tree = NORMAL.get();
                if (tree != null) {
                    if (tree.interact("Chop down")) {
                        Functions.waitFor(3000, new Condition() {
                            @Override
                            public boolean validate() {
                                return getState() == State.CHOPPING;
                            }
                        });
                    }
                }
                break;
        }
        System.out.println("loop, curstate=" + state.toString());
        return Random.nextInt(800, 1000);
    }

    private State getState() {
        if (Players.getLocal().isMoving()) {
            return State.WALKING;
        } else if (Players.getLocal().getAnimation() == 873) {
            return State.CHOPPING;
        }
        if (Inventory.isFull()) {
            return State.DROPPING;
        }
        return State.INACTIVE;
    }
}
