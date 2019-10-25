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

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class GotoParser {
    private final Node node;
    private Goto gto;

    GotoParser(final Node gotoNode) {
        node = gotoNode;

        gto = new Goto();
        gto.setName(getName());
        gto.setDestination(getDestination());
        gto.setAliases(getAliases());
    }

    public Goto getGoto() {
        return gto;
    }

    private String getName() {
        return node.getAttributes().getNamedItem("name").getTextContent();
    }

    private String getDestination() {
        return node.getAttributes().getNamedItem("place").getTextContent();
    }

    private List<String> getAliases() {
        List<String> aliasList = new ArrayList<>();

        NodeList nodeList = node.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node item = nodeList.item(i);

            if ("alias".equals(item.getNodeName())) {
                aliasList.add(getAliasName(item));
            }
        }

        return aliasList;
    }

    private String getAliasName(final Node item) {
        return item
                .getAttributes()
                .getNamedItem("name")
                .getTextContent();
    }
}
