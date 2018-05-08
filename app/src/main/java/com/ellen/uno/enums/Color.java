package com.ellen.uno.enums;

/**
 * enum which specifies the color
 */
public enum Color {
    RED(0xfff6546a), BLUE(0xff6ca2f5), GREEN(0xffa0db8e), YELLOW(0xfffefe51);

    public int getColorIndex() {
        return colorIndex;
    }

    private int colorIndex;

    Color (int colorIndex) {
        this.colorIndex = colorIndex;
    }
}


