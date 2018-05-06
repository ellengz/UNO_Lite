package com.ellen.uno.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class Computer extends Player{

    Random random = new Random();

    public Computer() {
        this.type = "COMPUTER";
    }
    /**
     * play for this turn
     * @param targetCard - card on the top of pile
     * @return null if pick, or Card if able to put
     */
    public Card tryPut (Card targetCard) {

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
            Card card = candidates.get(random.nextInt(candidates.size()));
            Log.d("CARD", "Computer put " + card.toString() + " matching " + targetCard.toString());
            inHand.remove(card);
            return card;
        }
    }
//
//    /**
//     * randomly choose a card to be put
//     * @param candidates
//     * @return Card
//     */
//    private Card choosePut(ArrayList<Card> candidates) {
//
//        return card;
//    }
}