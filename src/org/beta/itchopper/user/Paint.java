package org.beta.itchopper.user;

import java.awt.*;

public class Paint {

    public static void repaint(Graphics2D g) {
        g.drawString("Nests: " + Variables.nests, 100, 100);
        g.drawString("Chopped: " + Variables.chopped, 100, 120);
    }
}
