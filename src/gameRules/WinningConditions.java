package gameRules;

import setsAndCards.Deck;

/**
 * Andraws Putros
 * Student ID: 991407087
 */
public class WinningConditions {
    public boolean BlackJack (Deck playerHand) {
        boolean blackJack = false;
        
        if (playerHand.cardsValue() == 21) {
            System.out.println("Blacjack! Wow!");
            blackJack = true;
        }
        
        return blackJack;
    }
    
    public boolean playerBust (Deck hand, boolean endRound) {
        boolean bust = false;
        
        if ((hand.cardsValue() > 21) && endRound == false) {
            System.out.println("Bust! Currently valued at: " + hand.cardsValue());
            bust = true;
        }
        
        return bust;
    }
    
    public boolean dealerBust(Deck hand, boolean endRound) {
        boolean bust = false;

        if ((hand.cardsValue() > 21) && endRound == false) {
            System.out.println("Dealer busted, you win!");
            bust = true;
        }

        return bust;
    }
    
    public boolean playerWon(Deck firstHand, Deck secondHand, boolean endRound) {
        boolean won = false;
        
        if ((firstHand.cardsValue() > secondHand.cardsValue()) && endRound == false) {
            System.out.println("You win!");
            won = true;
        }
        
        return won;
    }
    
    public boolean dealerWon(Deck firstHand, Deck secondHand, boolean endRound) {
        boolean won = false;

        if ((firstHand.cardsValue() > secondHand.cardsValue()) && endRound == false) {
            System.out.println("Dealer wins!");
            won = true;
        }

        return won;
    }
    
    public boolean push(Deck firstHand, Deck secondHand, boolean endRound) {
        boolean won = false;

        if ((firstHand.cardsValue() == secondHand.cardsValue()) && endRound == false) {
            System.out.println("Push");
            won = true;
        }

        return won;
    }
}
