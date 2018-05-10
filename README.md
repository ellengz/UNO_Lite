# UNO
A simplified version of card game UNO with one user vs one computer.  

## 1. Environments
* Android 7.0 (API 24)
* Android Studio 3.1.2
* MacOS 10.13.3

## 2. Rules
Check https://en.wikipedia.org/wiki/Uno_(card_game) for complete rules.  
Summary of this implemented version:  
* only number cards [0,9] included
* only 80 cards in total (2x[0,9] for each color)
* for each turn, player has to either pick or put
* computer randomly chooses a playable card (if there's any) in its turn
* the first player who has no card left in hand wins  

Especially:  
* for each game, user always plays first
* if no cards left in deck, the cards that have been played will be shuffled and added back to the deck

## 3. Design
### 3.1 Classes
![GitHub](https://github.com/ellengz/UNO_Lite/blob/master/UNOClassDiagram.png "Class Diagram")
#### MainActivity
* initialise game view in onCreate()
* create instances of Computer and Player
* serve cards to players when requested
* handle actions relating to pick and card buttons  
#### Card
* abstraction of card
#### Player
* abstraction of player
* field inHand to represent cards in hand
* implement action of picking a card
#### User
* extends Player
* checkChosen(chosenCard, targetCard) to check if chosen card matches target card (card on the top of pile)
#### Computer
* extends Player
* tryPut(targetCard) to put a playable card or return null if nothing playable found  
#### Color
* enum contains colors needed in this game and associated color index
#### Status
* enum contains game status code and associated messages

### 3.2 Tests
#### MainActivity
* verify initial set up
* check if pick button works correctly
#### Models
* check method pick() of class Player
* check method checkChosen() of class User
* check method tryPut() of class Computer
