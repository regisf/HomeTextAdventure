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

package org.homerpg.content;

import org.homerpg.PlaceContent;
import org.homerpg.Resources;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Reader {
    private final URL fullPath;

    public Reader(final String filename) {
        fullPath = Resources.getInstance().getFromFilename(filename);
    }

    /**
     * Read a XML document and create a place content
     *
     * @return The place content
     * @see PlaceContent
     */
    public PlaceContent readContent() {
        PlaceDocument placeDocument = openDocument();

        return PlaceContent.fromDocument(placeDocument);
    }

    /**
     * Open a XML document for a place
     *
     * @return A PlaceDocument
     * @see PlaceDocument
     */
    private PlaceDocument openDocument() {
        DocumentBuilder documentBuilder = createNewBuilder();
        Document document = parseXMLDocument(documentBuilder);

        return new PlaceDocument(document);
    }

    /**
     * Create a new builder or exit
     *
     * @return The newly created builder
     */
    private DocumentBuilder createNewBuilder() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                .newInstance();

        DocumentBuilder documentBuilder = null;

        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println("An error occured. Unable to build " +
                    "the xml document");
            System.exit(1);
        }

        return documentBuilder;
    }

    /**
     * Parse the XML document or exit on failure
     *
     * @param documentBuilder The document build as created in createNewBuilder
     * @return The XML Document
     * @see this.createNewBuilder
     */
    private Document parseXMLDocument(final DocumentBuilder documentBuilder) {
        Document document = null;
        File file = new File(fullPath.getPath());

        try {
            document = documentBuilder.parse(file);
        } catch (SAXException | IOException e) {
            System.err.println("An error occured. Unable to parse " +
                    "the xml document because: " + e.getMessage());
            System.exit(1);
        }

        return document;
    }
}
