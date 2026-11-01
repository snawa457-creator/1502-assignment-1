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
}


