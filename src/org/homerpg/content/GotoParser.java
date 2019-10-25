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
