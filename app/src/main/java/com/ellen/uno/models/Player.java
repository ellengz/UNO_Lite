package com.ellen.uno.models;

import java.util.ArrayList;

public class Player {

    public ArrayList<Card> inHand;
    public String type;

    public Player() {
        this.inHand = new ArrayList<>();
    }

    /**
     * pick up a new card
     * @param cardReceived
     */
    public void pick(Card cardReceived) {
        inHand.add(cardReceived);
    }

}
