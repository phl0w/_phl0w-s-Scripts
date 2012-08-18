package org.itbarbfisher.strategies;

import org.itbarbfisher.user.Utilities;
import org.itbarbfisher.user.Variables;
import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;

public class Walker extends Strategy implements Task {
    NPC spot = Utilities.getNearestSpot();

    @Override
    public void run() {
        if (Calculations.distanceTo(Variables.startTile) >= 16) {
            Walking.findPath(Variables.startTile).traverse();
        }
        Walking.findPath(spot.getLocation()).traverse();
    }

    @Override
    public boolean validate() {
        return Variables.guiInitialized && (spot != null && !spot.isOnScreen())
                && Calculations.distance(new Tile(2520, 3518, 0), spot.getLocation()) > 5;
    }
}