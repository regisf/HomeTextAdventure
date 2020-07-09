package org.homerpg;

public class Inventory {
    private final Hand leftHand = new Hand();
    private final Hand rightHand = new Hand();
    private final Backpack backPack = new Backpack();

    public void addItemOnHand(Object obj) throws NoHandFreeError {
        if (leftHand.isFull() && rightHand.isFull()) {
            throw new NoHandFreeError();
        }

        Hand hand = leftHand.isFull() ? rightHand : leftHand;
        hand.storeObject(obj);
    }
}
