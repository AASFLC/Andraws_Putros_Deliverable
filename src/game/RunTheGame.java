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

        System.out.println("Welcome to Blackjack ! :)");

        //Create the playing deck
        Deck playingDeck = new Deck();
        playingDeck.createDeck();
        playingDeck.shuffleDeck();

        //Create player and dealer hands
        Deck playerHand = new Deck();
        Deck dealerHand = new Deck();

        //Call betting and conditions
        WinningConditions outcome = new WinningConditions();
        Betting bet = new Betting();

        //How much the player starts with
        double playerPool = 500.00;

        //The game
        while (playerPool > 0) {
            //Take the players bet
            System.out.println("You currently have $" + playerPool + ", how much would you like to bet?");
            double playerBet = input.nextDouble();

            if (playerBet > playerPool) {
                System.out.println("You cannot bet more than you have ! :(");
                break;
            }

            boolean endRound = false;

            //Player gets dealt two cards
            playerHand.draw(playingDeck);
            playerHand.draw(playingDeck);

            //Dealer gets dealt two cards
            dealerHand.draw(playingDeck);
            dealerHand.draw(playingDeck);

            while (true) { //While the game is ongoing
                System.out.println("\nYour hand includes:");
                System.out.print(playerHand.toString());
                System.out.println("\nYour hand is valued at: " + playerHand.cardsValue());

                //If they get a Blackjack from the beginning
                if (outcome.BlackJack(playerHand) == true) {
                    playerPool = bet.win(playerPool, playerBet);
                    endRound = true;
                    break;
                }
                
                //Display the dealer hand
                System.out.println("Dealer hand: " + dealerHand.getCard(0).toString() + " and [HIDDEN]");

                //Ask player what they want to do
                System.out.println("(1)hit (2)stand");
                int selection = input.nextInt();

                //If player hits
                if ((selection == 1) && outcome.BlackJack(playerHand) != true) {
                    playerHand.draw(playingDeck);
                    System.out.println("You drew a: " + playerHand.getCard(playerHand.deckSize() - 1));

                    //If player busts
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

            //Reveal the dealer hand
            System.out.println("Dealer Cards: " + dealerHand.toString());

            //See if the dealer has more points than the player
            if (outcome.dealerWon(dealerHand, playerHand, endRound)) {
                playerPool = bet.lose(playerPool, playerBet);
                endRound = true;
            }

            //Dealer draws at 16, stand at 17
            while ((dealerHand.cardsValue() < 17) && endRound == false) {
                dealerHand.draw(playingDeck);
                System.out.println("Dealer drew: " + dealerHand.getCard(dealerHand.deckSize() - 1).toString());
            }

            //Display total value for dealer
            System.out.println("Dealers hand is valued at: " + dealerHand.cardsValue());

            //Determine if dealer busted
            if (outcome.dealerBust(dealerHand, endRound) == true) {
                playerPool = bet.win(playerPool, playerBet);
                endRound = true;
            }

            //Determine if push
            if (outcome.push(playerHand, dealerHand, endRound) == true) {
                endRound = true;
            }

            //Player won
            if (outcome.playerWon(playerHand, dealerHand, endRound) == true) {
                playerPool = bet.win(playerPool, playerBet);
                endRound = true;
            } 
            //Dealer won
            else if (outcome.dealerWon(dealerHand, playerHand, endRound) == true) {
                playerPool = bet.lose(playerPool, playerBet);
                endRound = true;
            }

            playerHand.moveToDeck(playingDeck);
            dealerHand.moveToDeck(playingDeck);
            System.out.println("End of current hand");
        }

        System.out.println("Game over, you are out of money. Please play again! :)");
    }
}
