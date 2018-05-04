package com.ellen.uno.model;

enum Color {
    RED, BLUE, GREEN, YELLOW
}

public class Card {

    private int number;
    private Color color;

    public Card(int number, Color color) {
        this.number = number;
        this.color = color;
    }

    public int getNumber() {
        return number;
    }

    public Color getColor() {
        return color;
    }
}


