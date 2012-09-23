package org.itherblore.strategies.mixing;

import org.itherblore.user.Variables;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.util.Random;

public class Mix extends Node {

    @Override
    public boolean activate() {
        return Variables.guiInitialized && Widgets.get(905, 14).validate();

    }

    @Override
    public void execute() {
        Widgets.get(905, 14).click(true);
        Variables.status = "mixing";
        Task.sleep(Random.nextInt(400, 900));
    }
}
