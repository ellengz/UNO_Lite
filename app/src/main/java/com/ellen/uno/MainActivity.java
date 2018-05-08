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
    TextView systemInfo;
    Button pickButton;
    Button restartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll = findViewById(R.id.cards);
        deckCountText = findViewById(R.id.deckCount);
        topCardText = findViewById(R.id.topCard);
        computerCountText = findViewById(R.id.computerCount);
        systemInfo = findViewById(R.id.systemInfo);
        pickButton = findViewById(R.id.pick);
        restartButton = findViewById(R.id.restart);

        initGame();
        updateGameStatus(Status.TURN_USER);
    }

    /**
     * prepare deck,
     * serve initial cards,
     * prepare a top card, and
     * prepare game panel (relevant texts) and user panel (buttons)
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

        // set views for game panel, pick button and user cards panel
        setGamePanel(true,true, true);
        pickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickButtonClicked();
            }
        });
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });
        setUserCardsPanel();
    }

    /**
     * process computer's turn
     */
    private void compPlay() {
        if (gameStatus == Status.TURN_COMPUTER) {
            Card card = computer.tryPut(cardOnTop);
            if (card != null) {
                // computer can find at least one playable card
                cardOnTop = card;
            } else {
                // computer can't find one playable card
                serveNextCard(computer);
            }
            setGamePanel(true,true,true);

            if (computer.getInHand().size() == 0) {
                updateGameStatus(Status.COMPUTER_WIN);
            } else {
                updateGameStatus(Status.TURN_USER);
            }

        }
    }

    /**
     * set game relevant texts
     * @param updateDeck - true if update needed
     * @param updateTop - true if update needed
     * @param updateComputer - true if update needed
     */
    private void setGamePanel(boolean updateDeck, boolean updateTop, boolean updateComputer) {
        if(updateDeck)
            deckCountText.setText(String.valueOf(cardsInDeck.size()));
        if(updateTop)
            topCardText.setText(cardOnTop.toString());
            topCardText.setBackgroundColor(cardOnTop.getColor().getColorIndex());
        if(updateComputer)
            computerCountText.setText(String.valueOf(computer.getInHand().size()));
        systemInfo.setText("");
    }

    /**
     * set card buttons
     */
    private void setUserCardsPanel() {
        ll.removeAllViews();
        for (int i = 0; i < user.getInHand().size(); i ++) {
            newCardButton(i);
        }
    }

    /**
     * new a card button
     * @param cardButtonId
     */
    private void newCardButton(final int cardButtonId) {
        final Button cardButton = new Button(this);
        Card selected = user.getInHand().get(cardButtonId);
        cardButton.setText(selected.toString());
        cardButton.setId(cardButtonId); // consistent with position in inHand
        cardButton.setBackgroundColor(selected.getColor().getColorIndex());
        cardButton.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));
        ll.addView(cardButton); // add button to user panel

        cardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardButtonClicked(user.getInHand().get(cardButtonId));
            }
        });
    }

    /**
     * whether update user panel or warn user after user clicked a card button
     * @param chosenCard - card chosen
     */
    private void cardButtonClicked(Card chosenCard) {

        if (gameStatus == Status.TURN_USER
                || gameStatus == Status.UNPLAYABLE_CARD_CHOSEN) {
            if (user.checkChosen(cardOnTop, chosenCard)) {
                // chosen card is playable
                cardOnTop = chosenCard; // replace card on top with chosen card
                setUserCardsPanel(); // update cards panel
                setGamePanel(false,true,false);
                if (user.getInHand().size() == 0) {
                    updateGameStatus(Status.USER_WIN);
                } else {
                    updateGameStatus(Status.TURN_COMPUTER);
                }

            } else {
                updateGameStatus(Status.UNPLAYABLE_CARD_CHOSEN);
            }
        }
    }

    /**
     * update user and game panel when user chose to pick
     */
    private void pickButtonClicked() {
        if (gameStatus == Status.TURN_USER
                || gameStatus == Status.UNPLAYABLE_CARD_CHOSEN) {
            serveNextCard(user);
            setUserCardsPanel();
            setGamePanel(true, false, false);
            updateGameStatus(Status.TURN_COMPUTER);
        }
    }

    /**
     * serve the next card in deck to the given player
     * @param player
     */
    private void serveNextCard(Player player) {
        if (cardsInDeck.size() > 0) {
            Card nextCard = cardsInDeck.get(cardsInDeck.size() - 1);
            cardsInDeck.remove(nextCard);
            player.pick(nextCard);
        } else {
            updateGameStatus(Status.NO_CARD);
        }
    }

    /**
     * update game status
     */
    private void updateGameStatus(Status newStatus) {
        gameStatus = newStatus;
        systemInfo.setText(gameStatus.toString());

        switch (newStatus) {
            case TURN_COMPUTER:
                compPlay();
                break;
            case NO_CARD:
                if (user.getInHand().size() == computer.getInHand().size()) {
                    updateGameStatus(Status.TIE);
                } else {
                    updateGameStatus(user.getInHand().size() < computer.getInHand().size()?
                            Status.USER_WIN : Status.COMPUTER_WIN);
                }
                break;
            case USER_WIN: case COMPUTER_WIN: case TIE:
                restartButton.setVisibility(View.VISIBLE);
                break;
        }
    }
}
