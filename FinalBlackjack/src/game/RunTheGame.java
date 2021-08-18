package game;

import gameRules.Betting;
import gameRules.WinningConditions;
import java.util.Scanner;
import setsAndCards.Deck;

/**
 * Andraws Putros
 * Student ID: 991407087
 */
public class RunTheGame {
    public static void runTheGame() {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to Blackjack!");

        //create playing decks
        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffle();

        //create the hands
        Deck playerHand = new Deck();
        Deck dealerHand = new Deck();

        WinningConditions outcome = new WinningConditions();
        Betting bet = new Betting();

        double playerPool = 100.00;

        //game loop
        while (playerPool > 0) {
            //take the players bet
            System.out.println("You have $" + playerPool + ", how much would you like to bet?");
            double playerBet = input.nextDouble();

            if (playerBet > playerPool) {
                System.out.println("You cannot bet more than you have.");
                break;
            }

            boolean endRound = false;

            //start dealing
            //player gets two cards
            playerHand.draw(playingDeck);
            playerHand.draw(playingDeck);

            //dealer gets two cards
            dealerHand.draw(playingDeck);
            dealerHand.draw(playingDeck);

            while (true) {
                System.out.println("Your hand:");
                System.out.print(playerHand.toString());
                System.out.println("Your hand is valued at: " + playerHand.cardsValue());

                if (outcome.BlackJack(playerHand) == true) {
                    System.out.println("Blackjack!");
                    playerPool += playerBet;
                    endRound = true;
                    break;
                }
                //display dealer hand
                System.out.println("Dealer hand: " + dealerHand.getCard(0).toString() + " and [HIDDEN]");

                //what does player want to do
                System.out.println("(1)hit (2)stand");
                int selection = input.nextInt();

                //they hit
                if ((selection == 1) && outcome.BlackJack(playerHand) != true) {
                    playerHand.draw(playingDeck);
                    System.out.println("You drew a: " + playerHand.getCard(playerHand.deckSize() - 1));

                    //player busts
                    if (outcome.playerBust(playerHand, endRound) == true) {
                        playerPool = bet.lose(playerPool, playerBet);
                        endRound = true;
                        break;
                    }
                }

                if (selection == 2) {
                    break;
                }
            }

            //reveal dealer cards
            System.out.println("Dealer Cards: " + dealerHand.toString());

            //see if dealer has more points than player
            if (outcome.dealerWon(dealerHand, playerHand, endRound)) {
                playerPool = bet.lose(playerPool, playerBet);
                endRound = true;
            }

            //dealer draws at 16, stand at 17
            while ((dealerHand.cardsValue() < 17) && endRound == false) {
                dealerHand.draw(playingDeck);
                System.out.println("Dealer drew: " + dealerHand.getCard(dealerHand.deckSize() - 1).toString());
            }

            //display total value for dealer
            System.out.println("Dealers hand is valued at: " + dealerHand.cardsValue());

            //determine if dealer busted
            if (outcome.dealerBust(dealerHand, endRound) == true) {
                playerPool = bet.win(playerPool, playerBet);
                endRound = true;
            }

            //determine if push
            if (outcome.push(playerHand, dealerHand, endRound) == true) {
                endRound = true;
            }

            //player won
            if (outcome.playerWon(playerHand, dealerHand, endRound) == true) {
                playerPool = bet.win(playerPool, playerBet);
                endRound = true;
            } //dealer won
            else if (outcome.dealerWon(dealerHand, playerHand, endRound) == true) {
                playerPool = bet.lose(playerPool, playerBet);
                endRound = true;
            }

            playerHand.moveAllToDeck(playingDeck);
            dealerHand.moveAllToDeck(playingDeck);
            System.out.println("End of hand");
        }

        System.out.println("Game over! You are out of money :(");
    }
}
