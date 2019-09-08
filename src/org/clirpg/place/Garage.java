package org.clirpg.place;

import org.clirpg.Game;

public class Garage implements IPlace {
    private final Game game;

    public Garage(final Game game) {
        this.game = game;
    }

    @Override
    public void display() {
        System.out.println();
    }

    @Override
    public void doHelp() {
        System.out.println("Aide a implementer");
    }

    @Override
    public void doAction(String action) {
        System.out.println("Action a implementer");
    }
}
