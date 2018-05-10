package com.ellen.uno.models;

/**
 * user with a card-playability check
 */
public class User extends Player{

    public User() {
        setType("USER");
    }

    public boolean checkChosen(Card targetCard, Card chosenCard) {
        if (targetCard.getNumber() == chosenCard.getNumber() ||
                targetCard.getColor() == chosenCard.getColor()) {
            getInHand().remove(chosenCard);
            return true;
        } else {
            return false;
        }
    }

}
