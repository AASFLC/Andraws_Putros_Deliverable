package setsAndCards;

import enums.Value;
import enums.Suit;
import java.util.ArrayList;
import java.util.Random;

/**
 * Andraws Putros
 * Student ID: 991407087
 */
public class Deck {
    private ArrayList<Card> cards;
    
    public Deck() {
        this.cards = new ArrayList<>();
    }
    
    public void createDeck() {
        for (Suit cardSuit : Suit.values()) {
            for (Value cardValue : Value.values()) {
                this.cards.add(new Card(cardSuit, cardValue));
            }
        }
    }
    
    public void shuffleDeck() {
        ArrayList<Card> tempDeck = new ArrayList<>();
        Random random = new Random();
        int randomCardIndex = 0;
        int originalSize = this.cards.size();
        
        for(int i = 0 ; i < originalSize ; i++) {
            randomCardIndex = random.nextInt((this.cards.size()-1 - 0) + 1) + 0;
            tempDeck.add(this.cards.get(randomCardIndex)); //add
            this.cards.remove(randomCardIndex); //remove from original
        }
        
        this.cards = tempDeck;
    }
    
    public void removeCard(int i) {
        this.cards.remove(i);
    }
    
    public Card getCard(int i) {
        return this.cards.get(i);
    }
    
    public void addCard(Card addCard) {
        this.cards.add(addCard);
    }
    
    public void draw(Deck initialDeck) {
        this.cards.add(initialDeck.getCard(0));
        initialDeck.removeCard(0);
    }
    
    public int cardsValue() {
        int totalValue = 0;
        int aces = 0;
        
        for (Card aCard : this.cards) {
            switch(aCard.getValue()) {
                case TWO : { totalValue += 2; break; }
                case THREE : { totalValue += 3; break; }
                case FOUR : { totalValue += 4; break; }
                case FIVE : { totalValue += 5; break; }
                case SIX : { totalValue += 6; break; }
                case SEVEN : { totalValue += 7; break; }
                case EIGHT : { totalValue += 8; break; }
                case NINE : { totalValue += 9; break; }
                case TEN : { totalValue += 10; break; }
                case JACK : { totalValue += 10; break; }
                case QUEEN : { totalValue += 10; break; }
                case KING : { totalValue += 10; break; }
                case ACE : { aces += 1; break; }
            }
        }
        
        for(int i = 0 ; i < aces ; i++) {
            if (totalValue > 10) {
                totalValue += 1;
            } else {
                totalValue += 11;
            }
        }
        
        return totalValue;
    }
    
    public int deckSize() {
        return this.cards.size();
    }
    
    public void moveToDeck(Deck moveTo) {
        int thisDeckSize = this.cards.size();
        
        //put into moveto deck
        for(int i = 0 ; i < thisDeckSize ; i++) {
            moveTo.addCard(this.getCard(i));
        }
        
        for (int i = 0; i < thisDeckSize; i++) {
            this.removeCard(0);
        }
    }
    
    @Override
    public String toString() {
        String cardListOutput = "";
        int i = 0;
        
        for(Card aCard : this.cards) {
            cardListOutput += "\n" + aCard.toString();
        }
        
        return cardListOutput;
    }
}
