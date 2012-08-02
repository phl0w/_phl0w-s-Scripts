package org.phl0w.chatlogs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class Merge {

    public static void main(String... args) {
        new Merge();
    }

    public Merge() {
        long startTime = System.currentTimeMillis();
        if (Variables.ROOT_FOLDER != null && Variables.ROOT_FOLDER.exists()) {
            for (final File f : Variables.ROOT_FOLDER.listFiles()) {
                if (f.isDirectory() && f.getName().contains("System")) { //mandatory check
                    for (final File sub : f.listFiles()) {
                        String channelName = (!sub.getName().contains("#") ? "privmsg-" : "") + sub.getName().split("-")[1].replaceAll(".log", "");
                        if (Variables.files.containsKey(channelName)) {
                            ArrayList<File> tempFiles = new ArrayList<File>();
                            Collections.addAll(tempFiles, Variables.files.get(channelName));
                            tempFiles.add(sub);
                            File[] array = new File[tempFiles.size()];
                            array = tempFiles.toArray(array);
                            Variables.files.put(channelName, array);
                        } else {
                            Variables.files.put(channelName, new File[]{sub});
                        }
                    }
                }
            }
        } else {
            Variables.log.info("No log folder detected.");
        }
        String channel;
        File f;
        for (final Map.Entry<String, File[]> es : Variables.files.entrySet()) {
            channel = es.getKey();
            f = new File(Variables.ROOT_FOLDER.getAbsolutePath() + "/merged-" + channel + ".log");
            try {
                if (f.exists()) {
                    if (f.delete()) {
                        if (f.createNewFile()) {
                            Variables.log.info("Creating temporary storage file...");
                        }
                    }
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            Variables.filesToWrite.put(f, es.getValue());
        }
        try {
            BufferedWriter bw = null;
            File fix = null;
            for (final Map.Entry<File, File[]> entry : Variables.filesToWrite.entrySet()) {
                fix = entry.getKey();
                bw = new BufferedWriter(new FileWriter(fix));
                for (File read : entry.getValue()) {
                    bw.write(Functions.readFile(read.getAbsolutePath()));
                }
                bw.flush();
            }
            if (bw != null) {
                bw.close();
            }
            String update = Functions.updateLines(fix);
            bw = new BufferedWriter(new FileWriter(fix));
            bw.write(update);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("That took " + (System.currentTimeMillis() - startTime) + " ms.");
    }
}
