package org.homerpg.content;

import org.homerpg.Utils;
import org.w3c.dom.Node;

public class ItemParser {
    private Node node;
    private Item item;

    public ItemParser(final Node node) {
        setNode(node);
        parse();
    }

    private void parse() {
        Item actionItem = new Item();
        actionItem.setName(getNode()
                .getAttributes()
                .getNamedItem("name")
                .getTextContent());

        final String[] nodeContent = getNode()
                .getTextContent()
                .split("\\n");

        final String content = Utils.removeSpacesOfEachLines(nodeContent);

        actionItem.setDescription(content);
        setItem(actionItem);
    }

    private void setItem(final Item item) {
        this.item = item;
    }

    public Item getItem() {
        return this.item;
    }
    private void setNode(Node node) {
        this.node = node;
    }

    private Node getNode() {
        return this.node;
    }
}
