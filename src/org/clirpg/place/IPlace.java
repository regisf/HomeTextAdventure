package org.clirpg.place;

import org.clirpg.IContent;
import org.clirpg.content.Action;
import org.clirpg.content.Goto;
import org.clirpg.content.Item;

import java.util.List;
import java.util.stream.Collectors;

public abstract class IPlace {
    private IContent content;
    private String destination;

    public IContent getContent() {
        return content;
    }

    void setContent(IContent content) {
        this.content = content;
    }

    /**
     * Display the main description
     */
    public void display() {
        System.out.println(getContent().getDescription());
    }

    public void displayHelp() {
        List<Action> actions = getContent().getActions();

        for (final Action action : actions) {
            if (action.isVisible()) {
                System.out.println("\t" + action.getName());
            }
        }
    }

    public void doAction(String action) {
        setDestination(null);

        String[] tokens = action.split("\\s");
        String verb = tokens[0];
        String destination = "";

        if (tokens.length > 1) {
            destination = tokens[1];
        }

        if ("aller".equals(verb)) {
            List<Goto> gotos = getContent().getGotos();
            for (Goto gto : gotos) {
                if (gto.hasDestination(destination)) {
                    setDestination(gto.getDestination());
                    break;
                }
            }
        } else if ("revenir".equals(verb)) {
            System.err.println("Pas encore implémentée");
        } else {
            List<Action> actions = getContent().getActions();

            for (final Action act : actions) {
                if (act.getName().equals(verb)) {
                    if (destination.isEmpty()) {
                        System.out.println(act.getDefaultAction());
                    } else {
                        String desc = getDescriptionFromDestination(act, destination);
                        System.out.println(desc);
                    }
                }
            }
        }
    }

    private void setDestination(String dest) {
        destination = dest;
    }

    private String getDescriptionFromDestination(final Action act,
                                                 final String destination) {
        List<Item> items = act.getItems();
        final List<Item> result = act
                .getItems()
                .stream()
                .filter(item -> destination.equals(item.getName()))
                .collect(Collectors.toList());

        return result.size() == 0
                ? "Je n'ai pas compris \"" + destination + "\""
                : result.get(0).getDescription();
    }

    public String getDestination() {
        return destination;
    }
}
