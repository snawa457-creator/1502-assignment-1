package mru.game.controller;
import mru.game.model.Hand;
import mru.game.model.Player;
import mru.game.view.AppMenu;

 /**
 * This class runs the Blackjack game
 * handles the cards, turns, bets and game results
 */
public class BlackjackGame { // blackjack game is public bs gamemanager needs to create it as an object
    // module 3, private field to store the deck of cards, private bc other classes dont need to use these
    private CardDeck deck;
    // module 3, private field to use the menu for player input
    private AppMenu menu;
    // module 3, constructor creates the deck and sets the menu

    /**
     * Creates a new Blackjack game with a deck and menu
     * @param menu the menu used for player input
     */
    public BlackjackGame(AppMenu menu) { //public, module 3 bc gamemanager needs to create the game
        deck = new CardDeck(); //creates new carddeck with 52 shuffled cards
        this.menu = menu; //module 3 also menu was passed earlier
    }

    //lab 4 p3, method to start the game for a player

     /**
     * Starts the Blackjack game for a player
     * Lets them play multiple rounds until they stop or run out of money
     * @param player the player currently playing
     */
    public void playGame(Player player) { //used void bc this method doesnt return a value, playGamw is the methods name, player is a parameter

        boolean playing = true; //module 1, boolean to check if the player is still playing

        // module 1 loops, repeats the game until the player stops
        while (playing) {
         //this loop continues as long as playing stays true
            //lab 1 p3, checks if the player has enough money to bet
            if (player.getBalance() < 2) {
             //module 3 getters, getBalance gets the players current balance
                // module 1p6, prints a message if the player is too poor
                System.out.println("\nYou don't have enough money to play.");
                break; //exits loop immediately
            }

            // lab 4 p3, calls the method to play one round
            playRound(player); // passes current player into playRoudn

            //module 1 p6, displays the updated balance and wins
         // also module 3 getters section, gets the balance and wins after the round ends
            System.out.println("\nYour balance: $" + player.getBalance());
            System.out.println("Number of wins: " + player.getNumberOfWins());

            // lab 1 p3, checks if the player has enough money to play again
            if (player.getBalance() < 2) {
                System.out.println("\nYou don't have enough money for another round.");
                playing = false;

            } else {

                //lab 4 p3, calls the menu method to ask if they want another round
                String choice = menu.showPlayAgainMenu(); //the answer is stored as a string

                // lab 1 p3, checks if the player chose to return to the main menu
                if (choice.equals("2")) {
                    playing = false;
                }
            }
        }

    }

    // lab 4 p3, method to play one round of blackjack

    /**
     * Plays one round of Blackjack
     * Deals the cards, checks the scores and updates the player's balance
     * @param player the player playing the round
     */
    private void playRound(Player player) { //private bc this class just needs to run individual rounds

        // module 3, creates a new hand for the player and dealer
        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();

        // module 1 p6, displays the player's name and balance also uses getters from module 3 etc
        System.out.println("\nWelcome to Blackjack, " + player.getName() + "!");
        System.out.println("Your balance is $" + player.getBalance());

        // lab 4 p3, calls promptBet() to get a valid bet amount
        int bet = menu.promptBet(player.getBalance());

        // module 3, calls methods to deal two cards to each hand
        playerHand.addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());
// each drawcard removes one card from the deck and addcard puts it in the hand and the cards are dealt one at a time so both get 2 cards
        playerHand.addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());

        // module 1 p6, displays the player's cards and score
        System.out.println("\nYour cards:");
        showHand(playerHand);
        System.out.println("Your score: " + playerHand.getScore()); //lab4 alos showhand prints the cards and getscore calculates the total

        // module 1 arraylists, get(0) displays only the dealer's first card
        System.out.println("\nDealer's cards:");
        System.out.println(dealerHand.getCards().get(0)); //getcards returns the list and get0 gets the first cards
        System.out.println("Hidden card");

        // lab 1 p3, checks if the player or dealer has blackjack which is a tie
        if (playerHand.isNatural() || dealerHand.isNatural()) {

            // module 1 p6, reveals the dealer's cards and score
            System.out.println("\nDealer reveals their cards:");
            showHand(dealerHand);
            System.out.println("Dealer's score: " + dealerHand.getScore());

            // lab 1 p3, checks if both have blackjack which means a tie
            if (playerHand.isNatural() && dealerHand.isNatural()) { //both have to be true 
                System.out.println("\nBoth have Blackjack! It's a tie.");

            } else if (playerHand.isNatural()) { // if only player has blackjack they get higher payout

                // module 1 p9, double and multiplication to calculate 1.5 times the bet
                double winnings = bet * 1.5;

                // module 3, setters update the player's balance and wins
                player.setBalance(player.getBalance() + winnings);
                player.setNumberOfWins(player.getNumberOfWins() + 1);

                System.out.println("\nBlackjack! You win $" + winnings);

            } else {

                // module 3, subtracts the bet if the dealer has blackjack, also if only the dealer has blackjack the player lsoes the bet
                player.setBalance(player.getBalance() - bet);
                System.out.println("\nDealer has Blackjack. You lose $" + bet);
            }

            return; // lab 4 methods, ends this round
        }

        // module 1, boolean to check if the player's turn is still going
        boolean playerTurn = true;

        //module 1 loops, repeats until the player stands, busts or reaches 21
        while (playerTurn) {

            //lab 4 p3, gets the player's choice from the menu
            String choice = menu.showHitStandMenu();

            // lab 1 p3, checks if the player chose hit
            if (choice.equals("1")) {

                //module 3, calls drawCard() and adds the card to the player's hand
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

            //module 3, calls drawCard() to add another card to the dealer's hand
            dealerHand.addCard(deck.drawCard());

            System.out.println("\nDealer draws another card:");
            showHand(dealerHand);
            System.out.println("Dealer's score: " + dealerHand.getScore());
        }

        //lab 1 p3, checks if the dealer went over 21
        if (dealerHand.isBust()) {

            System.out.println("\nDealer busts! You win $" + bet);

            //module 3, updates balance and wins if the player wins
            player.setBalance(player.getBalance() + bet);
            player.setNumberOfWins(player.getNumberOfWins() + 1);

        } else if (playerHand.getScore() > dealerHand.getScore()) {

            //lab 1 p3, player wins if their score is higher
            System.out.println("\nYou win $" + bet);

            //module 3, updates the player's balance and wins
            player.setBalance(player.getBalance() + bet);
            player.setNumberOfWins(player.getNumberOfWins() + 1);

        } else if (playerHand.getScore() < dealerHand.getScore()) {

            //lab 1 p3, player loses if the dealer has a higher score
            System.out.println("\nDealer wins. You lose $" + bet);

            // module 3, subtracts the bet from the player's balance
            player.setBalance(player.getBalance() - bet);

        } else {

            // lab 1 p3, equal scores mean a tie so the balance stays the same
            System.out.println("\nIt's a tie! Your balance stays the same.");
        }
    }


     /**
     * Displays all the cards in the player's or dealer's hand
     * @param hand the hand containing the cards to display
     */
    private void showHand(Hand hand) {     // lab 4 p3, method to display all the cards in a hand

        //module 1, enhanced for loop goes through each card
        for (Card card : hand.getCards()) {
         //card represents each Card object in the hand one at a time
            System.out.println(card); // module 1 p6, prints each card
        }
    }
}
