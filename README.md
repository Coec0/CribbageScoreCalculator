# Cribbage Score Calculator
A simple program written in Java to calculate the score of a cribbage hand

This java project can be used to count the score from a hand or crib in cribbage. It's easy to use and is as simple as copy and pasting to install. See section **Installation**.

This code was originally created for the score counting in the app [Cribbage Board](https://play.google.com/store/apps/details?id=com.coeccode.cribbageboard), as I couldn't find any library online. I therefore decided to share this for other people who might want to do a java/android project for cribbage.

## Installation
Go to [releases](https://github.com/Coec0/CribbageScoreCalculator/releases) and click on counter.zip to download the package. Install by either copying the *counter* package to your project or by creating the *counter* package locally and add the class files.

You can also clone the entire project to receive the code with an example **Main** class that shows how the library can be used.

## Usage

The program consists of four classes:

* **Suit**, which is an enum that contains the card suits and a method to get a **Suit** from a character
* **Rank**, which is an enum that contains the card rank and a method to get a **Rank** from a string + some other functions
* **Card**, which is an object that holds a **Suit** and a **Rank**
* **Cribbage**, which is a class that has the static methods for calculating the score using a list of **Card** and a cut **Card**

To create a card, e.g the ace of clubs, you can type: 
```java
Card aceOfClubs = new Card(Rank.ACE,Suit.CLUBS);
```

You can also use the method *rankFromString(String rank)* and *suitFromChar(char c)* to get the rank and suit:
```java
Suit suit = Suit.suitFromChar('C');
Rank rank = Rank.rankFromString("A");
Card card = new Card(rank,suit);
```

The reason for *rankFromString(String rank)* and not *rankFromChar(Char rank)* is to be able to support **"10"** as input.

To calculate the score from a hand + cut, a list with the four cards in the hand and a single card for the cut should be created. The list and cut can then be put into any of the methods in the Cribbage class. Here's an example:

```java
ArrayList<Card> hand = new ArrayList<>(4);
hand.add(new Card(Rank.FIVE, Suit.HEARTS));
hand.add(new Card(Rank.SIX, Suit.HEARTS));
hand.add(new Card(Rank.SEVEN, Suit.HEARTS));
hand.add(new Card(Rank.EIGHT, Suit.HEARTS));
Card cut = new Card(Rank.EIGHT, Suit.SPADES);
boolean crib = false; //The cards counted are in the hand

int totalPoints = Cribbage.calculate(hand, cut, crib); //18 points
int fifteens = Cribbage.fifteens(hand, cut); //4 points
int runs = Cribbage.runs(hand, cut); //8 points
int pairs = Cribbage.pairs(hand, cut); // 2 points
int flush = Cribbage.flush(hand, cut, crib); //4 points
int nob = Cribbage.nob(hand, cut); //0 points
```

## Error handling
The program currently does not support error handling. This means that you could have duplicates of cards in your hand or have a hand of any length. The program will still try to calculate the score but it can lead to unexpected results.

**To make sure that the program works properly, make sure to always input a hand of four where every card is unique**
