package com.ellen.uno.model;

import java.util.ArrayList;

public class Player {

    public ArrayList<Card> inHand;

    public Player() {
        this.inHand = new ArrayList<>();
    }

    /**
     * pick up a new card
     * @param newCard
     */
    public void pick(Card newCard) {
        inHand.add(newCard);
    }
}
