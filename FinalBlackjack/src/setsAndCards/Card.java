package setsAndCards;

import enums.Value;
import enums.Suit;

public class Card {
    private Suit suit;
    private Value value;
    
    public Card(Suit suit, Value value) {
        this.value = value;
        this.suit = suit;
    }
    
    public String toString() {
        return this.value.toString() + " of " + this.suit.toString();
    }
    
    public Value getValue() {
        return this.value;
    }
}
