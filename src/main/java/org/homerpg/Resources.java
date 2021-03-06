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

package org.homerpg;

import java.io.InputStream;
import java.net.URL;

/**
 * Manage the resource files
 */
public class Resources {
    private static Resources resources = null;

    /**
     * Create the resources object if it is not instancied yet
     *
     * @return The resources object
     */
    public static Resources getInstance() {
        if (resources == null) {
            resources = new Resources();
        }

        return resources;
    }

    InputStream getFileAsStream(final String name)  {
        InputStream input = Resources.class.getResourceAsStream("/resources/" + name);
        if (input == null) {
            input = Resources.class.getClassLoader().getResourceAsStream(name);
        }

        return input;
    }

    /**
     * Get the main file.
     *
     * @return The main file as stream
     */
    InputStream getMainTitleFilename() {
        return getFileAsStream("main.txt");
    }

    /**
     * Construct the file path from its name
     *
     * @param filename The file name without extension
     * @return The file path with extension
     */
    public InputStream getFromFilename(final String filename) {
        return getFileAsStream(filename + ".xml");
    }

}
