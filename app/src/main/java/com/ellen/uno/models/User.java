package com.ellen.uno.models;

import android.util.Log;

public class User extends Player{

    public User() {
        this.type = "USER";
    }

    public boolean checkChosen(Card targetCard, Card chosenCard) {
        if (targetCard.getNumber() == chosenCard.getNumber() ||
                targetCard.getColor() == chosenCard.getColor()) {
            inHand.remove(chosenCard);
            Log.d("CARD", "User put " + chosenCard.toString() + " matching " + targetCard.toString());
            return true;
        } else {
            return false;
        }
    }

}
