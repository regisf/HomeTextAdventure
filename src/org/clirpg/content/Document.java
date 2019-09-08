package org.clirpg.content;


import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

class Document {
    private final org.w3c.dom.Document document;

    Document(final org.w3c.dom.Document doc) {
        document = doc;
    }

    String getName() {
        return document
                .getElementsByTagName("name")
                .item(0)
                .getTextContent().trim();
    }

    String getDescription() {
        return document
                .getElementsByTagName("description")
                .item(0)
                .getTextContent().trim();
    }

    List<Action> getActions() {
        final NodeList actionNodes = document.getElementsByTagName("action");
        List<Action> actions = new ArrayList<Action>();

        for (int i = 0; i < actionNodes.getLength(); i++) {
            final Node actionNode = actionNodes.item(i);
            ActionParser actionParser = new ActionParser(actionNode);
            Action action = actionParser.getAction();
            actions.add(action);
        }

        return actions;
    }
}

