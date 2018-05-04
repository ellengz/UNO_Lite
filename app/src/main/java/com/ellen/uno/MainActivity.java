package com.ellen.uno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.ellen.uno.Enum.Color;
import com.ellen.uno.Enum.Status;
import com.ellen.uno.model.*;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ArrayList<Card> deck;
    Card topOnPile;
    Computer computer;
    User user;
    Status status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.deck = new ArrayList<>();
        this.topOnPile = new Card();
        this.computer = new Computer();
        this.user = new User();
        this.status = Status.PLAYING;

        prepareDeck();
        serveSevenCards(user);
        serveSevenCards(computer);
        topOnPile = deck.get(deck.size() - 1);
        deck.remove(topOnPile);
        setScreen();

        while (status == Status.PLAYING) {

            play();

        }

    }

    private void setScreen() {
        TextView deckCount = findViewById(R.id.deckCount);
        deckCount.setText(String.valueOf("Cards left in deck: " + deck.size()));
        TextView topCard = findViewById(R.id.topCard);
        topCard.setText(topOnPile.toString());

        LinearLayout ll = findViewById(R.id.cards);
        for (Card card: user.inHand) {
            Button button = new Button(this);
            button.setText(card.toString());
            button.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            ll.addView(button);
        }

    }

    private void play() {

        // computer's turn
        Card cardByComputer = computer.play(topOnPile);
        if (cardByComputer != null) {
            topOnPile = cardByComputer;
            if (computer.inHand.size() == 0) {
                status = Status.COMPUTER_WIN;
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
//                status = Status.USER_WIN;
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
