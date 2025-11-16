package dev.miniks;

import java.util.List;

import dev.miniks.Card;

public class Main {

    public static void main(String[] args) {

        List<Card> deck = Card.getStandardDeck();
        Card.printDeck(deck);

        Card.printDeck(deck);
    }
}
