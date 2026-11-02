package mru.game.controller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;//this is from module 1, file handling section
import java.util.ArrayList;
import java.util.Scanner;

import mru.game.model.Player;
import mru.game.view.AppMenu;


public class GameManager {
	
	/* In this class you'll need these methods:
	 * A constructor
	 * A method to load the txt file into an arraylist (if it exists, so you check if the txt file exists first)
	 * A save method to store the arraylist into the the txt file 
	 * A method to search for a player based their name
	 * A method to find the top players
	 * Depending on your designing technique you may need and you can add more methods here 
	 */
private static final String FILE_PATH = "res/CasinoInfo.txt";

    ArrayList<Player> players; //<> its telling us the type of array it is

    File myFile; //declare the file here

    AppMenu appMen;

    BlackjackGame game;

    // Constructor
	 /**
     * Creates GameManager and the player list and menus and loads the player records
     * @throws IOException if there is a problem loading the file
     */
    public GameManager() throws IOException { //sn added ioexception

        players = new ArrayList<Player>();

        appMen = new AppMenu();

        game = new BlackjackGame(appMen);

        loadPlayers();
// sn removed start bc AppDriver alr calls it
    }
     /**
     * Starts the main casino menu
     * lets players play Blackjack, search for players or exit
     * @throws IOException if there is a problem saving the file
     */
    public void start() throws IOException { //sn just added io exceptionn

        boolean flag = true; //this flag is asking us whether we want to continue or not.

        while (flag) { //while loop just in case the user wants to exit the game.

            String choice = appMen.showMainMenu();

            if (choice.equals("p")) {

                String name = appMen.promptName();

                Player player = searchPlayer(name);

                //Checks if the player already exists or not.
                if (player == null) {

                    player = new Player(name, 100, 0);

                    players.add(player);

                    System.out.println("New player created!");

                } else {

                    System.out.println("Welcome back, " + player.getName() + "!");
                }

                //Checks if the player has enough money to play.
                if (player.getBalance() >= 2) {

                    game.playGame(player);

                } else {

                    System.out.println("You don't have enough money to play.");

                    appMen.pressEnter();
                }

            } else if (choice.equals("s")) {

                boolean searching = true;

                //This loop allows the user to keep searching for players.
                while (searching) {

                    String searchChoice = appMen.showSearchMenu();

                    if (searchChoice.equals("t")) {

                        findTopPlayers();

                        appMen.pressEnter();

                    } else if (searchChoice.equals("n")) {

                        String name = appMen.promptName();

                        Player player = searchPlayer(name);

                        //Checks if the player was found or not.
                        if (player == null) {

                            System.out.println("Player not found.");

                        } else {

                            System.out.println(player);
                        }

                        appMen.pressEnter();

                    } else if (searchChoice.equals("b")) {

                        searching = false; //Stops the search loop and goes back to the main menu.
                    }
                }

            } else if (choice.equals("e")) {

                savePlayers();

                System.out.println("Thank you for playing!");

                flag = false; //Stops the main loop and exits the game.
            }
        }
    }

    //Searches for a player using their name.
/**
     * Searches for a player using their name
     * @param name the name of the player being searched
     * @return the player if found or returns null if not found
     */
    private Player searchPlayer(String name) {

        for (Player player : players) { //Goes through the players in the ArrayList.

            if (player.getName().equalsIgnoreCase(name)) {

                return player; //Returns the player if the name matches.
            }
        }

        return null; //Returns null if the player is not found.
    }

    //Finds and displays the player with the most wins.

	/**
 	* Finds the player with the highest wins
 	* Displays all players who have the same highest number of wins
	*/
    private void findTopPlayers() {

        if (players.size() == 0) {

            System.out.println("There are no players yet.");

        } else {

            int highestWins = players.get(0).getNumberOfWins();

            //Finds the highest number of wins.
            for (Player player : players) {

                if (player.getNumberOfWins() > highestWins) {

                    highestWins = player.getNumberOfWins();
                }
            }

            System.out.println("\nTop player(s):");

            //Displays all players who have the highest number of wins.
            for (Player player : players) {

                if (player.getNumberOfWins() == highestWins) {

                    System.out.println(player);
                }
            }
        }
    }

    //This is responsible for checking whether the file exists or not.
    //If it exists, it is going to open it.
	/**
    * Loads the player records from CasinoInfo.txt
    * Reads each line and creates a Player 
    * @throws IOException if there is a problem reading the file
    */
    private void loadPlayers() throws IOException {

        myFile = new File(FILE_PATH);

        if (myFile.exists()) {

            Scanner input = new Scanner(myFile); //Now the file is open.

            String[] splittedLine;

            while (input.hasNextLine()) {

                splittedLine = input.nextLine().split(","); //Reads the whole line, then splits it based on the comma.

                //Then we are creating an object based on what we have above.
                //The player has 3 elements: name, balance and number of wins.

                players.add(new Player(splittedLine[0],
                        Double.parseDouble(splittedLine[1]),
                        Integer.parseInt(splittedLine[2])));
            }

            input.close();
        }
    }

    //This is responsible for saving all the player information into the file.
     /**
     * Saves all player records to CasinoInfo.txt
     * Writes each player's name, balance and wins into the file
     * @throws IOException if there is a problem writing to the file
     */
	private void savePlayers() throws IOException {

        FileWriter writer = new FileWriter(myFile); //Now the file is open for writing.

        //Goes through every player in the ArrayList.
        for (Player player : players) {

            writer.write(player.toFileString() + "\n"); //Writes the player information into the file.
        }

        writer.close(); //Closes the file.
    }
}
