package org.clirpg;

import org.clirpg.place.Front;
import org.clirpg.place.Garage;
import org.clirpg.place.IPlace;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Game {
    private IPlace currentRoom;

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
                    currentRoom.display();
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

            currentRoom.displayHelp();
        } else {
            currentRoom.doAction(action);

            String dest = currentRoom.getDestination();
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
        System.out.println(where);
        switch (where) {
            case "front":
                currentRoom = new Front();
                break;

            case "garage":
                currentRoom = new Garage();
                break;

            default:
                System.err.println("Ceci n'est pas encore implémenter");
                return;

        }

        currentRoom.display();
    }
}
