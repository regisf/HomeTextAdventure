package org.homerpg.content;

import org.homerpg.Utils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Node;

public class Item {
    private String name;
    private String description;

    @NotNull
    static Item fromNode(final Node item) {
        Item actionItem = new Item();
        actionItem.setName(
                item.getAttributes().getNamedItem("name").getTextContent());

        final String content = Utils.removeSpacesOfEachLines(
                item.getTextContent().split("\\n"));
        actionItem.setDescription(content);

        return actionItem;
    }

    @Contract(pure = true)
    public final String getName() {
        return this.name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    @Contract(pure = true)
    public final String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
