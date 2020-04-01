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
import org.homerpg.content.PlaceDocument;

import java.util.List;

public class PlaceContent {
    private List<Action> actions;
    private String name;
    private String description;
    private List<Goto> gotos;

    public static PlaceContent fromDocument(PlaceDocument placeDocument) {
        String name = placeDocument.getName();
        String description = placeDocument.getDescription();
        List<Action> actions = placeDocument.getActions();
        List<Goto> gotos = placeDocument.getGotos();

        PlaceContent placeContent = new PlaceContent();
        placeContent.setName(name);
        placeContent.setDescription(description);
        placeContent.setActions(actions);
        placeContent.setGotos(gotos);

        return placeContent;
    }

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
