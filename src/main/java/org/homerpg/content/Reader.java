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

import org.homerpg.Content;
import org.homerpg.Resources;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Reader {
    private String fullPath;

    public Reader(final String filename) {
        fullPath = Resources.getInstance().getFromFilename(filename);
    }

    public Content readContent() {
        Document document = openDocument();

        String name = document.getName();
        String description = document.getDescription();
        List<Action> actions = document.getActions();
        List<Goto> gotos = document.getGotos();

        Content content = new Content();
        content.setName(name);
        content.setDescription(description);
        content.setActions(actions);
        content.setGotos(gotos);

        return content;
    }

    private Document openDocument() {
        File file = new File(fullPath);
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

        org.w3c.dom.Document document = null;
        try {
            document = documentBuilder.parse(file);
        } catch (SAXException | IOException e) {
            System.err.println("An error occured. Unable to parse " +
                    "the xml document");
            System.exit(1);
        }

        return new Document(document);
    }
}
