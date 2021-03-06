package com.ellen.uno.models;

import java.util.ArrayList;

/**
 * base class for player
 */
public class Player {

    private ArrayList<Card> inHand;

    private String type;

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

    public ArrayList<Card> getInHand() {
        return inHand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
