package com.ellen.uno.enums;

public enum Status {

    WAIT_FOR_COMPUTER(100, "Wait for computer's move"),
    WAIT_FOR_USER(101, "Wait for user's input"),
    UNPLAYABLE_CARD_CHOSEN(102, "Please choose a playable card matching "),
    COMPUTER_WIN(200, "Computer wins! Bad luck!"),
    USER_WIN(201, "You win! Congratulations!"),
    NO_CARD(202, "Tie! No cards left in deck!")

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
        return "Status{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

    Status(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
