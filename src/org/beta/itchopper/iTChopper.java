package org.beta.itchopper;

import org.beta.itchopper.strategies.Chopper;
import org.powerbot.beta.core.script.Script;
import org.powerbot.concurrent.Task;
import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;

@Manifest(authors = {"phl0w"}, name = "iTChopper", description = "Testing the new framework :)", version = 1.0d)
public class iTChopper extends ActiveScript {

    final Script beta = new Chopper();

    @Override
    protected void setup() {
        submit(new Startup());
    }

    private class Startup implements Task {

        @Override
        public void run() {
            beta.start();
        }
    }
}
