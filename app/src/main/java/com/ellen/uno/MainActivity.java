package com.ellen.uno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ellen.uno.model.*;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ArrayList<Card> deck;
    Card topOnPile;
    Computer computer;
    User user;
    String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.deck = new ArrayList<>();
        this.topOnPile = new Card();
        this.computer = new Computer();
        this.user = new User();
        this.status = "Playing";

        prepareDeck();
        serveSevenCards(user);
        serveSevenCards(computer);
        topOnPile = deck.get(deck.size() - 1);
        deck.remove(topOnPile);

        while (status.equals("Playing")) {

            setScreen();
            play();

        }

    }

    private void setScreen() {

    }

    private void play() {

        // computer's turn
        Card cardByComputer = computer.play(topOnPile);
        if (cardByComputer != null) {
            topOnPile = cardByComputer;
            if (computer.inHand.size() == 0) {
                status = "Computer Wins";
                return;
            }

        } else {
            serveCard(computer);
        }

        // user's turn
        // user's interaction

//        Card cardByUser = user.play(topOnPile, null);
//        if (cardByUser != null) {
//            topOnPile = cardByUser;
//            if (user.inHand.size() == 0) {
//                status = "You win";
//                return;
//            }
//
//        } else {
//            serveCard(user);
//        }

    }



    private void prepareDeck() {
        for (int i = 0; i < 10; i ++) {
            for (int j = 0; j < 2; j ++) {
                deck.add(new Card(i, Color.BLUE));
                deck.add(new Card(i, Color.YELLOW));
                deck.add(new Card(i, Color.RED));
                deck.add(new Card(i, Color.GREEN));
            }
        }
        Collections.shuffle(deck);
    }

    private void serveCard(Player player) {
        player.pick(deck.get(deck.size() - 1));
        deck.remove(deck.size() - 1);
    }

    private void serveSevenCards(Player player) {
        for (int i = 0; i < 7; i ++) {
            serveCard(player);
        }
    }
}
