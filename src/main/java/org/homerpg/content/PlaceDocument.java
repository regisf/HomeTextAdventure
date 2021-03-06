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

import org.homerpg.Utils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * The Document parse the
 */
public class PlaceDocument {
    private final Document document;

    PlaceDocument(final Document doc) {
        document = doc;
    }

    public String getName() {
        return document
                .getElementsByTagName("name")
                .item(0)
                .getTextContent().trim();
    }

    public String getDescription() {
        final String[] desc = document
                .getElementsByTagName("description")
                .item(0)
                .getTextContent()
                .trim()
                .split("\\n");

        return Utils.removeSpacesOfEachLines(desc);
    }

    public List<Action> getActions() {
        final NodeList actionNodes = document.getElementsByTagName("action");
        final List<Action> actions = new ArrayList<>();

        for (int i = 0; i < actionNodes.getLength(); i++) {
            final Node actionNode = actionNodes.item(i);
            ActionParser actionParser = new ActionParser(actionNode);
            actionParser.parse();
            actions.add(actionParser.getAction());
        }

        return actions;
    }

    public List<Goto> getGotos() {
        final NodeList gotoNodes = document.getElementsByTagName("goto");
        final List<Goto> gotos = new ArrayList<>();

        for (int i = 0; i < gotoNodes.getLength(); i++) {
            Node gotoNode = gotoNodes.item(i);

            GotoParser parser = new GotoParser(gotoNode);
            Goto gto = parser.getGoto();
            gotos.add(gto);
        }

        return gotos;
    }
}

