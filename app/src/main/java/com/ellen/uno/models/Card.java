package com.ellen.uno.models;

import com.ellen.uno.enums.Color;

public class Card {

    private int number;
    private Color color;

    public Card(int number, Color color) {
        this.number = number;
        this.color = color;
    }

    @Override
    public String toString() {
        return color + " " + number;
    }

    public Card() {

    }

    public int getNumber() {
        return number;
    }

    public Color getColor() {
        return color;
    }


}


