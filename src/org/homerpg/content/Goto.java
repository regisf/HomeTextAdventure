package org.homerpg.content;

import java.util.List;

public class Goto {
    private String name;
    private String destination;
    private List<String> aliases;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    public List<String> getAliases() {
        return aliases;
    }

    void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }

    public boolean hasDestination(String name) {
        return getName().equals(name) || getAliases().contains(name);
    }
}
