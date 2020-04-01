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

package org.homerpg.content;

import org.homerpg.Utils;
import org.w3c.dom.Node;

public class Item {
    private String name;
    private String description;

    static Item fromNode(final Node item) {
        Item actionItem = new Item();
        actionItem.setName(
                item.getAttributes().getNamedItem("name").getTextContent());

        final String content = Utils.removeSpacesOfEachLines(
                item.getTextContent().split("\\n"));
        actionItem.setDescription(content);

        return actionItem;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
