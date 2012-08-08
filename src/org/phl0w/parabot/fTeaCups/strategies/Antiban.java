package org.phl0w.parabot.fTeaCups.strategies;

import com.parabot.methods.Camera;
import com.parabot.scripts.Strategy;
import org.phl0w.parabot.fTeaCups.utilities.Functions;

public class Antiban extends Strategy {

    @Override
    public void run() {
        Camera.setPitch(Functions.randomBetween(0, 2) == 1);
        Camera.setCameraAltitude(Functions.randomBetween(80, 100));
        Camera.setCameraRotation(Functions.randomBetween(0, 100));
    }

    @Override
    public boolean isValid() {
        return Functions.randomBetween(0, 100) == 13;
    }

    @Override
    public String getStatus() {
        return "Antiban";
    }
}
