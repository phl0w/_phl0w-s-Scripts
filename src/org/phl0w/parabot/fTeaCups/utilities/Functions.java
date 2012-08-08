package org.phl0w.parabot.fTeaCups.utilities;

import java.util.Random;

public class Functions {

    final static Random r = new Random();

    public static void sleep(int... args) {
        switch (args.length) {
            default:
            case 1:
                sleep(args[0]);
                break;
            case 2:
                sleep(r.nextInt(args[1] - args[2]) + args[1]);
                break;
        }
    }

    private void sleep(int arg) {
        try {
            Thread.sleep(arg);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public static boolean waitFor(long timeout, Condition c) {
        final long startTime = System.currentTimeMillis();
        while ((System.currentTimeMillis() - startTime) < timeout && !c.validate()) {
            sleep(50);
        }
        return c.validate();
    }
}
