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
    private PlaceContent placeContent = null;
    private PlaceContent lastPlace = null;
    private String destination = null;

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
                System.out.print("\t");

                for(String s : action.getNames()) {
                    System.out.print(s + " ");
                }

                System.out.println();
            }
        }

        if (getPlaceContent().getGotos().size() > 0) {
            System.out.println("    où aller ?");
        }

        if (getPlaceContent().getActions().size() > 0) {
            System.out.println("    quoi faire ?");
        }
    }

    boolean doAction(String action) {
        setDestination(null);

        String[] tokens = action.split("\\s");
        String verb = tokens[0];

        if (tokens.length > 1) {
            setDestination(tokens[1]);
        }

        if ("aller".equals(verb)) {
            return doGoTo();
        }

        else if ("ou".equals(verb) && "aller".equals(destination)) {
            doWhereToGo();
        }

        else if ("quoi".equals(verb) && "faire".equals(destination)) {
            doWhatToDo();
        }

        else if ("revenir".equals(verb) || "retour".equals(verb)) {
            doGoBack();
        }

        else {
            String description = getPlaceDescription(verb);
            System.out.println(description);
        }

        return false;
    }

    private boolean doGoTo() {
        boolean found = foundGoto(getDestination());
        if (!found) {
            System.out.println("Je ne sais pas aller vers " +
                    '"' + getDestination() + '"');
            return false;
        }
        return true;
    }

    private void doGoBack() {
        if (getLastPlace() == null) {
            System.out.println("Vous ne pouvez pas revenir en arrière. " +
                    "Vous êtes à votre point de départ.");
        } else {
            PlaceContent content = getPlaceContent();
            setPlaceContent(getLastPlace());
            setLastPlace(content);
            System.out.println("Vous êtes maintenant : " +
                    getPlaceContent().getName());
        }
    }

    /**
     * Display what the user may do regarding the actions
     */
    private void doWhatToDo() {
        List<Action> actions = getPlaceContent().getActions();
        if (actions.size() == 0) {
            System.out.println("Vous ne pouvez rien faire");
            return;
        }

        System.out.println("Vous pouvez utiliser ces actions :");
        for(final Action action : actions) {
            System.out.println("    " + action.getNames());
        }
    }

    /**
     * Seek all actions and display what to do
     */
    private void doWhereToGo() {
        List<Goto> gotos = getPlaceContent().getGotos();
        if (gotos.size() == 0) {
            System.out.println("Vous n'avez nulle part où aller !");
            return;
        }

        System.out.println("Vous pouvez aller :");

        for(final Goto gto : gotos) {
            System.out.println("  → " + gto.getName());
        }

    }

    private String getPlaceDescription(final String verb) {
        List<Action> actions = getPlaceContent().getActions();
        String dest = getDestination();

        for (final Action action : actions) {
            if (action.contains(verb)) {
                if (dest == null || dest.isEmpty()) {
                    String defaultAction = action.getDefaultAction();
                    return (defaultAction == null) ? "Hein ?" : defaultAction;
                } else {
                    return getDescriptionFromDestination(action);
                }
            }
        }

        return "Je ne comprends pas ce que vous voulez dire avec " +
                '"' + verb + '"';
    }

    private boolean foundGoto(String dest) {
        List<Goto> gotos = getPlaceContent().getGotos();
        for (Goto gto : gotos) {
            if (gto.hasDestination(dest)) {
                setDestination(gto.getDestination());
                return true;
            }
        }
        return false;
    }

    private String getDescriptionFromDestination(final Action act) {
        final List<Item> result = act
                .getItems()
                .stream()
                .filter(item -> getDestination().equals(item.getName()))
                .collect(Collectors.toList());

        return result.size() == 0
                ? "Je n'ai pas compris \"" + getDestination() + "\""
                : result.get(0).getDescription();
    }

    public String getDestination() {
        return destination;
    }

    private void setDestination(String dest) {
        destination = dest;
    }

    void changePlace(final String where) {
        setLastPlace(getPlaceContent());

        Reader reader = new Reader(where);
        setPlaceContent(reader.readContent());
    }

    private PlaceContent getLastPlace() {
        return lastPlace;
    }

    private void setLastPlace(PlaceContent lastPlace) {
        this.lastPlace = lastPlace;
    }
}
