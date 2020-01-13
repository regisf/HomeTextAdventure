/*
 * HomeRPG : Home Role Playing Game
 * Copyright (c) 2019 Régis FLORET <regisfloret@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package main.java.org.homerpg;

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
