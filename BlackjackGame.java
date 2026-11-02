
package mru.game.controller;
import mru.game.model.Hand;
import mru.game.model.Player;
import mru.game.view.AppMenu;

public class BlackjackGame {
    // module 3, private field to store the deck of cards
    private CardDeck deck;
    // module 3, private field to use the menu for player input
    private AppMenu menu;
    // module 3, constructor creates the deck and sets the menu
    public BlackjackGame(AppMenu menu) {
        deck = new CardDeck();
        this.menu = menu;
    }

    // lab 4 p3, method to start the game for a player
    public void playGame(Player player) {

        boolean playing = true; // module 1, boolean to check if the player is still playing

        // module 1 loops, repeats the game until the player stops
        while (playing) {

            // lab 1 p3, checks if the player has enough money to bet
            if (player.getBalance() < 2) {
                System.out.println("\nYou don't have enough money to play.");
                break;
            }

            // lab 4 p3, calls the method to play one round
            playRound(player);

            // module 1 p6, displays the updated balance and wins
            System.out.println("\nYour balance: $" + player.getBalance());
            System.out.println("Number of wins: " + player.getNumberOfWins());

            // lab 1 p3, checks if the player has enough money to play again
            if (player.getBalance() < 2) {
                System.out.println("\nYou don't have enough money for another round.");
                playing = false;

            } else {

                // lab 4 p3, calls the menu method to ask if they want another round
                String choice = menu.showPlayAgainMenu();

                // lab 1 p3, checks if the player chose to return to the main menu
                if (choice.equals("2")) {
                    playing = false;
                }
            }
        }

        // PLS PUT GAME MANAGER STUFF HERE

    }

    // lab 4 p3, method to play one round of blackjack
    private void playRound(Player player) {

        // module 3, creates a new hand for the player and dealer
        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();

        // module 1 p6, displays the player's name and balance
        System.out.println("\nWelcome to Blackjack, " + player.getName() + "!");
        System.out.println("Your balance is $" + player.getBalance());

        // lab 4 p3, calls promptBet() to get a valid bet amount
        int bet = menu.promptBet(player.getBalance());

        // module 3, calls methods to deal two cards to each hand
        playerHand.addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());

        playerHand.addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());

        // module 1 p6, displays the player's cards and score
        System.out.println("\nYour cards:");
        showHand(playerHand);
        System.out.println("Your score: " + playerHand.getScore());

        // module 1 arraylists, get(0) displays only the dealer's first card
        System.out.println("\nDealer's cards:");
        System.out.println(dealerHand.getCards().get(0));
        System.out.println("Hidden card");

        // lab 1 p3, checks if the player or dealer has blackjack
        if (playerHand.isNatural() || dealerHand.isNatural()) {

            // module 1 p6, reveals the dealer's cards and score
            System.out.println("\nDealer reveals their cards:");
            showHand(dealerHand);
            System.out.println("Dealer's score: " + dealerHand.getScore());

            // lab 1 p3, checks if both have blackjack which means a tie
            if (playerHand.isNatural() && dealerHand.isNatural()) {
                System.out.println("\nBoth have Blackjack! It's a tie.");

            } else if (playerHand.isNatural()) {

                // module 1 p9, double and multiplication to calculate 1.5 times the bet
                double winnings = bet * 1.5;

                // module 3, setters update the player's balance and wins
                player.setBalance(player.getBalance() + winnings);
                player.setNumberOfWins(player.getNumberOfWins() + 1);

                System.out.println("\nBlackjack! You win $" + winnings);

            } else {

                // module 3, subtracts the bet if the dealer has blackjack
                player.setBalance(player.getBalance() - bet);
                System.out.println("\nDealer has Blackjack. You lose $" + bet);
            }

            return; // lab 4 methods, ends this round
        }

        // module 1, boolean to check if the player's turn is still going
        boolean playerTurn = true;

        // module 1 loops, repeats until the player stands, busts or reaches 21
        while (playerTurn) {

            // lab 4 p3, gets the player's choice from the menu
            String choice = menu.showHitStandMenu();

            // lab 1 p3, checks if the player chose hit
            if (choice.equals("1")) {

                // module 3, calls drawCard() and adds the card to the player's hand
                playerHand.addCard(deck.drawCard());

                // module 1 p6, displays the player's new cards and score
                System.out.println("\nYou drew a card!");
                System.out.println("Your cards:");
                showHand(playerHand);
                System.out.println("Your score: " + playerHand.getScore());

                // lab 1 p3, checks if the player's score is over 21
                if (playerHand.isBust()) {
                    System.out.println("\nBust! You lose $" + bet);
                    player.setBalance(player.getBalance() - bet);
                    return; // lab 4 methods, ends the round
                }

                // lab 1 p3, ends the player's turn if they reach 21
                if (playerHand.getScore() == 21) {
                    playerTurn = false;
                }

            } else {

                // lab 1 p3, stand ends the player's turn
                playerTurn = false;
            }
        }

        // module 1 p6, reveals the dealer's cards and score
        System.out.println("\nDealer reveals their cards:");
        showHand(dealerHand);
        System.out.println("Dealer's score: " + dealerHand.getScore());

        // module 1 loops, dealer must keep drawing until their score reaches 17
        while (dealerHand.getScore() < 17) {

            // module 3, calls drawCard() to add another card to the dealer's hand
            dealerHand.addCard(deck.drawCard());

            System.out.println("\nDealer draws another card:");
            showHand(dealerHand);
            System.out.println("Dealer's score: " + dealerHand.getScore());
        }

        // lab 1 p3, checks if the dealer went over 21
        if (dealerHand.isBust()) {

            System.out.println("\nDealer busts! You win $" + bet);

            // module 3, updates balance and wins if the player wins
            player.setBalance(player.getBalance() + bet);
            player.setNumberOfWins(player.getNumberOfWins() + 1);

        } else if (playerHand.getScore() > dealerHand.getScore()) {

            // lab 1 p3, player wins if their score is higher
            System.out.println("\nYou win $" + bet);

            // module 3, updates the player's balance and wins
            player.setBalance(player.getBalance() + bet);
            player.setNumberOfWins(player.getNumberOfWins() + 1);

        } else if (playerHand.getScore() < dealerHand.getScore()) {

            // lab 1 p3, player loses if the dealer has a higher score
            System.out.println("\nDealer wins. You lose $" + bet);

            // module 3, subtracts the bet from the player's balance
            player.setBalance(player.getBalance() - bet);

        } else {

            // lab 1 p3, equal scores mean a tie so the balance stays the same
            System.out.println("\nIt's a tie! Your balance stays the same.");
        }
    }

    // lab 4 p3, method to display all the cards in a hand
    private void showHand(Hand hand) {

        // module 1, enhanced for loop goes through each card
        for (Card card : hand.getCards()) {
            System.out.println(card); // module 1 p6, prints each card
        }
    }
}
