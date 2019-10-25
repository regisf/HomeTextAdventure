package org.clirpg.content;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class ActionParser {
    private final Node node;
    private Action action;

    ActionParser(final Node actionNode) {
        node = actionNode;
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

    private String getDefaultAction(final Node node) {
        return node.getTextContent().trim();
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
