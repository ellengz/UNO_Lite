package com.ellen.uno.Enum;

public enum Status {

    PLAYING(0, ""),
    COMPUTER_WIN(100, "Computer wins! Bad luck!"),
    USER_WIN(101, "You win! Congratulations!"),
    NO_CARD(200, "Tie! No cards left in deck!")

    ;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    private Integer code;
    private String msg;

    Status(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
