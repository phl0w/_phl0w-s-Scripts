package org.ittokenexchanger.user;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.util.Timer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Utilities {

    public static boolean waitFor(final long timeout, final Condition con) {
        final Timer t = new Timer(timeout);
        while (t.isRunning() && !con.validate()) {
            Task.sleep(20);
        }
        return con.validate();
    }

    public static int getPrice(int id) throws IOException {
        URL url = new URL("http://open.tip.it/json/ge_single_item?item=" + id);
        URLConnection con = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                con.getInputStream()));
        String line = "";
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            line += inputLine;
        }
        in.close();
        if (!line.contains("mark_price"))
            return -1;
        line = line.substring(line.indexOf("mark_price\":\"")
                + "mark_price\":\"".length());
        line = line.substring(0, line.indexOf("\""));
        return Integer.parseInt(line.replaceAll(",", ""));
    }
}
