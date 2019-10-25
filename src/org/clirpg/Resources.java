package org.clirpg;

public class Resources {
    private static Resources resources = null;
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
        if (resources == null) {
            resources = new Resources();
        }

        return resources;
    }

    String getMainTitleFilename() {
        return baseDir + "main.txt";
    }

    public String getFromFilename(final String filename) {
        return baseDir + filename + ".xml";
    }

}
