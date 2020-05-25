package org.homerpg;

public class Player {
    private static Player player = null;
    private float energy = 1.0f;
    private int objectCount = 0;
    private Inventory inventory = null;

    private Player() {
        inventory = new Inventory();
    }

    public static Player getInstance() {
        if (player == null) {
            player = new Player();
        }

        return player;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public float getEnergy() {
        return energy;
    }

    public void setEnergy(float energy) {
        this.energy = energy;
    }

    public int getObjectCount() {
        return objectCount;
    }

    public void setObjectCount(int objectCount) {
        this.objectCount = objectCount;
    }
}
