// Noor's work :3
package mru.game.model;

public class Player {

	/**
	 * This class represent each player record in the Database
	 * It is basically a model class for each record in the txt file
	 */

	private String name;
	private double balance; // sn changed int to double
	private int numberOfWins;

	/**
    * Creates a player with their name, balance, and wins
    * @param name the player's name
    * @param balance the player's current balance
    * @param numberOfWins the number of games the player has won
    */
// sn changed int balance to double
	public Player(String name, double balance, int numberOfWins) {
		// "this" refers to the current Player object, so this.name is the field and name is the value passed into the constructor.
		this.name = name;
		this.balance = balance;
		this.numberOfWins = numberOfWins;
	}

	// Getters return a private field's value, while setters change it and are void because they return nothing.
	
	/**
    * Gets the player's name
    * @return the player's name
    */
	public String getName() {
		return name;
	}
	
	/**
    * Changes the player's name
    * @param name the new name
    */
	public void setName(String name) {
		this.name = name;
	}

	/**
    * Gets the player's current balance
    * @return the player's balance
    */
	// changed int to double sn
	public double getBalance() {
		return balance;
	}

	/**
    * Changes the player's balance
    * @param balance the new balance
    */
// changed int to double sn
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
    * Gets the number of games the player has won
    * @return the number of wins
    */
	public int getNumberOfWins() {
		return numberOfWins;
	}
	/**
    * Changes the player's number of wins
    * @param numberOfWins updated number of wins
    */
	public void setNumberOfWins(int numberOfWins) {
		this.numberOfWins = numberOfWins;
	}

	/**
    * Displays the player's name, balance, and wins.
    * @return the player information as a string
    */
	// Returns a readable version of the player.
	public String toString() {
		return name + " | Balance: " + balance + " $ | Wins: " + numberOfWins;
	}
	
    /**
    * Converts the player information into one line for CasinoInfo.txt
    * @return the player's name, balance, and wins
    */
	// Returns the player as one line for the text file: name,balance,numberOfWins
	public String toFileString() {
		return name + "," + balance + "," + numberOfWins;
	}
}
