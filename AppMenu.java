// Noor pt.4 (~1 hr)

package mru.game.view;

import java.util.Scanner;

public class AppMenu {

	/**
	 * This class will be used to show the menus and sub menus to the user
	 * It also prompts the user for the inputs and validates them 
	 */
	
	// Scanner reads what the user types; private so only AppMenu reads the keyboard.
	private Scanner input;

	// Constructor: creates the Scanner once, so every method can use it
	public AppMenu() {
		input = new Scanner(System.in);
	}

	// Shows the main menu and keeps asking until the user types p, s or e
	public String showMainMenu() {
		String choice;

		while (true) {
			System.out.println("Select one of these options:\n");
			System.out.println("\t(P) Play game");
			System.out.println("\t(S) Search");
			System.out.println("\t(E) Exit");
			System.out.print("\nEnter a choice: ");

			choice = input.nextLine().toLowerCase(); // lab 3 p3, converts to lowercase

			// valid choice: send it back to whoever called this method
			if (choice.equals("p") || choice.equals("s") || choice.equals("e")) {
				return choice;
			}

			// invalid choice: show an error, then the loop repeats the menu
			System.out.println("\nInvalid choice, please try again.\n");
		}
	}

		// Ok so now I'm working on the sub-menu where player asks for liek the leaderboard
	   // Show the search sub-menu and keeps asking until the user types t, n or b
	public String showSearchMenu() {
		String choice;

		// basically I made a while loop that will keep repeating this menu unless the player a valid choice
		while (true) {
			System.out.println("Select one of these options:\n");
			System.out.println("\t(T) Top player (Most number of wins)");
			System.out.println("\t(N) Looking for a Name");
			System.out.println("\t(B) Back to Main menu");
			System.out.print("\nEnter a choice: ");

			// clean up the input
			choice = input.nextLine().toLowerCase(); // lab 3 p3, converts to lowercase
			// valid choice: send it back to whoever called this method
			if (choice.equals("t") || choice.equals("n") || choice.equals("b")) {
				return choice;
			}

			// invalid choice: show an error, then the loop shows the menu again
			System.out.println("\nInvalid choice, please try again.\n");
		}
	}

    // Asks for the player's name and keeps asking until it is valid
    public String promptName() {
        String name;

        while (true) {
            System.out.print("What is your name: ");
            name = input.nextLine(); // module 1, reads input

            boolean valid = name.length() > 0; // module 1 String, lab 3 p2

            // checks each character to make sure the name doesn't have a comma
            for (int i = 0; i < name.length(); i++) { // lab 2 p3
                if (name.charAt(i) == ',') { // lab 3 p2, checks characters
                    valid = false;
                }
            }
			
			if (valid) {
			    return name;
			}

			System.out.println("\nInvalid name, please try again.\n");
		}
	}

	// Waits for the user to press Enter before continuing
	public void pressEnter() {
		System.out.print("\nPress \"Enter\" to continue...");
		input.nextLine();
	}

	// ok now the money HEHEHEHEHEH
	// Asks how much to bet; must be a whole number from $2 up to the player's balance

	// Takes the player's balance as input and returns the bet as an int
public int promptBet(double balance) {

    while (true) {

        System.out.print("How much do you want to bet this round? ");
        String text = input.nextLine(); // module 1 files, reads string/input

        boolean valid = text.length() > 0; // lab 3 p2, checks input length

        for (int i = 0; i < text.length(); i++) { // lab 2 p3
            if (!Character.isDigit(text.charAt(i))) { // lab 3 p2
                valid = false;
            }
        }

        if (valid && text.length() <= 9) { // lab 1 p2, limits number length
            int bet = Integer.parseInt(text); // module 1 string conversion

            if (bet >= 2 && bet <= balance) { // lab 1 p3, checks bet amount
                return bet;
            }
        }

        System.out.println("Invalid bet, please try again."); // module 1 p6
    }
}


	// Shows the Hit/Stand menu and returns "1" (hit) or "2" (stand)
	public String showHitStandMenu() {
		while (true) {
			System.out.println("Select an option:\n");
			System.out.println("\t1.Hit");
			System.out.println("\t2.Stand");
			System.out.print("\nYour choice: ");

			String choice = input.nextLine(); // module 1, reads input
			if (choice.equals("1") || choice.equals("2")) {
				return choice;
			}

			System.out.println("\nInvalid choice, please try again.\n");
		}
	}
	
    // lab 4 p3, method to ask if the player wants another round
    public String showPlayAgainMenu() {

        //module 1 loops, repeats until the player enters a valid choice
        while (true) {

            // module 1 p6, displays the play again options
            System.out.println("\nWould you like to play again?");
            System.out.println("\t1. Play again");
            System.out.println("\t2. Return to Main Menu");
            System.out.print("\nYour choice: ");

            //module 1, reads what the player entered
            String choice = input.nextLine();

            //lab 1 p3, checks if the player entered 1 or 2
            if (choice.equals("1") || choice.equals("2")) {
                return choice; // lab 4 p3, returns the valid choice
            }

            // module 1 p6, displays an error for invalid input
            System.out.println("\nInvalid choice, please try again.\n");
        }
    }

}


