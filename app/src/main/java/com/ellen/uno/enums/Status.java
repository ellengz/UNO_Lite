package com.ellen.uno.enums;

public enum Status {

    TURN_COMPUTER(100, "Waiting for computer's move"),
    TURN_USER(101, "Waiting for user's input"),
    UNPLAYABLE_CARD_CHOSEN(102, "Please choose a card matching color/number of the card on the top of pile!"),
    COMPUTER_WIN(200, "Computer wins! Bad luck!"),
    USER_WIN(201, "You win! Congratulations!"),
    NO_CARD(202, "No cards left in deck! According to the number of cards left in hand, the winner is "),
    TIE(203, "Tie!")

    ;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    private Integer code;
    private String msg;

    @Override
    public String toString() {
        return "SYSTEM INFO:" + '\n' + msg;
    }

    Status(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
