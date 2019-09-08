package org.clirpg.content;

import java.util.ArrayList;
import java.util.List;

public class Action {
    private String name;
    private boolean visible;
    private String description;
    private List<Item> items = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    void addItem(final Item item) {
        items.add(item);
    }
}
