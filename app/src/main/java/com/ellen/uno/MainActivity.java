package com.ellen.uno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.ellen.uno.enums.Color;
import com.ellen.uno.enums.Status;
import com.ellen.uno.models.*;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ArrayList<Card> cardsInDeck;
    Card cardOnTop;
    Status gameStatus;
    User user;
    Computer computer;

    LinearLayout ll;
    TextView deckCountText;
    TextView topCardText;
    TextView computerCountText;
    Button pickButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll = findViewById(R.id.cards);
        deckCountText = findViewById(R.id.deckCount);
        topCardText = findViewById(R.id.topCard);
        computerCountText = findViewById(R.id.computerCount);
        pickButton = findViewById(R.id.pick);
        gameStatus = Status.WAIT_FOR_COMPUTER;
        Log.d("GAME", gameStatus.toString());

        initGame();
        setGamePanel();
        setUserPanel();

        play();

    }

    /**
     * prepare deck and serve initial cards
     */
    private void initGame() {

        cardsInDeck = new ArrayList<>();
        cardOnTop = new Card();
        user = new User();
        computer = new Computer();

        // make cards
        for (int i = 0; i < 10; i ++) {
            for (int j = 0; j < 2; j ++) {
                cardsInDeck.add(new Card(i, Color.BLUE));
                cardsInDeck.add(new Card(i, Color.YELLOW));
                cardsInDeck.add(new Card(i, Color.RED));
                cardsInDeck.add(new Card(i, Color.GREEN));
            }
        }
        Collections.shuffle(cardsInDeck);
        // serve each player initial seven cards
        for (int i = 0; i < 7; i ++) {
            serveNextCard(computer);
            serveNextCard(user);
        }
        // make the top card
        cardOnTop = cardsInDeck.get(cardsInDeck.size() - 1);
        cardsInDeck.remove(cardOnTop);
        setGamePanel();

    }

    /**
     * setup texts
     */
    private void setGamePanel() {
        deckCountText.setText(String.valueOf(cardsInDeck.size()));
        topCardText.setText(cardOnTop.toString());
        computerCountText.setText(String.valueOf(computer.inHand.size()));
    }

    /**
     * setup card buttons and pick button
     */
    private void setUserPanel() {
        ll.removeAllViews();
        for (int i = 0; i < user.inHand.size(); i ++) {
            newCardButton(i);
        }
        pickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickButtonClicked();
            }
        });
    }

    /**
     * update a specific text view
     */
    private void updateGamePanel(TextView whichone, String value) {
        whichone.setText(value);
    }

    /**
     * handle the game process
     */
    private void play() {
        Card card = computer.tryPut(cardOnTop);
        if (card != null) {// computer chose to put
            cardOnTop = card;
            updateGamePanel(topCardText, card.toString());
        } else {
            serveNextCard(computer);
        }
        // whether pick or put, update number of cards left in computer's hand
        updateGamePanel(computerCountText, String.valueOf(computer.inHand.size()));
        if (computer.inHand.size() == 0) {
            gameStatus = Status.COMPUTER_WIN;
            Log.d("GAME", gameStatus.toString());
        }
        gameStatus = Status.WAIT_FOR_USER;
//        Log.d("GAME", gameStatus.toString());
    }

    /**
     * whether update user panel or warn user after user clicked a card button
     * @param id
     */
    private void cardButtonClicked(int id, Card card) {
        if (user.checkChosen(cardOnTop, user.inHand.get(id))) {
            // chosen card is playable
            updateGamePanel(topCardText, card.toString());
            setUserPanel();
            gameStatus = Status.WAIT_FOR_COMPUTER;
//            Log.d("GAME", gameStatus.toString());
            play();

        } else {
            gameStatus = Status.UNPLAYABLE_CARD_CHOSEN;
            Log.d("GAME", gameStatus.toString() + cardOnTop.toString());
        }
        if (user.inHand.size() == 0) {
            gameStatus = Status.USER_WIN;
            Log.d("GAME", gameStatus.toString());
        }
    }

    /**
     * update user and game panel when user chose to pick
     */
    private void pickButtonClicked() {
        serveNextCard(user);
        setUserPanel();

        updateGamePanel(deckCountText, String.valueOf(cardsInDeck.size()));
        gameStatus = Status.WAIT_FOR_COMPUTER;
        play();
//        Log.d("GAME", gameStatus.toString());
    }

    /**
     * serve the next card in deck to the given player
     * @param player
     */
    private void serveNextCard(Player player) {
        Card nextCard = cardsInDeck.get(cardsInDeck.size() - 1);
        cardsInDeck.remove(nextCard);
        player.pick(nextCard);
    }

    /**
     * new a card button
     * @param cardButtonId
     */
    private void newCardButton(final int cardButtonId) {
        final Button cardButton = new Button(this);
        cardButton.setText(user.inHand.get(cardButtonId).toString());
        cardButton.setId(cardButtonId); // consistent with position in inHand
        cardButton.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));
        ll.addView(cardButton); // add button to user panel

        cardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardButtonClicked(cardButtonId, user.inHand.get(cardButtonId));
            }
        });
    }

}
