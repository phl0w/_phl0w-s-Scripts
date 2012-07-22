package org.itplanker.constants;

import org.itplanker.Variables;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.map.TilePath;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Data {

    public static final Tile MUSICIAN_TILE = new Tile(3290, 3455, 0);
    public static final TilePath PATH_TO_SAWMILL = new TilePath(new Tile[]{new Tile(3265, 3429, 0), new Tile(3277, 3431, 0),
            new Tile(3280, 3436, 0), new Tile(3286, 3449, 0), new Tile(3287, 3457, 0), new Tile(3291, 3468, 0), new Tile(3294, 3476, 0),
            new Tile(3301, 3486, 0), new Tile(3301, 3491, 0)});
    public static final TilePath PATH_TO_BANK = new TilePath(new Tile[]{new Tile(3301, 3486, 0), new Tile(3294, 3476, 0),
            new Tile(3291, 3468, 0), new Tile(3287, 3457, 0), new Tile(3286, 3449, 0), new Tile(3280, 3436, 0), new Tile(3277, 3431, 0),
            new Tile(3265, 3429, 0), new Tile(3253, 3420, 0)});
    public static final int SAWMILL_OPERATOR = 4250; // Buy-plank
    public static final int MUSICIAN = 8700; // Listen-to

    public static final int NORMAL_LOG = 1511;
    public static final int OAK_LOG = 1521;
    public static final int TEAK_LOG = 6333;
    public static final int MAHOGANY_LOG = 6332;


    private static NPC operator;

    public static NPC getSawmillOperator() {
        return operator == null ? (operator = NPCs.getNearest(SAWMILL_OPERATOR)) : operator;
    }

    public static NPC getMusician() {
        return NPCs.getNearest(MUSICIAN);
    }

    public static WidgetChild getLogWidget() {
        switch (Variables.log) {
            case NORMAL_LOG:
                return Variables.Plank.NORMAL.getCache();
            case OAK_LOG:
                return Variables.Plank.OAK.getCache();
            case TEAK_LOG:
                return Variables.Plank.TEAK.getCache();
            case MAHOGANY_LOG:
                return Variables.Plank.MAHOGANY.getCache();
        }
        return null;
    }

    public static int getPlank() {
        switch (Variables.log) {
            case NORMAL_LOG:
                return 960;
            case OAK_LOG:
                return 8778;
            case TEAK_LOG:
                return 8780;
            case MAHOGANY_LOG:
                return 8782;
        }
        return -1;
    }

    public static int getProfit() {
        return Variables.planked * Variables.profitPerLog;
    }

    public static int getProfitPerLog() {
        int fee = 100;
        int pricePerLog = 0, pricePerPlank = 0;
        try {
            pricePerLog = getPrice(Variables.log);
            pricePerPlank = getPrice(Data.getPlank());
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (Variables.log) {
            case Data.NORMAL_LOG:
                break;
            case Data.OAK_LOG:
                fee = 250;
                break;
            case Data.TEAK_LOG:
                fee = 500;
                break;
            case Data.MAHOGANY_LOG:
                fee = 1500;
                break;
        }
        return (pricePerPlank - pricePerLog) - fee;
    }

    private static int getPrice(int id) throws IOException {
        URL url = new URL("http://open.tip.it/json/ge_single_item?item=" + id);
        URLConnection con = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line = "";
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            line += inputLine;
        }
        in.close();
        if (!line.contains("mark_price"))
            return -1;
        line = line.substring(line.indexOf("mark_price\":\"") + "mark_price\":\"".length());
        line = line.substring(0, line.indexOf("\""));
        return Integer.parseInt(line.replaceAll(",", ""));
    }

}
