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

package main.java.org.homerpg.content;

import java.util.ArrayList;
import java.util.List;

/**
 * The Action class contains all necessary for an action.
 */
public class Action {
    private String name;
    private String defaultAction = null;
    private boolean visible;
    private List<Item> items = new ArrayList<>();

    /**
     * Set the name of the action
     * @param name The name of the action
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Get the name of the action
     * @return The action name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the visibility for the action
     * @param visible The action visibility
     */
    void setVisible(final boolean visible) {
        this.visible = visible;
    }

    /**
     * Check if the action is visible or not
     * @return True if visible, False elsewhere
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Add an Item to the action.
     * @param item The item to add
     * @see Item
     */
    void addItem(final Item item) {
        items.add(item);
    }

    /**
     * Get all items. The items list always exists
     * @return The items list
     * @see Item
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Set the default action if exists
     * @param defaultAction The default action
     */
    void setDefaultAction(final String defaultAction) {
        this.defaultAction = defaultAction;
    }

    /**
     * Get the default action if exists
     * @return The default action or null if not exists
     */
    public String getDefaultAction() {
        return defaultAction;
    }
}
