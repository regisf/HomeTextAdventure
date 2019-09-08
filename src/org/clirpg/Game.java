package org.clirpg;

import org.clirpg.place.Front;
import org.clirpg.place.Garage;
import org.clirpg.place.IPlace;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Game {
    private IPlace currentRoom;

    void run() {
        Resources.getInstance();

        hello();
        currentRoom = new Front(this);
        currentRoom.display();

        while (true) {

            String action = waitForUser();
            if ("quitter".equals(action)) {
                break;
            } else if ("description".equals(action)) {
                currentRoom.display();
                continue;
            }


            doAction(action);
            currentRoom.doAction(action);
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
            System.out.println("    quitter");
        }
    }

    /**
     * Go to another place. if the place doesn't exist, display an error
     *
     * @param where Where to go.
     */
    public void goTo(final String where) {
        switch (where) {
            case "garage":
                currentRoom = new Garage(this);
                break;

            default:
                System.out.println("I don't know the place '" + where + "'");
                return;
        }

        currentRoom.display();
    }

    /**
     * Display the how to use informaiton
     */
    private void hello() {
        try {
            String content = Files.readString(
                    Paths.get(
                            Resources
                                    .getInstance()
                                    .getMainTitleFilename()
                    )
            );

            System.out.println(content);
        } catch (IOException e) {
            System.err.println("Erreur. impossible de lire le fichier main.txt");
            System.exit(1);
        }
    }
}
