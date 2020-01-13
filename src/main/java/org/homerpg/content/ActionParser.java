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

package main.java.org.homerpg.content;

import main.java.org.homerpg.Utils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Parse the XML file place
 */
class ActionParser {
    private final Node node;
    private Action action;

    /**
     * Parse an action tag
     * @param actionNode The document Node
     * @see Document
     */
    ActionParser(final Node actionNode) {
        node = actionNode;
    }

    /**
     * Parse the the content
     */
    void parse() {
        action = new Action();
        action.setName(getName());
        action.setVisible(getIsVisible());


        NodeList children = node.getChildNodes();
        for (int j = 0; j < children.getLength(); j++) {
            final Node item = children.item(j);

            if ("default".equals(item.getNodeName())) {
                String description = getDefaultAction(item);
                action.setDefaultAction(description);
            } else if ("item".equals(item.getNodeName())) {
                Item actionItem = Item.fromNode(item);
                action.addItem(Item.fromNode(item));
            }
        }
    }

    /**
     * Get from the default action from XML file if exists
     * @param node The XML Node
     * @return The default action
     */
    private String getDefaultAction(final Node node) {
        return Utils.removeSpacesOfEachLines(node.getTextContent().split("\\n"));
    }

    /**
     * Return the value of the action parsed from XML file
     *
     * @return The action value
     */
    Action getAction() {
        return action;
    }

    /**
     * Extract the action name from the Node object
     *
     * @return The action name
     */
    private String getName() {
        return node
                .getAttributes()
                .getNamedItem("name")
                .getTextContent();
    }

    /**
     * Get the visible flag. The flag may be yes (or what ever) or "no".
     * By default the value is visible
     *
     * @return The visible value
     */
    private boolean getIsVisible() {
        boolean visible = true;
        final Node visibleProp = node
                .getAttributes()
                .getNamedItem("visible");

        if (visibleProp != null) {
            final String visibleAttr = visibleProp.getTextContent();

            if ("no".equals(visibleAttr)) {
                visible = false;
            }
        }

        return visible;
    }
}
