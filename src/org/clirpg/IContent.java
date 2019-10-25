package org.clirpg;

import org.clirpg.content.Action;
import org.clirpg.content.Goto;

import java.util.List;

public abstract class IContent {
    private List<Action> actions;
    private String name;
    private String description;
    private List<Goto> gotos;

    public List<Action> getActions() {
        return actions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void displayName() {
        System.out.println("--> " + getName() + " <--");
        System.out.println();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public void setGotos(List<Goto> gotos) {
        this.gotos = gotos;
    }

    public List<Goto> getGotos() {
        return gotos;
    }
}
