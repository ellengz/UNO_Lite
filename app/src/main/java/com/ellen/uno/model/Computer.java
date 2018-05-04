package com.ellen.uno.model;

import java.util.ArrayList;
import java.util.Random;

public class Computer {

    public ArrayList<Card> inHand; // cards in hand
    Random random = new Random();

    /**
     * play for this turn
     * @param targetCard - card on the top of pile
     * @param cards - cards left in deck
     * @return null if pick, or Card if able to put
     */
    public Card play (Card targetCard, ArrayList<Card> cards) {

        ArrayList<Card> candidates = new ArrayList<>(); // playable cards

        for (Card card: inHand) {
            if (card.getColor() == targetCard.getColor() ||
                    card.getNumber() == targetCard.getNumber()) {
                candidates.add(card);
            }
        }

        if (candidates.size() == 0) {
            this.pick(cards);
            return null;
        } else {
            return this.put(candidates);
        }
    }

    /**
     * pick a card from the cards
     * @param cards
     */
    private void pick(ArrayList<Card> cards) {
        inHand.add(cards.get(cards.size() - 1));
    }

    /**
     * randomly choose a card to be placed
     * @param candidates
     * @return Card
     */
    private Card put(ArrayList<Card> candidates) {
        Card card = candidates.get(random.nextInt(candidates.size()));
        inHand.remove(card);
        return card;
    }
}
