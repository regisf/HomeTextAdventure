package org.clirpg.place;
import org.clirpg.PlaceContent;
import org.clirpg.content.Reader;
import org.clirpg.Game;

public class Front implements IPlace {
    private Game game;
    private PlaceContent content;

    public Front(final Game game) {
        this.game = game;

        Reader reader = new Reader("front");
        content = reader.readContent();

        System.out.println(content.getName());
    }

    /**
     * Display the main description
     */
    public void display() {
        System.out.println(content.getDescription());
    }

    public void doAction(final String action) {
        if (action.isEmpty()) {
            return;
        }

        String[] tokens = action.split("\\s");
        String verb = tokens[0];
        String destination = "";

        if (tokens.length > 1) {
            destination = tokens[1];
        }

        switch (verb) {
            case "aide":
            case "?":
                doHelp();
                break;

            case "regarder":
                doWatch(destination);
                break;

            case "aller":
                doGo(destination);
                break;

            case "ouvrir":
                doOpen(destination);
                break;
        }
    }

    @Override
    public void doHelp() {
        System.out.println("    regarder");
        System.out.println("    aller [quoi]");
        System.out.println("    ouvrir");
    }

    private void doWatch(final String destination) {
        if ("porte".equals(destination)) {
            System.out.println("Ben c'est une porte quoi");
        } else if ("garage".equals(destination)) {
            String comment = "Le garage se trouve à une courte distance\n" +
                    "Un petit chemin de pierre va jusqu'à lui\n" +
                    "Il y a deux boxes dans ce garage\n" +
                    "Un ouvert, l'autre fermé à clef";

            System.out.println(comment);
        } else {
            display();
        }
    }

    private void doGo(final String destination) {
        if (destination.isEmpty()) {
            System.out.println("Où est-ce que vous êtes supposer aller ?");
            return;
        }


        if ("garage".equals(destination)) {
            game.goTo("garage");
        } else if ("porte".equals(destination)) {

        }
    }

    private void doOpen(final String what) {
        System.out.println("open " + what);
    }
}
