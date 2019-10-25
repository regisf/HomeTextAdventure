package org.homerpg;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

class Game {
    private Place currentPlace = new Place();

    void run() {

        Resources.getInstance();

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

    private String waitForUser() {
        System.out.println();
        System.out.print("Que voulez vous faire ? ~> ");
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }

    private void doAction(final String action) {
        if ("?".equals(action) || "aide".equals(action)) {
            System.out.println("Actions disponible:");
            System.out.println("    revenir");
            System.out.println("    quitter");

            currentPlace.displayHelp();
        } else {
            currentPlace.doAction(action);

            String dest = currentPlace.getDestination();
            if (dest == null) {
                return;
            }

            changePlace(dest);
        }
    }

    /**
     * Display the how to use informaiton
     */
    private void displayIntroduction() {
        try {
            String title = Resources.getInstance().getMainTitleFilename();
            Path path = Paths.get(title);
            String content = Files.readString(path);

            System.out.println(content);
        } catch (IOException e) {
            System.err.println("Erreur. impossible de lire le fichier main.txt");
            System.exit(1);
        }
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
