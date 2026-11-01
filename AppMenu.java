// Noor pt.4 (~30 min)

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

			// trim removes extra spaces, toLowerCase makes P and p the same
			// basically, we clean up the input!
			choice = input.nextLine().trim().toLowerCase();

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
			choice = input.nextLine().trim().toLowerCase();

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
			name = input.nextLine().trim(); 
			// trim removes the end/start of the input btw (cleans it up)

			// a name can't be empty, and can't have a comma because commas separate the fields in CasinoInfo.txt
			if (!name.isEmpty() && !name.contains(",")) {
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
}


