package com.ellen.uno.models;

import java.util.ArrayList;

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

    public void setInHand(ArrayList<Card> inHand) {
        this.inHand = inHand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
