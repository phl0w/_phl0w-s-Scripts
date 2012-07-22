package org.itplanker.functions;

import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.util.Timer;

public class Functions {

    public static boolean waitFor(long timeout, Condition condition) {
        Timer timer = new Timer(timeout);
        while (timer.isRunning() && !condition.validate()) {
            Time.sleep(50);
        }
        return condition.validate();
    }

}
