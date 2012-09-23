package org.itbarbfisher.strategies;

import org.itbarbfisher.user.Utilities;
import org.itbarbfisher.user.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;

public class Walker extends Node {
    NPC spot = Utilities.getNearestSpot();

    @Override
    public void execute() {
        if (Calculations.distanceTo(Variables.startTile) >= 16) {
            Walking.findPath(Variables.startTile).traverse();
        }
        Walking.findPath(spot.getLocation()).traverse();
    }

    @Override
    public boolean activate() {
        return Variables.guiInitialized && (spot != null && !spot.isOnScreen())
                && Calculations.distance(new Tile(2520, 3518, 0), spot.getLocation()) > 5;
    }
}