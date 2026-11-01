// Noor's work :3
package mru.game.model;

public class Player {

	/**
	 * This class represent each player record in the Database
	 * It is basically a model class for each record in the txt file
	 */

	private String name;
	private int balance;
	private int numberOfWins;

	public Player(String name, int balance, int numberOfWins) {
		// "this" refers to the current Player object, so this.name is the field and name is the value passed into the constructor.
		this.name = name;
		this.balance = balance;
		this.numberOfWins = numberOfWins;
	}

	// Getters return a private field's value, while setters change it and are void because they return nothing.
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getNumberOfWins() {
		return numberOfWins;
	}

	public void setNumberOfWins(int numberOfWins) {
		this.numberOfWins = numberOfWins;
	}

	// Returns a readable version of the player.
	public String toString() {
		return name + " | Balance: " + balance + " $ | Wins: " + numberOfWins;
	}

	// Returns the player as one line for the text file: name,balance,numberOfWins
	public String toFileString() {
		return name + "," + balance + "," + numberOfWins;
	}
}
