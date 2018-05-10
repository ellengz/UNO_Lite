package com.ellen.uno.models;

import com.ellen.uno.enums.Color;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void pickTest() {
        Player player = new Player();
        Card testCard = new Card(1, Color.BLUE);
        player.pick(testCard);
        assertTrue(player.getInHand().contains(testCard));
    }

    @Test
    public void userCheckChosenTest() {
        User user = new User();
        assertTrue(
                user.checkChosen(new Card(1,Color.YELLOW), new Card(1, Color.BLUE)) &&
                user.checkChosen(new Card(1,Color.YELLOW), new Card(5, Color.YELLOW)));
    }

    @Test
    public void computerTryPutTest() {
        Computer computer = new Computer();
        assertNull(computer.tryPut(new Card(1, Color.GREEN)));
    }
}