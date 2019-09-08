package org.clirpg;

public class Resources {
    private static Resources resouces = null;
    private String baseDir = "";

    private Resources() {
        String thisFile = Resources.class.getResource("Resources.class").toString();
        if (thisFile.startsWith("jar:")) {
            baseDir = "resources/";
        } else {
            baseDir = "src/resources/";
        }
    }

    public static Resources getInstance() {
        if (resouces == null) {
            resouces = new Resources();
        }

        return resouces;
    }

    public String getMainTitleFilename() {
        return baseDir + "main.txt";
    }

    public String getFronFilename() {
        return baseDir + "front.xml";
    }

}
