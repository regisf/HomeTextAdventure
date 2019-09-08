package org.clirpg;

import org.clirpg.content.Action;

import java.util.List;

public class PlaceContent {
    private String name;
    private String description;
    private List<Action> actions;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }
}
