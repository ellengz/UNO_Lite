# UNO
A simplified version of card game UNO with one user vs one computer.  

### Environments
* Android 7.0 (API 24)
* Android Studio 3.1.2
* MacOS 10.13.3

### Intro
Check https://en.wikipedia.org/wiki/Uno_(card_game) for complete rules.  
Summary of this implemented version:  
* only number cards [0,9] included
* only 80 cards in total (2x[0,9] for each color)
* for each turn, player has to either pick or put
* computer randomly chooses a playable card (if there's any) in its turn
* the first player who has no card left in hand wins  

Especially:  
* for each game, user always plays first ("user-friendly")
* if no cards left in deck, the player who has fewer cards in hand will win

### Design
 
