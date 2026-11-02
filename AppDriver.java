//Sanchia

package mru.game.application;
import mru.game.controller.GameManager; // module 3, packages and imports
public class AppDriver {

     /**
     * Main method to start the program
     * @param args command line arguments
     */
	public static void main(String[] args) {
		
		GameManager game = new GameManager (); // module 3 p6
		game.start(); // lab 4 p3, calling methods
	}

}

