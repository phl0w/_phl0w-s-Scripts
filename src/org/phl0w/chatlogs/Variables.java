package org.phl0w.chatlogs;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.logging.Logger;

public class Variables {

    public static final Logger log = Logger.getLogger(Merge.class.getName());
    public static final File ROOT_FOLDER = new File(System.getProperty("user.home") + (System.getProperty("os.name").contains("Windows") ? "/AppData/Roaming/X-Chat 2/xchatlogs" : "/.xchat2/xchatlogs"));
    public static final HashMap<String, File[]> files = new HashMap<String, File[]>();
    public static final HashMap<File, File[]> filesToWrite = new HashMap<File, File[]>();
    public static final DateFormat FORMAT = new SimpleDateFormat("yyyy MMM dd hh:mm:sss");
    public static final String SEPARATOR = System.getProperty("line.separator");

}
