package com.ellen.uno.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class Computer extends Player{

    Random random = new Random();

    /**
     * play for this turn
     * @param targetCard - card on the top of pile
     * @return null if pick, or Card if able to put
     */
    public Card play (Card targetCard) {

        ArrayList<Card> candidates = new ArrayList<>(); // playable cards

        for (Card card: inHand) {
            if (card.getColor() == targetCard.getColor() ||
                    card.getNumber() == targetCard.getNumber()) {
                candidates.add(card);
            }
        }

        if (candidates.size() == 0) {
            return null;
        } else {
            return this.put(candidates);
        }
    }

    /**
     * randomly choose a card to be placed
     * @param candidates
     * @return Card
     */
    private Card put(ArrayList<Card> candidates) {
        Card card = candidates.get(random.nextInt(candidates.size()));
        Log.d("DEBUGGING", "Computer put " + card.toString());
        inHand.remove(card);
        return card;
    }
}
