/*
 * HomeRPG : Home Role Playing Game
 * Copyright (c) 2019 Régis FLORET <regisfloret@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.homerpg;

import org.homerpg.content.Action;
import org.homerpg.content.Goto;
import org.homerpg.content.Item;
import org.homerpg.content.Reader;

import java.util.List;
import java.util.stream.Collectors;

public class Place {
    private PlaceContent placeContent;
    private String destination;

    public PlaceContent getPlaceContent() {
        return placeContent;
    }

    private void setPlaceContent(PlaceContent placeContent) {
        this.placeContent = placeContent;
    }

    /**
     * Display the main description
     */
    void display() {
        System.out.println(getPlaceContent().getDescription());
    }

    void displayHelp() {
        List<Action> actions = getPlaceContent().getActions();

        for (final Action action : actions) {
            if (action.isVisible()) {
                System.out.println("\t" + action.getName());
            }
        }
    }

    void doAction(String action) {
        setDestination(null);

        String[] tokens = action.split("\\s");
        String verb = tokens[0];
        String destination = "";

        if (tokens.length > 1) {
            destination = tokens[1];
        }

        if ("aller".equals(verb)) {
            boolean found = foundGoto(destination);
            if (!found) {
                System.out.println("Je ne sais pas aller ver \"" + destination + "\"");
            }
        } else if ("revenir".equals(verb)) {
            System.err.println("Pas encore implémentée");
        } else {
            String description = getPlaceDescription(verb);
            System.out.println(description);
        }
    }

    private String getPlaceDescription(final String verb) {
        List<Action> actions = getPlaceContent().getActions();

        for (final Action act : actions) {
            if (act.getName().equals(verb)) {
                if (destination.isEmpty()) {
                    return act.getDefaultAction();
                } else {
                    return getDescriptionFromDestination(act, destination);
                }
            }
        }

        return null;
    }

    private boolean foundGoto(String destination) {
        List<Goto> gotos = getPlaceContent().getGotos();
        for (Goto gto : gotos) {
            if (gto.hasDestination(destination)) {
                setDestination(gto.getDestination());
                return true;
            }
        }
        return false;
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

    String getDestination() {
        return destination;
    }

    private void setDestination(String dest) {
        destination = dest;
    }

    void changePlace(final String where) {
        Reader reader = new Reader("front");
        setPlaceContent(reader.readContent());
    }
}
