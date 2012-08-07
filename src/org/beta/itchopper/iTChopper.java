package org.beta.itchopper;

import org.beta.itchopper.strategies.Chopper;
import org.beta.itchopper.strategies.Turn;
import org.beta.itchopper.user.Paint;
import org.beta.itchopper.user.Variables;
import org.powerbot.beta.core.script.Script;
import org.powerbot.concurrent.LoopTask;
import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.bot.event.MessageEvent;
import org.powerbot.game.bot.event.listener.MessageListener;
import org.powerbot.game.bot.event.listener.PaintListener;

import java.awt.*;


@Manifest(authors = {"phl0w"}, name = "iTChopper", description = "Testing the new framework :)", version = 1.0d)
public class iTChopper extends ActiveScript implements PaintListener, MessageListener {

    @Override
    protected void setup() {
        final Script beta_script = new Chopper();
        final Turn t = new Turn();
        t.setLock(false);
        t.setSync(true);
        provide(t);

        submit(new org.powerbot.concurrent.Task() {
            @Override
            public void run() {
                beta_script.start();
            }
        });
        submit(new LoopTask() {
            @Override
            public void run() {
                beta_script.setPaused(false);

                while (running) {
                    final int wait = loop();
                    if (wait > 0) {
                        Time.sleep(wait);
                    } else if (wait == -1) {
                        running = false;
                        beta_script.shutdown();
                        final Timer timer = new Timer(10000);
                        while (timer.isRunning() && beta_script.isActive()) {
                            Time.sleep(100);
                        }
                        if (beta_script.isActive()) {
                            beta_script.stop();
                        }
                        kill();
                    }
                }

                if (!killed) {
                    beta_script.setPaused(true);
                } else {
                    beta_script.stop();
                }
            }

            @Override
            public int loop() {
                if (beta_script.isActive()) {
                    return 1000;
                }
                iTChopper.this.stop();
                return -1;
            }
        });

        setIterationDelay(2000);
    }

    @Override
    public void onRepaint(Graphics g) {
        Paint.repaint((Graphics2D) g);
    }

    @Override
    public void messageReceived(MessageEvent e) {
        String msg = e.getMessage();
        if (msg.contains("You get some")) {
            Variables.chopped++;
        }
    }
}