package org.clirpg.content;

import org.w3c.dom.Node;

public class Item {
    private String name;
    private String description;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    static Item fromNode(final Node item) {
        Item actionItem = new Item();
        actionItem.setName(item.getAttributes().getNamedItem("name").getTextContent());
        actionItem.setDescription(item.getTextContent());

        return actionItem;
    }
}
