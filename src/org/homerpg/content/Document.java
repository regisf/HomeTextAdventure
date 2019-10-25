package org.homerpg.content;


import org.homerpg.Utils;
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
        final String[] desc = document
                .getElementsByTagName("description")
                .item(0)
                .getTextContent()
                .trim()
                .split("\\n");

        return Utils.removeSpacesOfEachLines(desc);
    }

    List<Action> getActions() {
        final NodeList actionNodes = document.getElementsByTagName("action");
        List<Action> actions = new ArrayList<>();

        for (int i = 0; i < actionNodes.getLength(); i++) {
            final Node actionNode = actionNodes.item(i);
            ActionParser actionParser = new ActionParser(actionNode);
            actions.add(actionParser.getAction());
        }

        return actions;
    }

    List<Goto> getGotos() {
        final NodeList gotoNodes = document.getElementsByTagName("goto");
        List<Goto> gotos = new ArrayList<>();

        for (int i = 0; i < gotoNodes.getLength(); i++) {
            Node gotoNode = gotoNodes.item(i);

            GotoParser parser = new GotoParser(gotoNode);
            Goto gto = parser.getGoto();
            gotos.add(gto);
        }

        return gotos;
    }
}

