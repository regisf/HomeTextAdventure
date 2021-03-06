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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * The game class. Get the user input and manage the command
 *
 * @author Régis FLORET
 */
class Game {
    private final Place currentPlace = new Place();
    private final Player player = Player.getInstance();
    private Character character = null;

    /**
     * The game main loop
     */
    void run() throws IOException, Error {
        Resources.getInstance();

        Character character = Character.createFromUserEntry();
        setCharacter(character);

        displayIntroduction();
        changePlace("front");

        boolean quit = false;

        while (!quit) {
            String action = waitForUser();
            if (!action.isEmpty()) {
                quit = "quitter".equals(action);

                if ("description".equals(action)) {
                    currentPlace.display();
                } else {
                    doAction(action);
                }
            }
        }

        System.out.println("Je vous souhaite une bonne journée.");
    }

    private void setCharacter(Character character) {
        this.character = character;
    }

    /**
     * Get the user keyboard input
     *
     * @return The user command line
     */
    private String waitForUser() {
        System.out.println();
        System.out.print("Que voulez vous faire ? ~> ");
        Scanner scanner = new Scanner(System.in);

        String action = scanner.nextLine().strip();

        return Normalizer
                .normalize(action, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }

    /**
     * Perform the action entered by the user
     *
     * @param action The user command
     */
    private void doAction(final String action) {
        if ("?".equals(action) || "aide".equals(action)) {
            System.out.println("Actions disponible:");
            System.out.println("    revenir");
            System.out.println("    quitter");

            currentPlace.displayHelp();
        } else {
            boolean moveTo = currentPlace.doAction(action);

            if (moveTo) {
                String dest = currentPlace.getDestination();

                if (dest == null) {
                    return;
                }

                changePlace(dest);
            }
        }
    }

    /**
     * Display the how to use informaiton
     */
    private void displayIntroduction() {
        InputStream stream = Resources.getInstance().getMainTitleFilename();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String content = reader.lines().collect(Collectors.joining("\n"));
        System.out.println(content);
    }

    /**
     * Go to another place. if the place doesn't exist, display an error
     *
     * @param where Where to go.
     */
    private void changePlace(final String where) {
        currentPlace.changePlace(where);
        currentPlace.display();
    }
}
