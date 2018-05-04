package com.ellen.uno.model;

public class User extends Player{

    public Card play (Card targetCard, Card chosenCard) {

        if (targetCard.getNumber() == chosenCard.getNumber() ||
                targetCard.getColor() == chosenCard.getColor()) {
            inHand.remove(chosenCard);
            return chosenCard;
        } else {
            return null;
        }

    }

}
