package com.ellen.uno.models;

import java.util.ArrayList;
import java.util.Random;

/**
 * computer with a put strategy
 */
public class Computer extends Player{

    private Random random = new Random();

    public Computer() {
        setType("COMPUTER");
    }
    /**
     * play for this turn
     * @param targetCard card on the top of pile
     * @return null if pick, or Card if able to put
     */
    public Card tryPut (Card targetCard) {

        ArrayList<Card> candidates = new ArrayList<>(); // playable cards

        for (Card card: getInHand()) {
            if (card.getColor() == targetCard.getColor() ||
                    card.getNumber() == targetCard.getNumber()) {
                candidates.add(card);
            }
        }

        if (candidates.size() == 0) {
            return null;
        } else {
            Card card = candidates.get(random.nextInt(candidates.size()));
            getInHand().remove(card);
            return card;
        }
    }
}
