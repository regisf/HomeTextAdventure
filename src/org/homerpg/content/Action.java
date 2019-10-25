package org.homerpg.content;

import java.util.ArrayList;
import java.util.List;

public class Action {
    private String name;
    private String defaultAction;
    private boolean visible;
    private List<Item> items = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    void addItem(final Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public void setDefaultAction(final String defaultAction) {
        this.defaultAction = defaultAction;
    }

    public String getDefaultAction() {
        return defaultAction;
    }
}
