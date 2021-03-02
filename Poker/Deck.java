package src.Poker;

import java.util.ArrayList;
import java.util.List;

/**
 * Deck Class
 */
public class Deck {
    private List<Card> deck;

    /**
     * Deck 초기 설정
     */
    public Deck() {
        deck = new ArrayList<>();
        fillDeck("space");
        fillDeck("diamond");
        fillDeck("heart");
        fillDeck("clover");
    }

    /**
     * Helper Method for making all Cards
     * 모든 모양과 숫자를 만들기 위한 method
     * 
     * @param shape 카드 모양
     */
    private void fillDeck(String shape) {
        for (int i = 0; i < 13; i++) {
            if (i == 0) {
                deck.add(new Card(shape, "A"));
            } else if (i == 10) {
                deck.add(new Card(shape, "J"));
            } else if (i == 11) {
                deck.add(new Card(shape, "Q"));
            } else if (i == 12) {
                deck.add(new Card(shape, "K"));
            } else {
                deck.add(new Card(shape, Integer.toString(i + 1)));
            }
        }
    }

    /**
     * getter for deck
     * 
     * @return 모든 카드 list
     */
    public List<Card> getDeck() {
        return deck;
    }
}
