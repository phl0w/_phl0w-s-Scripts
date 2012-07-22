package org.itplanker;

import org.itplanker.constants.Data;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

public class Variables {

    public static boolean guiDone;

    public static int log = Data.OAK_LOG;
    public static boolean restEnabled = true;
    public static int restAt = 40;

    public static int planked;
    public static int profitPerLog;

    public static long startTime;

    public static String status = "nothing";
    private static final int PARENT_WIDGET_ID = 403;

    public enum Plank {
        NORMAL(PARENT_WIDGET_ID, 12),
        OAK(PARENT_WIDGET_ID, 13),
        TEAK(PARENT_WIDGET_ID, 14),
        MAHOGANY(PARENT_WIDGET_ID, 15);

        private final int widgetId;
        private final int widgetChildId;

        private WidgetChild cache;

        private Plank(int widgetId, int widgetChildId) {
            this.widgetId = widgetId;
            this.widgetChildId = widgetChildId;
        }

        public int getWidgetId() {
            return widgetId;
        }

        public int getWidgetChildId() {
            return widgetChildId;
        }

        public WidgetChild getCache() {
            return (cache == null || !cache.validate()) ? (cache = Widgets.get(widgetId, widgetChildId)) : cache;
        }
    }

}
